package com.agrarco.agrovestapi.ScalesRepository;

import com.agrarco.agrovestapi.ScalesEntity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    Optional<Lot> findByNumber(String number);
}
