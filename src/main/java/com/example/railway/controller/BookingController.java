package com.example.railway.controller;

import com.example.railway.dto.BookingRequestDTO;
import com.example.railway.dto.BookingResponseDTO;
import com.example.railway.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequest) {
        return ResponseEntity.ok(bookingService.bookTicket(bookingRequest));
    }

    @GetMapping("/{pnr}")
    public ResponseEntity<BookingResponseDTO> getBookingByPnr(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.getBookingByPnr(pnr));
    }

    @DeleteMapping("/{pnr}")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable String pnr) {
        return ResponseEntity.ok(bookingService.cancelBooking(pnr));
    }
}
