package com.agrarco.agrovestapi.AgrovestRepository;

import com.agrarco.agrovestapi.AgrovestEntity.Reference734;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Reference734Repository extends JpaRepository<Reference734, UUID> {
    UUID id(UUID id);
}
