package com.agrarco.agrovestapi.DTO;

import com.agrarco.agrovestapi.ScalesEntity.Purchase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Long id;
    private String loggedInUser;
    private String neqliyyatNomresi;
    private String lotNomresi;
    private String menteqe;
    private String regionBag;
    private String anbar;
    private String tedarukcu;
    private int kiseSayi;
    private double birKiseninCekisi;
    private Integer paletSayi;
    private Double birPaletinCekisi;
    private double doluCeki;
    private double bosCeki;
    private double netCeki;
    private String qeyd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime doluTarix;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime bosTarix;
    public PurchaseResponseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.loggedInUser = purchase.getLoggedInUser().trim();
        this.neqliyyatNomresi = purchase.getNeqliyyatNomresi().trim();
        this.lotNomresi = purchase.getLot() != null ? purchase.getLot().getNumber().trim() : null;
        this.menteqe = purchase.getMenteqe() != null ? purchase.getMenteqe().getName().trim() : null;
        this.regionBag = purchase.getRegion() != null ? purchase.getRegion().getName().trim() : null;
        this.anbar = purchase.getAnbar() != null ? purchase.getAnbar().getName().trim() : null;
        this.tedarukcu = purchase.getTedarukcu() != null ? purchase.getTedarukcu().getName().trim() : null;

        this.kiseSayi = purchase.getKiseSayi();
        this.birKiseninCekisi = purchase.getBirKiseninCekisi();
        this.paletSayi = purchase.getPaletSayi();
        this.birPaletinCekisi = purchase.getBirPaletinCekisi();
        this.doluCeki = purchase.getDoluCeki();
        this.bosCeki = purchase.getBosCeki();
        this.netCeki = purchase.getNetCeki();
        this.qeyd = purchase.getQeyd().trim();
        this.doluTarix = purchase.getDoluTarix();
        this.bosTarix = purchase.getBosTarix();
    }


}