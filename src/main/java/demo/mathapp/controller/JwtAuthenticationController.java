package demo.mathapp.controller;

import demo.mathapp.CustomUserDetails;
import demo.mathapp.LoginResponse;
import demo.mathapp.JwtTokenUtil;
import demo.mathapp.service.impl.UserDetailsService;
import demo.mathapp.transferobject.LoginDto;
import demo.mathapp.transferobject.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto jwtRequest) throws Exception{
        Authentication authenticate = authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        UserInfo userInfo = new UserInfo(userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName());

        return ResponseEntity.ok(new LoginResponse(userInfo,token));
    }

    private Authentication authenticate(String email, String password) throws Exception{
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }
}
