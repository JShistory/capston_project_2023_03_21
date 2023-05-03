package com.example.doctor;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.base.domain.Patient;
import com.example.base.repository.PatientRepository;
import com.example.base.domain.WearablePatient;
import com.example.base.repository.WearableRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserApplicationTests {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WearableRepository wearableRepository;
    @Test
    void contextLoads() {
        //test
    }

    @Test
    void removed(){
        //given
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setWearableId(2L);
        patient1.setCreateDate(LocalDateTime.now());
        patient1.setModifyDate(LocalDateTime.now());
        patient1.setUsername("김재성");
        patient1.setGender("M");
        //when
        patientRepository.save(patient1);
        //then
        assertThat(patientRepository.findById(patient1.getId()));
    }
    @Test
    void test(){

        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setWearableId(2L);
        patient1.setCreateDate(LocalDateTime.now());
        patient1.setModifyDate(LocalDateTime.now());
        patient1.setUsername("김재성");
        patient1.setGender("M");
        //when
        patientRepository.save(patient1);
        WearablePatient wearablePatient1 = new WearablePatient();
        wearablePatient1.setId(2L);
        wearablePatient1.setFromPatient(patient1);
        System.out.println("wearable = " + wearablePatient1);
        wearableRepository.save(wearablePatient1);
    }
}

