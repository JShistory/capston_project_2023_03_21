package com.example.base.initData;


import com.example.base.domain.Patient;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class NotProd {
    LocalDateTime localDateTime;
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            Patient patient1 = createPatient("김길동","1999-03-29","남자","010-1111-2222",10L,5L,10L,2L);
            em.persist(patient1);

            Patient patient2 = createPatient("홍길동","2001-04-29","남자","010-1111-2222",100L,50L,20L,10L);
            em.persist(patient2);

            Patient patient3 = createPatient("이주성","2011-07-21","남자","010-1111-2222",10L,2L,100L,5L);
            em.persist(patient3);

            Patient patient4 = createPatient("김길성","1967-02-22","남자","010-1111-2222",10L,1L,100L,20L);
            em.persist(patient4);

        }

        private static Patient createPatient(String patientName, String birthday,String gender, String guardianPhoneNumber, Long correctionTime, Long wearingTime, Long correctionDay, Long wearingDay) {
            Patient patient = new Patient();
            patient.setPatientName(patientName);
            patient.setBirthday(birthday);
            patient.setGuardianPhoneNumber(guardianPhoneNumber);
            patient.setCorrectionDay(correctionDay);
            patient.setCorrectionTime(correctionTime);
            patient.setWearingTime(wearingTime);
            patient.setWearingDay(wearingDay);
            patient.setGender(gender);
            return patient;
        }
    }
}

