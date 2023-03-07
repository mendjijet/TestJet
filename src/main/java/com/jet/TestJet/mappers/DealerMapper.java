package com.jet.TestJet.mappers;

import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.model.Dealer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DealerMapper {

    DealerMapper INSTANCE = Mappers.getMapper(DealerMapper.class);

    @BeanMapping(ignoreByDefault  = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "tierLimit", target = "tierLimit")
    DealerDto dealerToDealerDto(Dealer dealer);

    @BeanMapping(ignoreByDefault  = true)
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "tierLimit", target = "tierLimit")
    Dealer dealerDtoToDealer(DealerDto dealerDto);
}
