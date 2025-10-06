package com.agrarco.agrovestapi.Controller;

import com.agrarco.agrovestapi.DTO.PurchaseRequestDTO;
import com.agrarco.agrovestapi.DTO.PurchaseResponseDTO;
import com.agrarco.agrovestapi.DTO.UserResponseDTO;
import com.agrarco.agrovestapi.ScalesEntity.*;
import com.agrarco.agrovestapi.ScalesRepository.*;
import com.agrarco.agrovestapi.ScalesEntity.Tedarukcu;
import com.agrarco.agrovestapi.Security.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;
    private final LotRepository lotRepository;
    private final MenteqeRepository menteqeRepository;
    private final RegionRepository regionRepository;
    private final userRepository userRepository;
    private final AnbarRepository anbarRepository;
    private final TedarukcuRepository tedarukcuRepository;
    private final JwtUtil jwtUtil;

    public PurchaseController(PurchaseRepository purchaseRepository,
                              LotRepository lotRepository,
                              MenteqeRepository menteqeRepository,
                              RegionRepository regionRepository, userRepository userRepository,
                              AnbarRepository anbarRepository,
                              TedarukcuRepository tedarukcuRepository, JwtUtil jwtUtil) {
        this.purchaseRepository = purchaseRepository;
        this.lotRepository = lotRepository;
        this.menteqeRepository = menteqeRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
        this.anbarRepository = anbarRepository;
        this.tedarukcuRepository = tedarukcuRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<PurchaseResponseDTO> getPurchases() {
        return purchaseRepository.findAll().stream()
                .map(PurchaseResponseDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchase(@PathVariable Long id) {
        purchaseRepository.deleteById(id);
    }
    @GetMapping("/current")
    public UserResponseDTO getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new UserResponseDTO("Anonymous", "Anonymous");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        if (username == null) {
            return new UserResponseDTO("Anonymous", "Anonymous");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new UserResponseDTO(username, username);
        }

        String name = (user.getName() != null && !user.getName().isEmpty()) ? user.getName() : username;
        return new UserResponseDTO(username, name);
    }





    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Purchase updatePurchaseById(@PathVariable Long id,
                                       @RequestBody PurchaseRequestDTO dto) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));

        Menteqe menteqe = menteqeRepository.findFirstByName(dto.getMenteqe().trim())
                .orElseGet(() -> {
                    Menteqe m = new Menteqe();
                    m.setName(dto.getMenteqe().trim());
                    return menteqeRepository.save(m);
                });
        purchase.setMenteqe(menteqe);

        if (purchase.getLot() == null) {
            Lot lot = new Lot();
            lot = lotRepository.save(lot);
            lot.setNumber(generateLot(menteqe, lot));
            lotRepository.save(lot);
            purchase.setLot(lot);
        }

        Region region = regionRepository.findFirstByName(dto.getRegion().trim())
                .orElseGet(() -> {
                    Region r = new Region();
                    r.setName(dto.getRegion().trim());
                    return regionRepository.save(r);
                });
        purchase.setRegion(region);

        Anbar anbar = anbarRepository.findFirstByName(dto.getAnbar().trim())
                .orElseGet(() -> {
                    Anbar a = new Anbar();
                    a.setName(dto.getAnbar().trim());
                    return anbarRepository.save(a);
                });
        purchase.setAnbar(anbar);

        Tedarukcu tedarukcu = tedarukcuRepository.findFirstByName(dto.getTedarukcu().trim())
                .orElseGet(() -> {
                    Tedarukcu t = new Tedarukcu();
                    t.setName(dto.getTedarukcu().trim());
                    return tedarukcuRepository.save(t);
                });
        purchase.setTedarukcu(tedarukcu);

        purchase.setNeqliyyatNomresi(dto.getNeqliyyatNomresi());
        purchase.setKiseSayi(dto.getKiseSayi());
        purchase.setBirKiseninCekisi(dto.getBirKiseninCekisi());
        purchase.setBirPaletinCekisi(dto.getBirPaletinCekisi());
        purchase.setPaletSayi(dto.getPaletSayi());
        purchase.setDoluCeki(dto.getDoluCeki());
        purchase.setBosCeki(dto.getBosCeki());
        purchase.setNetCeki(dto.getNetCeki());
        purchase.setQeyd(dto.getQeyd().trim());
        purchase.setDoluTarix(dto.getDoluTarix());
        purchase.setBosTarix(dto.getBosTarix());

        return purchaseRepository.save(purchase);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase createPurchase(@RequestBody PurchaseRequestDTO dto) {
        Purchase purchase = new Purchase();
        purchase.setNeqliyyatNomresi(dto.getNeqliyyatNomresi());

        String menteqeName = dto.getMenteqe().trim();
        Menteqe menteqe = menteqeRepository.findFirstByName(menteqeName)
                .orElseGet(() -> {
                    Menteqe newMenteqe = new Menteqe();
                    newMenteqe.setName(menteqeName);
                    return menteqeRepository.save(newMenteqe);
                });


        Lot lot = new Lot();
        lot.setNumber("TEMP");
        lot = lotRepository.save(lot);

        String lotNumber = generateLot(menteqe, lot);
        lot.setNumber(lotNumber);
        lotRepository.save(lot);

        purchase.setLot(lot);

        String regionName = dto.getRegion().trim();
        Region region = regionRepository.findFirstByName(regionName)
                .orElseGet(() -> {
                    Region newRegion = new Region();
                    newRegion.setName(regionName);
                    return regionRepository.save(newRegion);
                });

        String anbarName = dto.getAnbar().trim();
        Anbar anbar = anbarRepository.findFirstByName(anbarName)
                .orElseGet(() -> {
                    Anbar newAnbar = new Anbar();
                    newAnbar.setName(anbarName);
                    return anbarRepository.save(newAnbar);
                });

        String supplierName = dto.getTedarukcu().trim();
        Tedarukcu tedarukcu = tedarukcuRepository.findFirstByName(supplierName)
                .orElseGet(() -> {
                    Tedarukcu newSupplier = new Tedarukcu();
                    newSupplier.setName(supplierName);
                    return tedarukcuRepository.save(newSupplier);
                });

        purchase.setTedarukcu(tedarukcu);
        purchase.setAnbar(anbar);
        purchase.setRegion(region);
        purchase.setMenteqe(menteqe);

        purchase.setKiseSayi(dto.getKiseSayi());
        purchase.setBirKiseninCekisi(dto.getBirKiseninCekisi());
        purchase.setBirPaletinCekisi(dto.getBirPaletinCekisi());
        purchase.setPaletSayi(dto.getPaletSayi());
        purchase.setDoluCeki(dto.getDoluCeki());
        purchase.setBosCeki(dto.getBosCeki());
        purchase.setNetCeki(dto.getNetCeki());
        purchase.setQeyd(dto.getQeyd().trim());
        purchase.setDoluTarix(dto.getDoluTarix());
        purchase.setBosTarix(dto.getBosTarix());
        purchase.setLoggedInUser(dto.getLoggedInUser());

        return purchaseRepository.save(purchase);
    }

    private String generateLot(Menteqe menteqe, Lot lot) {
        String prefix = "FA-";
        String code = switch (menteqe.getId().intValue()) {
            case 3 -> "DZ";
            case 69 -> "LZ";
            case 70 -> "OZ";
            case 71 -> "YFEZ";
            case 72 -> "YTZ";
            default -> "UNK";
        };
        long id = lot.getId();
        int year = LocalDateTime.now().getYear();
        return prefix + code + "-" + id + "/" + year;
    }


}

