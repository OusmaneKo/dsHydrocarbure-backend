package com.hg1.dsHydrocabure.models.cores;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ForeignKey;
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
@Table(name = "fiche_interventions")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE fiche_interventions set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class FicheInterventions extends AbstractEntity {

    private LocalDate dateEntree;
    private LocalDate dateSortie;
    private String description;
    private String statut; // EN_COURS, TERMINE

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "vhle.fk", value = ConstraintMode.NO_CONSTRAINT), name = "vehiculeId", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Vehicule vehicule;

    @OneToMany(mappedBy = "ficheIntervention")
    private List<Pieces> piecesUtilisees;

    @OneToOne(mappedBy = "ficheIntervention", cascade = CascadeType.ALL)
    private Factures factures;
}
