package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.model.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Integer> {

}
