package org.fitlink.fitlinkbackend.Controller;


import jakarta.validation.Valid;
import org.fitlink.fitlinkbackend.Dto.AuthReponse;
import org.fitlink.fitlinkbackend.Dto.LoginRequest;
import org.fitlink.fitlinkbackend.Dto.RegisterRequest;
import org.fitlink.fitlinkbackend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping( "/api/auth")
@ResponseStatus (HttpStatus.OK)
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthReponse authReponse = authService.register(request);
            return ResponseEntity.ok(authReponse);

        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  loginUser(@Valid @RequestBody LoginRequest request) {
        try  {
            AuthReponse authReponse  = authService.login(request);
            return  ResponseEntity.ok(authReponse);
        }catch ( RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
