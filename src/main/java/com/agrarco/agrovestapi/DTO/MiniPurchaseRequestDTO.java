package com.agrarco.agrovestapi.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiniPurchaseRequestDTO {
    private Long purchaseId; // parent purchase ID
    private double ceki;
    private int kiseSayi;
    private double birKiseninCekisi;
    private int paletSayi;
    private double birPaletinSayi;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime tarix;
}
