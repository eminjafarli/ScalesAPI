package com.agrarco.agrovestapi.ScalesEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Purchases", schema = "dbo")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "logged_in_user")
    private String loggedInUser;

    @JsonProperty("loggedInUser")
    public String getLoggedInUser() {
        return loggedInUser;
    }

    private String neqliyyatNomresi;
    private int kiseSayi;
    private double birKiseninCekisi;
    @Column(name = "PaletSayi")
    private Integer paletSayi;
    @Column(name = "BirPaletinCekisi")
    private Double birPaletinCekisi;
    private double doluCeki;
    private double bosCeki;
    private double netCeki;
    private String qeyd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime doluTarix;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime bosTarix;

    @ManyToOne
    @JoinColumn(name = "LotId")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "MenteqeId")
    private Menteqe menteqe;

    @ManyToOne
    @JoinColumn(name = "RegionId")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "AnbarId")
    private Anbar anbar;

    @ManyToOne
    @JoinColumn(name = "TedarukcuId")
    private Tedarukcu tedarukcu;
}