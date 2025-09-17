package com.example.railway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainNumber;
    private String trainName;
    private String sourceStation;
    private String destinationStation;
    private int totalSeats;
    private int availableSeats;
}
