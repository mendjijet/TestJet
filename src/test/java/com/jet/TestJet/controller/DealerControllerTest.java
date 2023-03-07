package com.jet.TestJet.controller;

import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.services.DealerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealerControllerTest {
    @Mock
    private DealerService dealerService;

    @InjectMocks
    private DealerController dealerController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDealerDtoById_ReturnsDealerDto_WhenIdExists() {
        String id = "1L";
        DealerDto dealerDto = DealerDto.builder().name("Test Dealer").tierLimit(5).build();
        when(dealerService.getDealerById(id)).thenReturn(dealerDto);

        ResponseEntity<DealerDto> response = dealerController.getDealerById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealerDto, response.getBody());
    }

    @Test
    public void getDealerDtoById_ReturnsNotFound_WhenIdDoesNotExist() {
        String id = "1L";
        when(dealerService.getDealerById(id)).thenReturn(null);

        ResponseEntity<DealerDto> response = dealerController.getDealerById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
