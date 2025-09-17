package com.example.railway.repository;

import com.example.railway.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findBySourceStationAndDestinationStation(String source, String destination);
}
