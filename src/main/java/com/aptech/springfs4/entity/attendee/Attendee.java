package com.aptech.springfs4.entity.attendee;

import com.aptech.springfs4.entity.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "attendees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull(message = "Event must be selected")
    private Event event;

    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Invalid email format")
    @Size(max = 50, min = 1, message = "Email must be between 3 and 50 characters")
    private String email;
}
