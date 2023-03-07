package com.jet.TestJet.repositories;

import com.jet.TestJet.InfrastructureTestConfig;
import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.populator.DealerPopulator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {InfrastructureTestConfig.class})
@DirtiesContext
public class DealerRepositoryIntegrationTest {

    @Autowired
    private DealerRepository  dealerRepository;

    @AfterEach
    void setupDatabase(){
        this.dealerRepository.deleteAll();
    }

    @Test
    void given_dealer_to_save_then_dealer_persisted_to_database(){
        //GIVEN
        Dealer dealer = DealerPopulator.createDealer("mendjijet", 10);

        //WHEN
        final Dealer persistedDealer = dealerRepository.save(dealer);
        Optional<Dealer> optionalDealer = dealerRepository.findById(dealer.getId());

        //THEN
        Assertions.assertEquals(dealer,persistedDealer);
        Assertions.assertTrue(optionalDealer.isPresent());

    }
}
