package com.jet.TestJet.services;

import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.exception.DealerNotFoundException;
import com.jet.TestJet.mappers.DealerMapper;
import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.repositories.DealerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DealerServiceTest {
    @Mock
    private DealerRepository dealerRepository;

    @InjectMocks
    private DealerServiceImpl dealerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createDealerDto_ValidInput_ReturnsDealerDto() {
        String dealerDtoName = "Test Dealer";
        int dealerDtotierLimit = 10;
        DealerDto dealerDto = DealerDto.builder().name(dealerDtoName).tierLimit(dealerDtotierLimit).build();

        when(dealerRepository.save(any(Dealer.class))).thenReturn(DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto));

        DealerDto createdDealerDto = dealerService.createDealer(dealerDto);

        assertEquals(dealerDto, createdDealerDto);
        verify(dealerRepository, times(1)).save(any(Dealer.class));
    }

    @Test
    public void getDealerDtoById_ValidId_ReturnsDealerDto() throws DealerNotFoundException {
        String dealerDtoId = "1L";
        String dealerDtoName = "Test Dealer";
        int tierLimit = 10;
        DealerDto dealerDto = DealerDto.builder().name(dealerDtoName).tierLimit(tierLimit).build();
        dealerDto.setId(dealerDtoId);

        when(dealerRepository.findById(dealerDtoId)).thenReturn(Optional.of(DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto)));

        DealerDto foundDealerDto = dealerService.getDealerById(dealerDtoId);

        assertEquals(dealerDto, foundDealerDto);
        verify(dealerRepository, times(1)).findById(dealerDtoId);
    }

    @Test
    public void getDealerDtoById_InvalidId_ThrowsException() {
        String dealerDtoId = "1L";
        when(dealerRepository.findById(dealerDtoId)).thenReturn(Optional.empty());

        assertThrows(DealerNotFoundException.class, () -> dealerService.getDealerById(dealerDtoId));
        verify(dealerRepository, times(1)).findById(dealerDtoId);
    }

    @Test
    public void getAllDealerDtos_ReturnsListOfDealerDtos() {
        List<DealerDto> dealerDtos = new ArrayList<>();
        dealerDtos.add(DealerDto.builder().name("Dealer 1").tierLimit(5).build());
        dealerDtos.add(DealerDto.builder().name("Dealer 2").tierLimit(10).build());

        when(dealerRepository.findAll()).thenReturn(dealerDtos.stream().map(DealerMapper.INSTANCE::dealerDtoToDealer).collect(Collectors.toList()));

        List<DealerDto> foundDealerDtos = dealerService.getAllDealers();

        assertEquals(dealerDtos, foundDealerDtos);
        verify(dealerRepository, times(1)).findAll();
    }

    @Test
    public void updateDealerDto_ValidInput_ReturnsUpdatedDealerDto() throws DealerNotFoundException {
        String dealerDtoId = "1L";
        String oldDealerDtoName = "Test Dealer";
        int oldTierLimit = 10;
        DealerDto oldDealerDto = DealerDto.builder().name(oldDealerDtoName).tierLimit(oldTierLimit).build();
        oldDealerDto.setId(dealerDtoId);
        String newDealerDtoName = "New Dealer Name";
        int newTierLimit = 5;

        DealerDto updatedDealerDto = DealerDto.builder().name(newDealerDtoName).tierLimit(newTierLimit).build();
        updatedDealerDto.setId(dealerDtoId);

        when(dealerRepository.findById(dealerDtoId)).thenReturn(Optional.of(DealerMapper.INSTANCE.dealerDtoToDealer(oldDealerDto)));
        when(dealerRepository.save(any(Dealer.class))).thenReturn(DealerMapper.INSTANCE.dealerDtoToDealer(updatedDealerDto));

        DealerDto result = dealerService.updateDealer(updatedDealerDto);

        assertEquals(dealerDtoId, result.getId());
        assertEquals(newDealerDtoName, result.getName());
        verify(dealerRepository, times(0)).save(DealerMapper.INSTANCE.dealerDtoToDealer(oldDealerDto));
    }

    @Test
    public void updateDealerDto_NonexistentDealerDto_ThrowsDealerNotFoundException() throws DealerNotFoundException {
        String dealerDtoId = "1L";
        String newDealerDtoName = "New Dealer Name";
        DealerDto newDealerDto = DealerDto.builder().build();
        newDealerDto.setName(newDealerDtoName);
        when(dealerRepository.findById(dealerDtoId)).thenReturn(Optional.empty());

        assertThrows(DealerNotFoundException.class, () -> dealerService.updateDealer(newDealerDto));

        verify(dealerRepository, times(0)).findById(dealerDtoId);
        verify(dealerRepository, never()).save(any(Dealer.class));
    }
}
