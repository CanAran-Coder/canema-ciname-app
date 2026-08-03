package org.test.canema.service;


import org.springframework.web.bind.annotation.RequestBody;
import org.test.canema.dto.request.HallRequest;
import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;

public interface HallService {
    public HallResponse getHall(String hall);
}
