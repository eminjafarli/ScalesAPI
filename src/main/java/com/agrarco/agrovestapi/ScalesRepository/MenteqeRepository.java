package com.agrarco.agrovestapi.ScalesRepository;

import com.agrarco.agrovestapi.ScalesEntity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenteqeRepository extends JpaRepository<Menteqe, Long> {
    Optional<Menteqe> findFirstByName(String name);
}

