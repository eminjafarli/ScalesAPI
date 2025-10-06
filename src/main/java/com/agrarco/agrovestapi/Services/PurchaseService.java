package com.agrarco.agrovestapi.Services;

import com.agrarco.agrovestapi.DTO.PurchaseRequestDTO;
import com.agrarco.agrovestapi.ScalesEntity.*;
import com.agrarco.agrovestapi.ScalesRepository.*;
import com.agrarco.agrovestapi.ScalesEntity.Tedarukcu;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final LotRepository lotRepository;
    private final MenteqeRepository menteqeRepository;
    private final RegionRepository regionRepository;
    private final AnbarRepository anbarRepository;
    private final TedarukcuRepository tedarukcuRepository;

    public void deletePurchase(Long id) {
        if (!purchaseRepository.existsById(id)) {
            throw new RuntimeException("Purchase not found with id: " + id);
        }
        purchaseRepository.deleteById(id);
    }

    @Transactional
    public Purchase savePurchase(PurchaseRequestDTO dto) {
        Purchase purchase = new Purchase();
        purchase.setNeqliyyatNomresi(dto.getNeqliyyatNomresi());
        purchase.setKiseSayi(dto.getKiseSayi());
        purchase.setBirKiseninCekisi(dto.getBirKiseninCekisi());
        purchase.setDoluCeki(dto.getDoluCeki());
        purchase.setBosCeki(dto.getBosCeki());
        purchase.setNetCeki(dto.getNetCeki());
        purchase.setQeyd(dto.getQeyd());
        purchase.setDoluTarix(dto.getDoluTarix());
        purchase.setBosTarix(dto.getBosTarix());

        Lot lot = new Lot();
        lot.setNumber(dto.getLotNomresi());
        lotRepository.save(lot);

        Menteqe menteqe = new Menteqe();
        menteqe.setName(dto.getMenteqe());
        menteqeRepository.save(menteqe);

        Region region = new Region();
        region.setName(dto.getRegion());
        regionRepository.save(region);

        Anbar anbar = new Anbar();
        anbar.setName(dto.getAnbar());
        anbarRepository.save(anbar);

        Tedarukcu tedarukcu = new Tedarukcu();
        tedarukcu.setName(dto.getTedarukcu());
        tedarukcuRepository.save(tedarukcu);

        return purchaseRepository.save(purchase);
    }
}
