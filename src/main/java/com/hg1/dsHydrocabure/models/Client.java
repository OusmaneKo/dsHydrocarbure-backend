package com.hg1.dsHydrocabure.models;

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
@Table(name = "client")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE client set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class Client extends AbstractEntity{
    @Embedded
    private Personnes personnes;

    @OneToMany(mappedBy = "client")
    private List<Vehicule> vehicules;
}
