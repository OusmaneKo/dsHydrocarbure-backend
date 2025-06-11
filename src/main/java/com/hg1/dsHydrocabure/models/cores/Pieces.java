package com.hg1.dsHydrocabure.models.cores;


import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pieces")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE pieces set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class Pieces extends AbstractEntity {
    private String reference;
    private String nom;
    private Double prixUnitaire;

    private Integer quantiteDispo;
    private Integer seuilReapprovisionnement;
    private Long ficheInterventionId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "fi_fk", value = ConstraintMode.NO_CONSTRAINT), name = "ficheInterventionId", referencedColumnName = "id",
            updatable = false, insertable = false)
    private FicheInterventions ficheIntervention;


}
