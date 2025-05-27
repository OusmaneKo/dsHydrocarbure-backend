package com.hg1.dsHydrocabure.models.pojos;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Date;


@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Personnes {
    private String civilite;
    private String nom;
    private String prenom;
    private String tel1;
    private String tel2;
    private String email;
    private String genre;
    private Date dateNaiss;
}
