package demo.mathapp.controller;

import demo.mathapp.LoginResponse;
import demo.mathapp.service.JwtAuthenticationService;
import demo.mathapp.transferobject.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final JwtAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginDTO jwtRequest) {
        return ResponseEntity.ok(authenticationService.getLoginResponse(jwtRequest));
    }

}
