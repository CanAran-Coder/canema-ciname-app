package org.test.canema.factory;

import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;

public class HallTestDataFactory {

    public static Hall createDefaultHall(){
        return Hall.builder().name("Hall1")
                .seatsPerRow(5)
                .totalRows(10)
                .id(1L)
                .build();
    }

    public static HallResponse createDefaultHallResponse(){
        return HallResponse.builder()
                .seats_per_row(5)
                .total_rows(10)
                .build();
    }

    public static Hall createHallWithName(String Name){
        return Hall.builder().name(Name)
                .seatsPerRow(5)
                .totalRows(10)
                .id(1L)
                .build();
    }
}
