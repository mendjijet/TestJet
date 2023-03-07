package com.jet.TestJet.repositories;

import com.jet.TestJet.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, String> {
    List<Dealer> findAll();

    Optional<Dealer> findById(String id);
}
