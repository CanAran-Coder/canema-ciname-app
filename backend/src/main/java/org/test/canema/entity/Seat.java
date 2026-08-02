package org.test.canema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "seat_number")
    private Integer seatNumber;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

}
