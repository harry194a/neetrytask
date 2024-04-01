package com.neetry.platform.book.config.persistance;

import com.neetry.platform.book.domain.entity.basic.Audit;
import com.neetry.platform.book.domain.entity.basic.AuditableBaseEntity;
import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 20:37
 */
public class AuditListener extends AuditingEntityListener {

    @PrePersist
    public void setCreatedOn(AuditableBaseEntity auditable) {
        Audit audit = auditable.getAudit();
        audit.setCreatedOn(LocalDateTime.now());
        audit.setStatus(ModelStatus.ACTIVE);
        setUpdatedOn(auditable);
    }

    @PreUpdate
    public void setUpdatedOn(AuditableBaseEntity auditable) {
        Audit audit = auditable.getAudit();
        audit.setUpdatedOn(LocalDateTime.now());

        if (audit.getStatus().equals(ModelStatus.DELETED)) {
            audit.setDeletedOn(LocalDateTime.now());
        } else {
            audit.setDeletedOn(null);
        }
    }
}