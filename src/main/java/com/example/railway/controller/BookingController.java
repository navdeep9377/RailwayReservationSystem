package com.example.railway.controller;

import com.example.railway.dto.BookingRequestDTO;
import com.example.railway.dto.BookingResponseDTO;
import com.example.railway.service.BookingService;
import jakarta.servlet.http.HttpSession; // Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Add this import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // ADMIN-ONLY: Get all bookings
    @GetMapping("/all")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings(HttpSession session) {
        // --- SECURITY CHECK ---
        if (session.getAttribute("adminUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // ---
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // PUBLIC: Anyone can book a ticket
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequest) {
        return ResponseEntity.ok(bookingService.bookTicket(bookingRequest));
    }

    // PUBLIC: Anyone can check their own PNR status
    @GetMapping("/{pnr}")
    public ResponseEntity<BookingResponseDTO> getBookingByPnr(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.getBookingByPnr(pnr));
    }

    // ADMIN-ONLY: Cancel any ticket
    @DeleteMapping("/{pnr}")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable String pnr, HttpSession session) {
        // --- SECURITY CHECK ---
        if (session.getAttribute("adminUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // ---
        return ResponseEntity.ok(bookingService.cancelBooking(pnr));
    }
}