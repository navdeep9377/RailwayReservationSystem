package com.example.railway.service;

import com.example.railway.dto.BookingRequestDTO;
import com.example.railway.dto.BookingResponseDTO;
import com.example.railway.entity.Booking;
import com.example.railway.entity.BookingStatus;
import com.example.railway.entity.Train;
import com.example.railway.exception.ResourceNotFoundException;
import com.example.railway.repository.BookingRepository;
import com.example.railway.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Transactional
    public BookingResponseDTO bookTicket(BookingRequestDTO bookingRequest) {
        Train train = trainRepository.findById(bookingRequest.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + bookingRequest.getTrainId()));

        if (train.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available on this train.");
        }

        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

        Booking booking = new Booking();
        booking.setPassengerName(bookingRequest.getPassengerName());
        booking.setPassengerAge(bookingRequest.getPassengerAge());
        booking.setTrain(train);
        booking.setPnrNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setSeatNumber(train.getTotalSeats() - train.getAvailableSeats());
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);
        return convertToResponseDTO(savedBooking);
    }

    public BookingResponseDTO getBookingByPnr(String pnrNumber) {
        Booking booking = bookingRepository.findByPnrNumber(pnrNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with PNR: " + pnrNumber));
        return convertToResponseDTO(booking);
    }

    @Transactional
    public BookingResponseDTO cancelBooking(String pnrNumber) {
        Booking booking = bookingRepository.findByPnrNumber(pnrNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with PNR: " + pnrNumber));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled.");
        }

        Train train = booking.getTrain();
        train.setAvailableSeats(train.getAvailableSeats() + 1);
        trainRepository.save(train);

        booking.setStatus(BookingStatus.CANCELLED);
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToResponseDTO(updatedBooking);
    }

    private BookingResponseDTO convertToResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setPnrNumber(booking.getPnrNumber());
        dto.setTrainNumber(booking.getTrain().getTrainNumber());
        dto.setPassengerName(booking.getPassengerName());
        dto.setSeatNumber(booking.getSeatNumber());
        dto.setStatus(booking.getStatus());
        dto.setBookingDate(booking.getBookingDate());
        return dto;
    }
}
