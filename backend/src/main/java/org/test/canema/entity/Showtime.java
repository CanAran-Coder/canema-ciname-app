package org.test.canema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {
    @Id
    private Long id;

    @Column(name = "start_time",nullable = false)
    private LocalDateTime startTime;
    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "hall_id",nullable = false)
    private Hall hall;
}
