package com.neetry.platform.iam.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 13:05
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AggregateRoot {

    private static final Logger logger = LoggerFactory.getLogger(AggregateRoot.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true, columnDefinition = "BIGINT")
    private Long id;

    @CreatedDate
    protected Instant createdAt;

    @LastModifiedDate
    protected Instant lastModifiedAt;

    @Transient
    private final Collection<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot() {
    }

    @DomainEvents
    Collection<DomainEvent> domainEvents() {
        return Collections.unmodifiableCollection(domainEvents);
    }

    @AfterDomainEventPublication
    void clearEvents() {
        domainEvents.clear();
    }

    protected void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
        logger.debug("Added DomainEvent - {}", domainEvent);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(final Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("createdAt", createdAt)
                .append("lastModifiedAt", lastModifiedAt)
                .append("domainEvents", domainEvents)
                .toString();
    }
}

