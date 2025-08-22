package org.fitlink.fitlinkbackend.Controller;


import jakarta.validation.Valid;
import org.fitlink.fitlinkbackend.Dto.AuthResponse;
import org.fitlink.fitlinkbackend.Dto.LoginRequest;
import org.fitlink.fitlinkbackend.Dto.RegisterRequest;
import org.fitlink.fitlinkbackend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            AuthResponse authResponse = authService.register(request);

            return ResponseEntity.ok(authResponse);

        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")

    public ResponseEntity<?>  loginUser(@Valid @RequestBody LoginRequest request) {
        try  {
            AuthResponse authResponse = authService.login(request);
            return  ResponseEntity.ok(authResponse);
        }catch ( RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
