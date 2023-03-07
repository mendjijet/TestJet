package com.jet.TestJet.exception;

public class TierLimitExceededException extends RuntimeException {
    public TierLimitExceededException(String dealerId) {
        super(String.format("Dealer with id %s has exceeded their tier limit", dealerId));
    }
}
