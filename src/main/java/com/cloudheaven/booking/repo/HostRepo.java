package com.cloudheaven.booking.repo;

import com.cloudheaven.booking.model.user.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepo extends JpaRepository<Host,Long> {

}
