package com.example.railway;

import com.example.railway.entity.Train;
import com.example.railway.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RailwayReservationSystemApplication implements CommandLineRunner {

    @Autowired
    private TrainRepository trainRepository;

    public static void main(String[] args) {
        SpringApplication.run(RailwayReservationSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Pre-populate some train data
        trainRepository.save(new Train(null, "12015", "Shatabdi Express", "Delhi", "Chandigarh", 100, 100));
        trainRepository.save(new Train(null, "12951", "Rajdhani Express", "Mumbai", "Delhi", 150, 150));
        trainRepository.save(new Train(null, "22439", "Vande Bharat", "Delhi", "Katra", 80, 80));
        trainRepository.save(new Train(null, "12002", "Bhopal Shatabdi", "Delhi", "Bhopal", 120, 118));
    }
}
