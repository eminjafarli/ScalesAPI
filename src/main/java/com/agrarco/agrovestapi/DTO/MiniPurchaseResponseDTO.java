package com.agrarco.agrovestapi.DTO;

import com.agrarco.agrovestapi.ScalesEntity.miniPurchase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MiniPurchaseResponseDTO {
    private Long id;
    private Long purchaseId;
    private double ceki;
    private int kiseSayi;
    private double birKiseninCekisi;
    private int paletSayi;
    private double birPaletinSayi;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime tarix;

    public MiniPurchaseResponseDTO(miniPurchase miniPurchase) {
        this.id = miniPurchase.getId();
        this.purchaseId = miniPurchase.getPurchase().getId();
        this.ceki = miniPurchase.getCeki();
        this.kiseSayi = miniPurchase.getKiseSayi();
        this.birKiseninCekisi = miniPurchase.getBirKiseninCekisi();
        this.paletSayi = miniPurchase.getPaletSayi();
        this.birPaletinSayi = miniPurchase.getBirPaletinSayi();
        this.tarix = miniPurchase.getTarix();
    }
}
