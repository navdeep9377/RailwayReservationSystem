package com.example.railway;

import com.example.railway.entity.User; // Add this import
import com.example.railway.repository.UserRepository; // Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RailwayReservationSystemApplication implements CommandLineRunner {

    // Inject the UserRepository to interact with the user table
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RailwayReservationSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // --- THIS CODE CREATES THE DEFAULT ADMIN USER ---

        // Check if a user with the username "admin" already exists.
        if (userRepository.findByUsername("admin").isEmpty()) {

            // If not, create a new User object.
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("password"); // In a real app, this password should be encrypted!

            // Save the new admin user to the database.
            userRepository.save(admin);

            // Print a message to the console so we know it was created.
            System.out.println(">>> Created default admin user (admin/password) <<<");
        }
    }
}