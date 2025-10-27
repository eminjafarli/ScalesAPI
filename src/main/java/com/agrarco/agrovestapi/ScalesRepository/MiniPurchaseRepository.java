package com.agrarco.agrovestapi.ScalesRepository;

import com.agrarco.agrovestapi.ScalesEntity.miniPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiniPurchaseRepository extends JpaRepository<miniPurchase, Long> {
    List<miniPurchase> findByPurchaseId(Long purchaseId);
}
