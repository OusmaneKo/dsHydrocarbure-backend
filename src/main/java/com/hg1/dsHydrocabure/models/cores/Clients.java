package com.hg1.dsHydrocabure.models.cores;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import com.hg1.dsHydrocabure.models.pojos.Personnes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE clients set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class Clients extends AbstractEntity {
    @Embedded
    private Personnes personnes;

    @OneToMany(mappedBy = "clients")
    private List<Vehicule> vehicules;
}
