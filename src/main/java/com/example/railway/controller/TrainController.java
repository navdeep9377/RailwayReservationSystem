package com.example.railway.controller;

import com.example.railway.entity.Train;
import com.example.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping("/search")
    public List<Train> searchTrains(@RequestParam String source, @RequestParam String destination) {
        return trainService.searchTrains(source, destination);
    }
}
