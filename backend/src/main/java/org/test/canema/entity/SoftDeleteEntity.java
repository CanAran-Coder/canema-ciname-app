package org.test.canema.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class SoftDeleteEntity extends BaseEntity {
    @Column(name ="is_deleted",nullable = false,columnDefinition = " boolean default false")
    private boolean isDeleted = false;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
