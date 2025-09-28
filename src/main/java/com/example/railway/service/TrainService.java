package com.example.railway.service;

import com.example.railway.entity.Train;
import com.example.railway.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    // ... (other methods like getAllTrains, addTrain, etc. are here)

    // --- ADD THESE TWO NEW METHODS ---
    public List<String> getSourceStations() {
        return trainRepository.findDistinctSourceStations();
    }

    public List<String> getDestinationStations() {
        return trainRepository.findDistinctDestinationStations();
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainRepository.findBySourceStationAndDestinationStationIgnoreCase(source, destination);
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    public void deleteTrain(Long id) {
        trainRepository.deleteById(id);
    }
}