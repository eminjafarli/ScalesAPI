package com.agrarco.agrovestapi.AgrovestEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "dbo", name = "_Reference89255")
public class Reference89255 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "_IDRRef")
    private UUID id;

    @Column(name = "_Description", length = 100)
    private String menteqe;
}
