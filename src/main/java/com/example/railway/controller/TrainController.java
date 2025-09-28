package com.example.railway.controller;

import com.example.railway.entity.Train;
import com.example.railway.service.TrainService;
import jakarta.servlet.http.HttpSession; // Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Add this import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    // ADMIN-ONLY: Get all trains
    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains(HttpSession session) {
        // --- SECURITY CHECK ---
        if (session.getAttribute("adminUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // ---
        return ResponseEntity.ok(trainService.getAllTrains());
    }

    // PUBLIC: Anyone can search for trains
    @GetMapping("/search")
    public List<Train> searchTrains(@RequestParam String source, @RequestParam String destination) {
        return trainService.searchTrains(source, destination);
    }

    // ADMIN-ONLY: Add a new train
    @PostMapping
    public ResponseEntity<Train> addTrain(@RequestBody Train train, HttpSession session) {
        // --- SECURITY CHECK ---
        if (session.getAttribute("adminUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // ---
        Train savedTrain = trainService.addTrain(train);
        return ResponseEntity.ok(savedTrain);
    }

    // ADMIN-ONLY: Delete a train
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long id, HttpSession session) {
        // --- SECURITY CHECK ---
        if (session.getAttribute("adminUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // ---
        trainService.deleteTrain(id);
        return ResponseEntity.noContent().build();
    }

    // These endpoints for getting station lists are public
    @GetMapping("/stations/sources")
    public List<String> getSourceStations() {
        return trainService.getSourceStations();
    }

    @GetMapping("/stations/destinations")
    public List<String> getDestinationStations() {
        return trainService.getDestinationStations();
    }
}