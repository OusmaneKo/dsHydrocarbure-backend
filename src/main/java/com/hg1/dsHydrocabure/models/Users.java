package com.hg1.dsHydrocabure.models;

import com.hg1.dsHydrocabure.models.pojos.Personnes;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;


@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE users set etat=0 where id=?", check = ResultCheckStyle.COUNT)
public class Users extends AbstractEntity{
    private String login;
    private String pwd;

    @Embedded
    private Personnes personnes;
    private String status;
    private long groupeId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(name = "grp_fk", value = ConstraintMode.NO_CONSTRAINT), name = "groupeId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private UsersGroups group;


}
