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

    public List<Train> searchTrains(String source, String destination) {
        return trainRepository.findBySourceStationAndDestinationStation(source, destination);
    }
}
