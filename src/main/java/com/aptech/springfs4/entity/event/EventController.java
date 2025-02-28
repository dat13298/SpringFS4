package com.aptech.springfs4.entity.event;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public String events(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventService.findAllEvents(pageable);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Event eventDelete = eventService.findEventById(id);

        if (eventDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        eventService.deleteEvent(eventDelete);
        return "redirect:/events";
    }

    @GetMapping("/add-event")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @PostMapping("/save-event")
    public String saveEvent(@ModelAttribute("event") @Valid Event event,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addEvent";
        }
        if(eventService.findEventById(event.getId()) != null) {
            result.rejectValue("id", "duplicate", "Event already exists");
            return "addEvent";
        }

        eventService.save(event);
        return "redirect:/events";
    }
}
