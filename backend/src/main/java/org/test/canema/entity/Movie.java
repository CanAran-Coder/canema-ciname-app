package org.test.canema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String title;
    @Column(length = 1000,nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer durationMinutes;
    @Column(nullable = false)
    private String imageURL;
    @Version
    private Long version;

}
