package com.agrarco.agrovestapi.ScalesEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "miniPurchases", schema = "dbo")
public class miniPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime Tarix;
    private double Ceki;
    private int KiseSayi;
    private double BirKiseninCekisi;
    private int PaletSayi;
    private double BirPaletinSayi;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase; // reference to the parent Purchase

}
