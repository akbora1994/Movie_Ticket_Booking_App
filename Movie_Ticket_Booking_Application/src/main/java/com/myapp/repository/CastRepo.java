package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.model.Cast;

public interface CastRepo extends JpaRepository<Cast, Integer> {

}
