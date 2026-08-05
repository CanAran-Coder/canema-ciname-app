package org.test.canema.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallResponse {
    Integer total_rows;
    Integer seats_per_row;
}
