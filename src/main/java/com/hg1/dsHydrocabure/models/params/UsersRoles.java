package com.hg1.dsHydrocabure.models.params;

import com.hg1.dsHydrocabure.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_roles")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "update users_roles set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class UsersRoles extends AbstractEntity {
    private Date dateDebut;
    private Date dateFin;
    private Long usersId;
    private Long rolesId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "usr_fk", value = ConstraintMode.NO_CONSTRAINT), name = "usersId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "rle_fk", value = ConstraintMode.NO_CONSTRAINT), name = "rolesId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Roles roles;
}
