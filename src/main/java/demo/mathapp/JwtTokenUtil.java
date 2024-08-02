package demo.mathapp;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {


    public String generateToken(String secret, Long expiration, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("role", roles);
        return doGenerateToken(secret, expiration, claims, userDetails.getUsername());
    }

    public Boolean validateToken(String secret, String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(secret, token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(secret, token));
    }

    public String getUsernameFromToken(String secret, String token) {
        return getClaimFromToken(secret, token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String secret, String token) {
        return getClaimFromToken(secret, token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String secret, String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(secret, token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String secret, String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String secret, String token) {
        final Date expiration = getExpirationDateFromToken(secret, token);
        return expiration.before(new Date());
    }

    private String doGenerateToken(String secret, Long expiration, Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
