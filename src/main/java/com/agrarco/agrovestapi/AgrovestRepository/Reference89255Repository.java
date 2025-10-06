package com.agrarco.agrovestapi.AgrovestRepository;

import com.agrarco.agrovestapi.AgrovestEntity.Reference734;
import com.agrarco.agrovestapi.AgrovestEntity.Reference89255;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Reference89255Repository extends JpaRepository<Reference89255, UUID> {
    UUID id(UUID id);
}
