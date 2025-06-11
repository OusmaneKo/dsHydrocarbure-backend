package com.hg1.dsHydrocabure.models.cores;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicule")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE vehicule set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class Vehicule extends AbstractEntity {

    private String marque;
    private String model;
    private String annee;
    private String immatriculation;
    private Long clientId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "clt_fk", value = ConstraintMode.NO_CONSTRAINT), name = "clientId", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Clients clients;

    @OneToMany(mappedBy = "vehicule")
    private List<RendezVous> rendezVousList;

}
