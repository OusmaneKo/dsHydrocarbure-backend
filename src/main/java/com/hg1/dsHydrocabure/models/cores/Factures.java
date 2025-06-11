package com.hg1.dsHydrocabure.models.cores;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "factures")
@Where(clause = "etat <> 0" )
@SQLDelete(sql = "UPDATE factures set etat = 0 where id=? ", check = ResultCheckStyle.COUNT)
public class Factures extends AbstractEntity {

    private String numero;
    private LocalDate dateFac;
    private Double montant;
    private Double remiseFac;
    private Double total;
    private Long clientId;

    @OneToOne
    private FicheInterventions ficheIntervention;

    @OneToMany(mappedBy = "facture")
    private List<FacturesLignes> lignes;

}
