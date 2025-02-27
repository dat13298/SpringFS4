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
    private Event event;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50, unique = true)
    @NotNull
    @Email
    @Size(max = 50)
    private String email;
}
