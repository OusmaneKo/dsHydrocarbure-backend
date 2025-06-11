package com.hg1.dsHydrocabure.models.cores;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import com.hg1.dsHydrocabure.models.params.Users;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rendez_vous")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE rendez_vous set etat = 1 Where id=?", check = ResultCheckStyle.COUNT)
public class RendezVous extends AbstractEntity {

    private LocalDateTime dateHeure;
    private String motif;
    private String statut;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "clt_fk", value = ConstraintMode.NO_CONSTRAINT), name = "clientId", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Clients clients;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "vhle.fk", value = ConstraintMode.NO_CONSTRAINT), name = "vehiculeId", referencedColumnName = "id",
    updatable = false, insertable = false)
    private Vehicule vehicule;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "tech_fk", value = ConstraintMode.NO_CONSTRAINT), name = "usersId", referencedColumnName = "id",
    updatable = false, insertable = false)
    private Users technicienAttribue;


}
