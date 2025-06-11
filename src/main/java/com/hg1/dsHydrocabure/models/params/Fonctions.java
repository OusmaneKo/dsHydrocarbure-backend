package com.hg1.dsHydrocabure.models.params;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@Table(name = "fonctions")
public class Fonctions {
    @Id
    private String Code;
    private String libelle;
    private Boolean etat;
    private String module;
    private int ordre;
}
