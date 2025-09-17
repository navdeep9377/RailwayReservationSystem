package com.example.railway.repository;

import com.example.railway.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPnrNumber(String pnrNumber);
}
