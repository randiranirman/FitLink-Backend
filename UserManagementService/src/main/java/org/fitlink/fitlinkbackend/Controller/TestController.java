package org.fitlink.fitlinkbackend.Controller;

import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Autowired
    private AppUserRepository userRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<AppUser> users = userRepository.findAll();
            System.out.println("Found " + users.size() + " users in database");
            for (AppUser user : users) {
                System.out.println("User: " + user.getEmail() + ", Role: " + user.getUserRole());
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            System.err.println("Error retrieving users: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/users/count")
    public ResponseEntity<?> getUserCount() {
        try {
            long count = userRepository.count();
            System.out.println("Total users count: " + count);
            Map<String, Object> response = new HashMap<>();
            response.put("totalUsers", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error counting users: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/mongodb/connection")
    public ResponseEntity<?> testMongoConnection() {
        try {
            // Test MongoDB connection
            String dbName = mongoTemplate.getDb().getName();
            System.out.println("Connected to MongoDB database: " + dbName);
            
            // Get collection names
            var collections = mongoTemplate.getCollectionNames();
            System.out.println("Available collections: " + collections);
            
            Map<String, Object> response = new HashMap<>();
            response.put("connected", true);
            response.put("database", dbName);
            response.put("collections", collections);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("MongoDB connection error: " + e.getMessage());
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("connected", false);
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/collections/app_users")
    public ResponseEntity<?> checkAppUsersCollection() {
        try {
            // Check if the app_users collection exists and has data
            boolean exists = mongoTemplate.collectionExists("app_users");
            long count = 0;
            if (exists) {
                count = mongoTemplate.getCollection("app_users").countDocuments();
            }
            
            System.out.println("Collection 'app_users' exists: " + exists);
            System.out.println("Documents in 'app_users': " + count);
            
            Map<String, Object> response = new HashMap<>();
            response.put("collectionExists", exists);
            response.put("documentCount", count);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error checking app_users collection: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
