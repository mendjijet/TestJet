package com.jet.TestJet.dtos;

import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.model.ListingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingDto {


    private String id;

    private Dealer dealer;

    private String vehicle;

    private double price;

    private Date createdAt;

    private ListingStatus state;
}
