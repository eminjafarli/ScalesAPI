package com.agrarco.agrovestapi.AgrovestRepository;

import com.agrarco.agrovestapi.AgrovestEntity.Reference734;
import com.agrarco.agrovestapi.AgrovestEntity.Reference921;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Reference921Repository extends JpaRepository<Reference921, UUID> {
    UUID id(UUID id);
}
