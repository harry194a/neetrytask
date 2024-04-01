package com.neetry.platform.iam.domain.base;

import jakarta.persistence.Embedded;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 12:33
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
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
