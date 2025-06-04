package com.hg1.dsHydrocabure.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usersGroups")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE usersGroups set etat = 0, where id=?", check = ResultCheckStyle.COUNT)
public class UsersGroups extends AbstractEntity{
    private String nom;
    private String nature;
    private String type;
}
