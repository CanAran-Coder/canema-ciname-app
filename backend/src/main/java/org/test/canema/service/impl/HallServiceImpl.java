package org.test.canema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.test.canema.dto.request.HallRequest;
import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;
import org.test.canema.repository.HallRepository;
import org.test.canema.service.HallService;


@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    @PreAuthorize("permitAll")
    @Cacheable(value = "halls",key = "#hall")
    @Override
    public HallResponse getHall(String hall) {
        Hall response = hallRepository.findHallByName(hall);
        HallResponse dto = new HallResponse();
        dto.setSeats_per_row(response.getSeatsPerRow());
        dto.setTotal_rows(response.getTotalRows());


        return dto;
    }
}
