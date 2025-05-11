package com.restapi.RestAPI.repository;

import com.restapi.RestAPI.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Model, Long> {
}
