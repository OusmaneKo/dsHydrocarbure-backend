package com.hg1.dsHydrocabure.models;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.processing.SQL;

import java.time.LocalDate;


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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "clt_fk", value = ConstraintMode.NO_CONSTRAINT), name = "clientId", referencedColumnName = "id",
            updatable = false, insertable = false)
    private Client client;

}
