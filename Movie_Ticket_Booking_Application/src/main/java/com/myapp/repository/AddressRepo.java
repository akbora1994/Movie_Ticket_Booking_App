package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
