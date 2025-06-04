package com.hg1.dsHydrocabure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.Id;

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
    private String immatriculation;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "vehicule")
    private List<RendezVous> rendezVousList;
}
