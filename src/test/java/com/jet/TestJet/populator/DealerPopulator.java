package com.jet.TestJet.populator;

import com.jet.TestJet.model.Dealer;

public class DealerPopulator {

    public static Dealer createDealer(String name, int tierLimit) {
        return Dealer.builder().name(name).tierLimit(tierLimit).build();
    }
}
