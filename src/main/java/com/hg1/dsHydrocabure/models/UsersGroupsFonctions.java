package com.hg1.dsHydrocabure.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_groups_fonctions")
public class UsersGroupsFonctions {
    @Id
    private Long id;
    private Long groupid;
    private String fonction;

}
