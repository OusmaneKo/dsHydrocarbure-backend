package com.hg1.dsHydrocabure.models;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "factures_lignes")
@Where(clause = " etat <> 0")
@SQLDelete(sql = "update factures_lignes set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class FacturesLignes {

    private Date dateFac;
    private Double montant;
    private Double remise;
    private Double total;
    private Double taux;
    private String numBon;
    private Long factureId;
    private Long fInterventionId;
    private Long vehiculeId;
    private Long pieceId;
    private Long techId;


    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @jakarta.persistence.ForeignKey(name = "fac_fk", value = ConstraintMode.NO_CONSTRAINT), name = "factureId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Factures facture;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @jakarta.persistence.ForeignKey(name = "fInt_fk", value = ConstraintMode.NO_CONSTRAINT), name = "fInterventionId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private FicheInterventions ficheInterventions;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @jakarta.persistence.ForeignKey(name = "vhl_fk", value = ConstraintMode.NO_CONSTRAINT), name = "vehiculeId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Vehicule vehicule;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @jakarta.persistence.ForeignKey(name = "piece_fk", value = ConstraintMode.NO_CONSTRAINT), name = "pieceId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Pieces pieces;


    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @jakarta.persistence.ForeignKey(name = "tech_fk", value = ConstraintMode.NO_CONSTRAINT), name = "techId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Users user;

}
