package com.aptech.springfs4.entity.event;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.*;

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
    private String name;
    @Column(nullable = false)
    @Future
    private Date date;
    @Column(nullable = false, length = 50)
    private String venue;
    @Column(nullable = false)
    @Min(value = 0)
    private Integer seatsAvailable;
}
