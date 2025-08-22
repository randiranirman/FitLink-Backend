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

@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse registerUser(@Valid @RequestBody RegisterRequest request) {

        try {
            System.out.println("registering user " + request.username());
            return authService.register(request);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
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

    @GetMapping("/test")
    public String  testController() {
        return "this is the test controller for auth service";
    }




}
