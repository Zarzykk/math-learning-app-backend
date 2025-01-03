package demo.mathapp.service;

import demo.mathapp.CustomUserDetails;
import demo.mathapp.JwtTokenUtil;
import demo.mathapp.LoginResponse;
import demo.mathapp.model.token.JwtTokenConfiguration;
import demo.mathapp.service.impl.UserDetailsService;
import demo.mathapp.transferobject.LoginDTO;
import demo.mathapp.transferobject.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final JwtTokenConfiguration jwtTokenConfiguration;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;


    public LoginResponse getLoginResponse(LoginDTO jwtRequest) {
        Authentication authenticate = null;
        final String secret = jwtTokenConfiguration.getSecret();
        final Long expiration = Long.parseLong(jwtTokenConfiguration.getExpiration());
        try {
            authenticate = authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        CustomUserDetails userDetails = getUserDetails(jwtRequest.getEmail());
        String token = jwtTokenUtil.generateToken(secret, expiration, userDetails);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName());

        return new LoginResponse(userInfoDTO, token);
    }

    private Authentication authenticate(String email, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public CustomUserDetails getUserDetails(String email) {
        return(CustomUserDetails) userDetailsService.loadUserByUsername(email);
    }
}
