package com.jet.TestJet.services;

import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.exception.DealerNotFoundException;
import com.jet.TestJet.model.Dealer;

import java.util.List;

public interface DealerService {
    DealerDto createDealer(DealerDto dealerDto);
    DealerDto getDealerById(String id) throws DealerNotFoundException;
    List<DealerDto> getAllDealers();
    DealerDto updateDealer(DealerDto dealerDto) throws DealerNotFoundException;
    void deleteDealer(String id) throws DealerNotFoundException;
}
