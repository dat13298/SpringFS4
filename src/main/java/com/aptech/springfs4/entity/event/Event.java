package com.aptech.springfs4.entity.event;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    @NotBlank(message = "Event name cannot be empty")
    private String name;

    @Column(nullable = false)
    @Future(message = "The event date must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Venue cannot be empty")
    private String venue;

    @Column(nullable = false)
    @Min(value = 0, message = "Seats available must be at least 0")
    private Integer seatsAvailable;
}
