package com.example.base.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String guardianPhoneNumber;
    private Long correctionTime;
    private Long wearingTime;
    private Long correctionDay;
    private Long wearingDay;
    private int timeToWear;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<WearableEquipment> wearableEquipment;


}
