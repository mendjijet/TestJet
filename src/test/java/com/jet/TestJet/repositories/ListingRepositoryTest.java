package com.jet.TestJet.repositories;

import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.model.Listing;
import com.jet.TestJet.model.ListingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ListingRepositoryTest {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByDealerId() {
        Dealer dealer = new Dealer();
        dealer.setName("Test Dealer");
        entityManager.persist(dealer);

        Listing listing1 = new Listing();
        listing1.setDealer(dealer);
        listing1.setState(ListingStatus.DRAFT);
        listing1.setVehicle("Test Vehicle 1");
        listing1.setPrice(10000.0);
        listing1.setCreatedAt(new Date());
        entityManager.persist(listing1);

        Listing listing2 = new Listing();
        listing2.setDealer(dealer);
        listing2.setState(ListingStatus.DRAFT);
        listing2.setVehicle("Test Vehicle 2");
        listing2.setPrice(20000.0);
        listing2.setCreatedAt(new Date());
        entityManager.persist(listing2);

        List<Listing> listings = listingRepository.findByDealerId(dealer.getId());

        assertThat(listings).isNotEmpty();
        assertThat(listings.size()).isEqualTo(2);
    }
}
