package com.example.railway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String pnrNumber;

    private String passengerName;
    private int passengerAge;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    private int seatNumber;
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
