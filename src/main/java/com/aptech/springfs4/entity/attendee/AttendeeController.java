package com.aptech.springfs4.entity.attendee;

import com.aptech.springfs4.entity.event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;
    private final EventService eventService;

    @GetMapping
    public String attendees(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Attendee> attendees = attendeeService.findAll(pageable);
        model.addAttribute("attendees", attendees);
        return "attendees";
    }

    @GetMapping("/add-new")
    public String addNewAttendee(Model model) {
        model.addAttribute("attendee", new Attendee());
        model.addAttribute("events", eventService.findAll());
        return "addNewAttendee";
    }

    @PostMapping("/save-attendee")
    public String saveAttendee(@ModelAttribute("attendee") @Valid Attendee attendee,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("events", eventService.findAll());
            return "addNewAttendee";
        }

        if (attendeeService.attendeeExists(attendee.getEmail())) {
            result.rejectValue("email", "error.attendee", "Email already exists");
            model.addAttribute("events", eventService.findAll());
            return "addNewAttendee";
        }

        attendeeService.save(attendee);
        return "redirect:/attendees";
    }



}
