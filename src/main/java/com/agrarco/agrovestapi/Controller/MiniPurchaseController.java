package com.agrarco.agrovestapi.Controller;

import com.agrarco.agrovestapi.DTO.MiniPurchaseRequestDTO;
import com.agrarco.agrovestapi.DTO.MiniPurchaseResponseDTO;
import com.agrarco.agrovestapi.DTO.PurchaseResponseDTO;
import com.agrarco.agrovestapi.ScalesEntity.miniPurchase;
import com.agrarco.agrovestapi.ScalesEntity.Purchase;
import com.agrarco.agrovestapi.ScalesRepository.MiniPurchaseRepository;
import com.agrarco.agrovestapi.ScalesRepository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/minipurchase")
public class MiniPurchaseController {

    @Autowired
    private MiniPurchaseRepository miniPurchaseRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping
    public List<MiniPurchaseResponseDTO> getMiniPurchases() {
        return miniPurchaseRepository.findAll().stream()
                .map(MiniPurchaseResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public MiniPurchaseResponseDTO addMiniPurchase(@RequestBody MiniPurchaseRequestDTO dto) {
        Purchase parentPurchase = purchaseRepository.findById(dto.getPurchaseId())
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        miniPurchase mini = new miniPurchase();
        mini.setPurchase(parentPurchase);
        mini.setCeki(dto.getCeki());
        mini.setKiseSayi(dto.getKiseSayi());
        mini.setBirKiseninCekisi(dto.getBirKiseninCekisi());
        mini.setPaletSayi(dto.getPaletSayi());
        mini.setBirPaletinSayi(dto.getBirPaletinSayi());
        mini.setTarix(dto.getTarix() != null ? dto.getTarix() : LocalDateTime.now());

        mini = miniPurchaseRepository.save(mini);
        System.out.println("Received JSON Tarix = " + mini.getTarix());
        return new MiniPurchaseResponseDTO(mini);
    }

    @GetMapping("/byPurchase/{purchaseId}")
    public List<MiniPurchaseResponseDTO> getByPurchase(@PathVariable Long purchaseId) {
        return miniPurchaseRepository.findByPurchaseId(purchaseId)
                .stream()
                .map(MiniPurchaseResponseDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMiniPurchase(@PathVariable Long id) {
        miniPurchaseRepository.deleteById(id);
    }
}
