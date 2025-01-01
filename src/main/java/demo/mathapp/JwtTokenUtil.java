package demo.mathapp;


import demo.mathapp.model.token.JwtTokenConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtTokenUtil {

    private final JwtTokenConfiguration tokenConfiguration;

    public String generateToken(String secret, Long expiration, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("role", roles);
        return doGenerateToken(secret, expiration, claims, userDetails.getUsername());
    }

    public Boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(tokenConfiguration.getSecret(), token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(tokenConfiguration.getSecret(), token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String secret, String token) {
        return getClaimFromToken(secret, token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String secret, String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(secret, token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String secret, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
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
                .signWith(getSigningKey(secret), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
