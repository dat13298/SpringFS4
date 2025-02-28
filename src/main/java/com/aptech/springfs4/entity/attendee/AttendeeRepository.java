package com.aptech.springfs4.entity.attendee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    boolean existsByEmail(String email);
}
