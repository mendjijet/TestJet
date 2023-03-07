package com.jet.TestJet.mappers;

import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.model.Dealer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerMapperTest {

    @Test
    void given_dealer_id_should_map_dealerDto_id(){
        //GIVEN
        Dealer dealer = Dealer.builder().id("1l").build();

        //WHEN
        final DealerDto dealerDto = DealerMapper.INSTANCE.dealerToDealerDto(dealer);

        //THEN
        Assertions.assertEquals(dealer.getId(),dealerDto.getId());
    }

    @Test
    void given_dealer_name_should_map_dealerDto_name(){
        //GIVEN
        Dealer dealer = Dealer.builder().name("name").build();

        //WHEN
        final DealerDto dealerDto = DealerMapper.INSTANCE.dealerToDealerDto(dealer);

        //THEN
        Assertions.assertEquals(dealer.getName(),dealerDto.getName());
    }

    @Test
    void given_dealer_tierLimit_should_map_dealerDto_tierLimit(){
        //GIVEN
        Dealer dealer = Dealer.builder().tierLimit(10).build();

        //WHEN
        final DealerDto dealerDto = DealerMapper.INSTANCE.dealerToDealerDto(dealer);

        //THEN
        Assertions.assertEquals(dealer.getTierLimit(),dealerDto.getTierLimit());
    }

    @Test
    void given_dealerDto_id_should_map_dealer_id(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().id("1l").build();

        //WHEN
        final Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        //THEN
        Assertions.assertEquals(dealer.getId(),dealerDto.getId());
    }

    @Test
    void given_dealerDto_name_should_map_dealer_name(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().name("name").build();

        //WHEN
        final Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        //THEN
        Assertions.assertEquals(dealer.getName(),dealerDto.getName());
    }

    @Test
    void given_dealerDto_tierLimit_should_map_dealer_tierLimit(){
        //GIVEN
        DealerDto dealerDto = DealerDto.builder().tierLimit(10).build();

        //WHEN
        final Dealer dealer = DealerMapper.INSTANCE.dealerDtoToDealer(dealerDto);

        //THEN
        Assertions.assertEquals(dealer.getTierLimit(),dealerDto.getTierLimit());
    }
}
