package com.hg1.dsHydrocabure.models.params;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "modules")
public class Modules {
    @Id
    private String code;
    private String libelle;
    private Boolean etat;
    private int ordre;
}
