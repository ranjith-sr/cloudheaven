package com.cloudheaven.booking.repo;

import com.cloudheaven.booking.model.property.Property;
import com.cloudheaven.booking.model.user.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TravelerRepo extends JpaRepository<Traveler , Long> {

    @Query("SELECT traveler.wishList FROM Traveler traveler WHERE traveler.userId = :userId")
    List<Property> findWishListByUserId(Long userId);

    int countByEmail(String email);
}
