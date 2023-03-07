package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.model.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

}
