package com.cloudheaven.booking.repo;

import com.cloudheaven.booking.model.user.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepo extends JpaRepository<Traveler , Long> {

}
