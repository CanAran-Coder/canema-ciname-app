package org.test.canema.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public  abstract class BaseEntity {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
