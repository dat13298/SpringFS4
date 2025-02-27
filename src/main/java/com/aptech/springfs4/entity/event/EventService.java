package com.aptech.springfs4.entity.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Page<Event> findAllEvents(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "date"));
        return eventRepository.findAll(pageRequest);
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public void save(Event event) {
        eventRepository.save(event);
    }
}
