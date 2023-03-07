package com.jet.TestJet.exception;

public class DealerNotFoundException extends RuntimeException {
    public DealerNotFoundException(String dealerId) {
        super(String.format("Dealer with id %s not found", dealerId));
    }
}
