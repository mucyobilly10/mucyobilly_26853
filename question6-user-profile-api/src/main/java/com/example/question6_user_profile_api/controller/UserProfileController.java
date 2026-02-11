package com.example.question6_user_profile_api.controller;

import com.example.question6_user_profile_api.model.ApiResponse;
import com.example.question6_user_profile_api.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    
    // list to store user profiles
    private List<UserProfile> userList = new ArrayList<>();
    
    // constructor to add sample data
    public UserProfileController() {
        // adding sample user profiles
        userList.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software developer passionate about coding", true));
        userList.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 30, "Canada", "Data scientist and AI enthusiast", true));
        userList.add(new UserProfile(3L, "mike_johnson", "mike@example.com", "Mike Johnson", 22, "UK", "College student studying computer science", true));
        userList.add(new UserProfile(4L, "sarah_williams", "sarah@example.com", "Sarah Williams", 28, "Australia", "Full-stack developer and tech blogger", false));
        userList.add(new UserProfile(5L, "david_brown", "david@example.com", "David Brown", 35, "USA", "Senior software engineer with 10 years experience", true));
        userList.add(new UserProfile(6L, "emma_davis", "emma@example.com", "Emma Davis", 27, "Germany", "UX designer creating beautiful interfaces", true));
        userList.add(new UserProfile(7L, "alex_wilson", "alex@example.com", "Alex Wilson", 24, "Canada", "Mobile app developer", false));
        userList.add(new UserProfile(8L, "lisa_martin", "lisa@example.com", "Lisa Martin", 31, "USA", "Product manager at tech startup", true));
    }
    
    // GET /api/users - get all user profiles
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "User profiles retrieved successfully",
            userList
        );
        return ResponseEntity.ok(response);
    }
    
    // GET /api/users/{userId} - get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        for (UserProfile user : userList) {
            if (user.getUserId().equals(userId)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile found",
                    user
                );
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User profile not found",
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    // GET /api/users/search/username?username={username}
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<UserProfile>> searchByUsername(@RequestParam String username) {
        for (UserProfile user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User found by username",
                    user
                );
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "No user found with username: " + username,
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    // GET /api/users/search/country/{country}
    @GetMapping("/search/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@PathVariable String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userList) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Found " + result.size() + " user(s) from " + country,
            result
        );
        return ResponseEntity.ok(response);
    }
    
    // GET /api/users/search/age-range?min={min}&max={max}
    @GetMapping("/search/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam int min, 
            @RequestParam int max) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userList) {
            if (user.getAge() >= min && user.getAge() <= max) {
                result.add(user);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Found " + result.size() + " user(s) in age range " + min + "-" + max,
            result
        );
        return ResponseEntity.ok(response);
    }
    
    // GET /api/users/active - get active users only
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getActiveUsers() {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : userList) {
            if (user.isActive()) {
                result.add(user);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(
            true,
            "Active users retrieved successfully",
            result
        );
        return ResponseEntity.ok(response);
    }
    
    // POST /api/users - create new user profile
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        // generate new ID
        Long newId = 1L;
        for (UserProfile u : userList) {
            if (u.getUserId() >= newId) {
                newId = u.getUserId() + 1;
            }
        }
        user.setUserId(newId);
        userList.add(user);
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            true,
            "User profile created successfully",
            user
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // PUT /api/users/{userId} - update user profile
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(
            @PathVariable Long userId, 
            @RequestBody UserProfile updatedUser) {
        
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId().equals(userId)) {
                updatedUser.setUserId(userId);
                userList.set(i, updatedUser);
                
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile updated successfully",
                    updatedUser
                );
                return ResponseEntity.ok(response);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User profile not found",
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    // PATCH /api/users/{userId}/activate - activate user
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : userList) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile activated successfully",
                    user
                );
                return ResponseEntity.ok(response);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User profile not found",
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    // PATCH /api/users/{userId}/deactivate - deactivate user
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : userList) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                
                ApiResponse<UserProfile> response = new ApiResponse<>(
                    true,
                    "User profile deactivated successfully",
                    user
                );
                return ResponseEntity.ok(response);
            }
        }
        
        ApiResponse<UserProfile> response = new ApiResponse<>(
            false,
            "User profile not found",
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    // DELETE /api/users/{userId} - delete user profile
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId().equals(userId)) {
                userList.remove(i);
                
                ApiResponse<Void> response = new ApiResponse<>(
                    true,
                    "User profile deleted successfully",
                    null
                );
                return ResponseEntity.ok(response);
            }
        }
        
        ApiResponse<Void> response = new ApiResponse<>(
            false,
            "User profile not found",
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
