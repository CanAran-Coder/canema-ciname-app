package org.test.canema.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @ElementCollection
    @CollectionTable(
            name = "movie_show_times",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    private List<LocalDateTime> showTime;
    private Integer durationMinutes;
    private String hallName;
    private Integer totalSeats;
    private Long price;
    private String imageURL;
    @Version
    private Long version;

}
