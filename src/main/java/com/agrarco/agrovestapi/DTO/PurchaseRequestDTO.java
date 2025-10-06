package com.agrarco.agrovestapi.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseRequestDTO {
    private String loggedInUser;
    private String neqliyyatNomresi;
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

    @JsonProperty("lotNomresi")
    private String lotNomresi;

    @JsonProperty("menteqe")
    private String menteqe;

    @JsonProperty("regionBag")
    private String region;

    @JsonProperty("anbar")
    private String anbar;

    @JsonProperty("tedarukcu")
    private String tedarukcu;
}
