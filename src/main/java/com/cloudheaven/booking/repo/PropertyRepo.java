package com.cloudheaven.booking.repo;

import com.cloudheaven.booking.model.property.Property;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepo extends JpaRepository<Property , Long> {

    public List<Property> findByHostUserId(Long userId);

    public Optional<Property> findByPropertyIdAndHostUserId(Long propertyId , Long userId);

    @Transactional
    public void deleteByPropertyIdAndHostUserId(Long propertyId , Long userId);
}
