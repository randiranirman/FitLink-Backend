package org.fitlink.fitlinkbackend.Controller;

import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.fitlink.fitlinkbackend.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/oauth2/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OAuth2TestController {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Test OAuth2 configuration and get OAuth2 login URLs
     */
    @GetMapping("/config")
    public ResponseEntity<?> getOAuth2Config() {
        Map<String, Object> config = new HashMap<>();
        config.put("googleLoginUrl", "/oauth2/authorization/google");
        config.put("loginSuccessUrl", "/api/oauth2/test/success");
        config.put("loginFailureUrl", "/login?error=oauth2_error");
        config.put("message", "OAuth2 configuration is active");
        
        return ResponseEntity.ok(config);
    }

    /**
     * Test endpoint to verify OAuth2 authentication status
     */
    @GetMapping("/auth-status")
    public ResponseEntity<?> getAuthStatus(Authentication authentication, HttpServletRequest request) {
        Map<String, Object> status = new HashMap<>();
        
        if (authentication == null) {
            status.put("authenticated", false);
            status.put("authType", "none");
            status.put("message", "No authentication found");
        } else {
            status.put("authenticated", authentication.isAuthenticated());
            status.put("authType", authentication.getClass().getSimpleName());
            status.put("principal", authentication.getName());
            status.put("authorities", authentication.getAuthorities());
            
            // Check if it's OAuth2 authentication
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                status.put("oauth2User", true);
                status.put("email", oauth2User.getAttribute("email"));
                status.put("name", oauth2User.getAttribute("name"));
                status.put("attributes", oauth2User.getAttributes());
            } else {
                status.put("oauth2User", false);
            }
        }
        
        // Check for JWT in Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            boolean isValidJWT = jwtUtils.validateJwtToken(token);
            status.put("jwtPresent", true);
            status.put("jwtValid", isValidJWT);
            if (isValidJWT) {
                status.put("jwtUsername", jwtUtils.extractUsername(token));
            }
        } else {
            status.put("jwtPresent", false);
        }
        
        return ResponseEntity.ok(status);
    }

    /**
     * Test endpoint for authenticated OAuth2 users
     */
    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OAuth2User oauth2User, 
                                       Authentication authentication) {
        if (oauth2User == null) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "No OAuth2 user found",
                "message", "This endpoint requires OAuth2 authentication",
                "loginUrl", "/oauth2/authorization/google"
            ));
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", oauth2User.getAttribute("email"));
        userInfo.put("name", oauth2User.getAttribute("name"));
        userInfo.put("picture", oauth2User.getAttribute("picture"));
        userInfo.put("verified_email", oauth2User.getAttribute("verified_email"));
        userInfo.put("authorities", oauth2User.getAuthorities());
        userInfo.put("allAttributes", oauth2User.getAttributes());
        
        // Check if user exists in database
        String email = oauth2User.getAttribute("email");
        Optional<AppUser> dbUser = userRepository.findByEmail(email);
        if (dbUser.isPresent()) {
            userInfo.put("inDatabase", true);
            userInfo.put("dbUser", Map.of(
                "id", dbUser.get().getId(),
                "email", dbUser.get().getEmail(),
                "name", dbUser.get().getName(),
                "role", dbUser.get().getUserRole(),
                "fromOauth", dbUser.get().isFromOauth()
            ));
            
            // Generate JWT for this user
            String jwt = jwtUtils.generateToken(dbUser.get());
            userInfo.put("generatedJWT", jwt);
        } else {
            userInfo.put("inDatabase", false);
            userInfo.put("message", "User authenticated via OAuth2 but not found in database");
        }
        
        return ResponseEntity.ok(userInfo);
    }

    /**
     * Success page after OAuth2 login
     */
    @GetMapping("/success")
    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "OAuth2 login failed",
                "message", "No OAuth2 user information available"
            ));
        }

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "OAuth2 login successful");
        response.put("email", email);
        response.put("name", name);
        response.put("redirectMessage", "You should be redirected with a JWT token");
        
        // Check database user
        Optional<AppUser> dbUser = userRepository.findByEmail(email);
        if (dbUser.isPresent()) {
            String jwt = jwtUtils.generateToken(dbUser.get());
            response.put("jwt", jwt);
            response.put("user", Map.of(
                "id", dbUser.get().getId(),
                "email", dbUser.get().getEmail(),
                "name", dbUser.get().getName(),
                "role", dbUser.get().getUserRole()
            ));
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get all OAuth2 users from database
     */
    @GetMapping("/oauth2-users")
    public ResponseEntity<?> getOAuth2Users() {
        try {
            List<AppUser> oauth2Users = userRepository.findAll()
                .stream()
                .filter(AppUser::isFromOauth)
                .toList();
                
            Map<String, Object> response = new HashMap<>();
            response.put("total", oauth2Users.size());
            response.put("users", oauth2Users.stream().map(user -> Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "name", user.getName(),
                "role", user.getUserRole(),
                "fromOauth", user.isFromOauth()
            )).toList());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "error", "Failed to fetch OAuth2 users",
                "message", e.getMessage()
            ));
        }
    }

    /**
     * Test JWT generation for a specific user
     */
    @PostMapping("/generate-jwt")
    public ResponseEntity<?> generateJwtForUser(@RequestParam String email) {
        try {
            Optional<AppUser> user = userRepository.findByEmail(email);
            if (user.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "error", "User not found",
                    "email", email
                ));
            }
            
            String jwt = jwtUtils.generateToken(user.get());
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("email", email);
            response.put("jwt", jwt);
            response.put("user", Map.of(
                "id", user.get().getId(),
                "email", user.get().getEmail(),
                "name", user.get().getName(),
                "role", user.get().getUserRole(),
                "fromOauth", user.get().isFromOauth()
            ));
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "error", "Failed to generate JWT",
                "message", e.getMessage()
            ));
        }
    }

    /**
     * Test JWT validation
     */
    @PostMapping("/validate-jwt")
    public ResponseEntity<?> validateJwt(@RequestParam String token) {
        try {
            boolean isValid = jwtUtils.validateJwtToken(token);
            Map<String, Object> response = new HashMap<>();
            response.put("valid", isValid);
            
            if (isValid) {
                String username = jwtUtils.extractUsername(token);
                response.put("username", username);
                response.put("expiration", jwtUtils.extractExpiration(token));
            } else {
                response.put("message", "Token is invalid or expired");
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "valid", false,
                "error", "Token validation failed",
                "message", e.getMessage()
            ));
        }
    }

    /**
     * Health check for OAuth2 setup
     */
    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("oauth2Enabled", true);
        health.put("endpoints", Map.of(
            "googleLogin", "/oauth2/authorization/google",
            "authStatus", "/api/oauth2/test/auth-status",
            "userInfo", "/api/oauth2/test/user-info",
            "success", "/api/oauth2/test/success",
            "config", "/api/oauth2/test/config"
        ));
        health.put("message", "OAuth2 test controller is ready");
        
        return ResponseEntity.ok(health);
    }
}
