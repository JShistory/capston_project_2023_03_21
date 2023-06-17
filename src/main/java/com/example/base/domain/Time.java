package com.example.base.domain;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class Time {
    @Id @GeneratedValue
    private Long id;
    @OneToOne
    private Patient patient;

    private Long correctionTime;
    private Long wearingTime;
    private Long correctionDay;
    private Long wearingDay;
}
