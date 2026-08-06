package org.test.canema.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.canema.dto.request.ShowTimeRequest;
import org.test.canema.service.ShowTimeService;

@RestController
@RequestMapping("/api/showTime")
@RequiredArgsConstructor
public class ShowTimeController {

    private final ShowTimeService showTimeService;

    @PostMapping("/addShowTime")
    public void addShowTime(@RequestBody ShowTimeRequest request){
        showTimeService.addShowTime(request);
    }
}
