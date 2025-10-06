package com.agrarco.agrovestapi.ScalesRepository;

import com.agrarco.agrovestapi.ScalesEntity.Anbar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnbarRepository extends JpaRepository<Anbar, Long> {
    Optional<Anbar> findFirstByName(String name);
}
