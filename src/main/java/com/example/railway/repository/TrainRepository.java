package com.example.railway.repository;

import com.example.railway.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Add this import
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findBySourceStationAndDestinationStationIgnoreCase(String source, String destination);

    // --- ADD THESE TWO NEW METHODS ---
    @Query("SELECT DISTINCT t.sourceStation FROM Train t ORDER BY t.sourceStation")
    List<String> findDistinctSourceStations();

    @Query("SELECT DISTINCT t.destinationStation FROM Train t ORDER BY t.destinationStation")
    List<String> findDistinctDestinationStations();
}