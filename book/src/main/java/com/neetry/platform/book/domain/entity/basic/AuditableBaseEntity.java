package com.neetry.platform.book.domain.entity.basic;

import com.neetry.platform.book.config.persistance.AuditListener;
import jakarta.persistence.Embedded;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 16:18
 */
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class AuditableBaseEntity extends AbstractDomainEntity {

    @Embedded
    private Audit audit = new Audit();

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(final Audit audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("audit", audit)
                .toString();
    }
}

