package com.agrarco.agrovestapi.ScalesRepository;

import com.agrarco.agrovestapi.ScalesEntity.Tedarukcu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TedarukcuRepository extends JpaRepository<Tedarukcu, Long> {
    Optional<Tedarukcu> findFirstByName(String name);
}