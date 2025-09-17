package com.example.railway.dto;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long trainId;
    private String passengerName;
    private int passengerAge;
}
