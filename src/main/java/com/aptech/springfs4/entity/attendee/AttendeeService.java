package com.aptech.springfs4.entity.attendee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Page<Attendee> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "id"));
        return attendeeRepository.findAll(pageable);
    }

    public boolean attendeeExists(String email) {
        return attendeeRepository.existsByEmail(email);
    }

    public void save(Attendee attendee) {
        attendeeRepository.save(attendee);
    }
}
