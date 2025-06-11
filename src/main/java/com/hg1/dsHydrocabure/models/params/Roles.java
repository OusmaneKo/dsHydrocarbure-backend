package com.hg1.dsHydrocabure.models.params;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE services set etat =0 where id=?", check = ResultCheckStyle.COUNT)
public class Roles extends AbstractEntity {
    private String nom;
}
