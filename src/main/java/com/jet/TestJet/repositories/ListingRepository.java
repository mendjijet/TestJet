package com.jet.TestJet.repositories;

import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.model.Listing;
import com.jet.TestJet.model.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {
    List<Listing> findByDealerId(String dealerId);

    List<Listing> findByDealerIdAndState(String dealerId, ListingStatus status);

    Listing findFirstByDealerIdAndStateOrderByCreatedAtAsc(String dealerId, ListingStatus status);

    int countByDealerIdAndState(String dealerId, ListingStatus status);

    Optional<Listing> findById(String id);
}
