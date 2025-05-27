package com.hg1.dsHydrocabure.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable, Comparable<AbstractEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    private String cBy;

    @LastModifiedBy
    private String mBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date mDate;

    @Column
    private int etat;

    @Override
    public int compareTo(AbstractEntity abstractEntity) {
        if (cDate == null || abstractEntity.cDate == null) {
            return 0;
        }
        return cDate.compareTo(abstractEntity.getCDate());
    }

    @PrePersist
    public void beforePersist() {
        this.etat = 1;
    }

    @PreRemove
    public void beforeDelete() {
        this.etat = 0;
    }

}
