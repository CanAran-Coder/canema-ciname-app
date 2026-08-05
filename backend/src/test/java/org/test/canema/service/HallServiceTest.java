package org.test.canema.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;
import org.test.canema.factory.HallTestDataFactory;
import org.test.canema.mappers.HallMapper;
import org.test.canema.repository.HallRepository;
import org.test.canema.service.impl.HallServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HallServiceTest {

    @Mock
    private HallRepository hallRepository;

    @Mock
    private HallMapper hallMapper;

    @InjectMocks
    private HallServiceImpl hallService;





    @Test
    public void getHallByName(){
        Hall mockHall = HallTestDataFactory.createDefaultHall();
        HallResponse mockHallResponse = HallTestDataFactory.createDefaultHallResponse();

        when(hallRepository.findHallByName("Hall1")).thenReturn(mockHall);
        when(hallMapper.toResponse(mockHall)).thenReturn(mockHallResponse);

        HallResponse response = hallService.getHall("Hall1");

        assertNotNull(response);
        assertEquals(10,response.getTotal_rows());
        assertEquals(5,response.getSeats_per_row());

        verify(hallRepository,times(1)).findHallByName("Hall1");
        verify(hallMapper,times(1)).toResponse(mockHall);

    }

}
