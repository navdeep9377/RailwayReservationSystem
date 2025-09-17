package com.example.railway.dto;

import com.example.railway.entity.BookingStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private String pnrNumber;
    private String trainNumber;
    private String passengerName;
    private int seatNumber;
    private BookingStatus status;
    private LocalDateTime bookingDate;
}
