package org.test.canema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.test.canema.dto.request.HallRequest;
import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;
import org.test.canema.repository.HallRepository;
import org.test.canema.service.HallService;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallController {
    private final HallService hallService;

    @PostMapping("/getHall")
    public HallResponse getHall(@RequestBody HallRequest request){

        return  hallService.getHall(request.hallName());

    }

}
