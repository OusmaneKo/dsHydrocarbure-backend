package com.hg1.dsHydrocabure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users_groups_modules")
@AllArgsConstructor
@NoArgsConstructor
public class UsersGroupsModuls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long groupid;
    private String module;
}
