package org.test.canema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer capacity;
    @Column(name = "total_rows")
    private Integer totalRows;
    @Column(name = "seats_per_row")
    private Integer seatsPerRow;

}
