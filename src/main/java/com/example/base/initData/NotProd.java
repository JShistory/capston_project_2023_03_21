package com.example.base.initData;


import com.example.base.domain.Patient;
import com.example.base.domain.WearableEquipment;
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
    public void init() {
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Patient patient1 = createPatient("김길동", "1999-03-29", "남자", "010-1111-2222", 200L, 0L, 50L, 2L,2);
            em.persist(patient1);

            Patient patient2 = createPatient("홍길동", "2001-04-29", "남자", "010-1111-2222", 200L, 50L, 50L, 10L,3);
            em.persist(patient2);

            Patient patient3 = createPatient("이주성", "2011-07-21", "남자", "010-1111-2222", 200L, 2L, 50L, 5L,3);
            em.persist(patient3);

            Patient patient4 = createPatient("김길성", "1967-02-22", "남자", "010-1111-2222", 200L, 1L, 50L, 20L,4);
            em.persist(patient4);

            createTime(patient1, "202304011200","202304011300");

            createTime(patient1, "202305011200","202305011300");
            createTime(patient1, "202305011300","202305011400");
            createTime(patient1, "202305011500","202305011700");

            createTime(patient2, "202305011200","202305011300");
            createTime(patient2, "202305011300","202305011400");
            createTime(patient2, "202305011500","202305011700");

            createTime(patient1, "202305021200","202305021300");
            createTime(patient1, "202305021300","202305021400");
            createTime(patient1, "202305021500","202305021700");

            createTime(patient2, "202305021300","202305021400");

            createTime(patient1, "202305031200","202305031300");
            createTime(patient1, "202305031300","202305031400");
            createTime(patient1, "202305031500","202305031700");

            createTime(patient2, "202305031200","202305031300");
            createTime(patient2, "202305031300","202305031400");
            createTime(patient2, "202305031500","202305031700");

            createTime(patient3, "202305031200","202305031300");
            createTime(patient3, "202305031300","202305031400");
            createTime(patient3, "202305031500","202305031700");


            createTime(patient1, "202305041200","202305041300");
            createTime(patient1, "202305041300","202305041400");
            createTime(patient1, "202305041500","202305041700");

            createTime(patient1, "202305051200","202305051300");
            createTime(patient1, "202305051300","202305051400");
            createTime(patient1, "202305051500","202305051700");

            createTime(patient1, "202305061200","202305061300");
            createTime(patient1, "202305061300","202305061400");
            createTime(patient1, "202305061500","202305061700");

            createTime(patient1, "202305071200","202305071300");
            createTime(patient1, "202305071300","202305071400");
            createTime(patient1, "202305071500","202305071700");

            createTime(patient1, "202305081200","202305081300");
            createTime(patient1, "202305081300","202305081400");
            createTime(patient1, "202305081500","202305081700");

            createTime(patient1, "202305091200","202305091300");
            createTime(patient1, "202305091300","202305091400");
            createTime(patient1, "202305091500","202305091700");

            createTime(patient1, "202305101200","202305101300");
            createTime(patient1, "202305101300","202305101400");
            createTime(patient1, "202305101500","202305101700");

            createTime(patient1, "202305111200","202305111300");
            createTime(patient1, "202305111300","202305111400");
            createTime(patient1, "202305111500","202305111700");

            createTime(patient3, "202305091200","202305091300");
            createTime(patient3, "202305091300","202305091400");
            createTime(patient3, "202305091500","202305091700");

            createTime(patient3, "202305101200","202305101300");
            createTime(patient3, "202305101300","202305101400");
            createTime(patient3, "202305101500","202305101700");

            createTime(patient4, "202305111200","202305111300");
            createTime(patient4, "202305111300","202305111400");
            createTime(patient4, "202305111500","202305111700");

            createTime(patient1, "202305121200","202305121300");
            createTime(patient1, "202305121300","202305121400");
            createTime(patient1, "202305121500","202305121700");

            createTime(patient4, "202305121500","202305121700");

            createTime(patient1, "202305131200","202305131300");
            createTime(patient1, "202305131300","202305131400");
            createTime(patient1, "202305131500","202305131700");

            createTime(patient1, "202305141200","202305141300");
            createTime(patient1, "202305141300","202305141400");
            createTime(patient1, "202305141500","202305141700");

            createTime(patient1, "202305151200","202305151300");
            createTime(patient1, "202305151300","202305151400");
            createTime(patient1, "202305151500","202305151700");

            createTime(patient1, "202305161200","202305161300");
            createTime(patient1, "202305161300","202305161400");
            createTime(patient1, "202305161500","202305161700");

            createTime(patient1, "202305171200","202305171300");
            createTime(patient1, "202305171300","202305171400");
            createTime(patient1, "202305171500","202305171700");

            createTime(patient1, "202305181200","202305181300");
            createTime(patient1, "202305181300","202305181400");
            createTime(patient1, "202305181500","202305181700");

            createTime(patient1, "202305191200","202305191300");
            createTime(patient1, "202305191300","202305191400");
            createTime(patient1, "202305191500","202305191700");

            createTime(patient1, "202305201200","202305201300");
            createTime(patient1, "202305201300","202305201400");
            createTime(patient1, "202305201500","202305201700");

            createTime(patient1, "202305211200","202305211300");
            createTime(patient1, "202305211300","202305211400");
            createTime(patient1, "202305211500","202305211700");

            createTime(patient1, "202305221200","202305221300");
            createTime(patient1, "202305221300","202305221400");
            createTime(patient1, "202305221500","202305221700");

            createTime(patient1, "202305231200","202305231300");
            createTime(patient1, "202305231300","202305231400");
            createTime(patient1, "202305231500","202305231700");

            createTime(patient1, "202305241200","202305241300");
            createTime(patient1, "202305241300","202305241400");
            createTime(patient1, "202305241500","202305241700");

            createTime(patient1, "202305251200","202305251300");
            createTime(patient1, "202305251300","202305251400");
            createTime(patient1, "202305251500","202305251700");

            createTime(patient1, "202305261200","202305261300");
            createTime(patient1, "202305261300","202305261400");
            createTime(patient1, "202305261500","202305261700");

            createTime(patient1, "202305271200","202305271300");
            createTime(patient1, "202305271300","202305271400");
            createTime(patient1, "202305271500","202305271700");

            createTime(patient1, "202305281200","202305281300");
            createTime(patient1, "202305281300","202305281400");
            createTime(patient1, "202305281500","202305281700");

            createTime(patient1, "202305291200","202305291300");
            createTime(patient1, "202305291300","202305291400");
            createTime(patient1, "202305291500","202305291700");

            createTime(patient1, "202305301200","202305301300");
            createTime(patient1, "202305301300","202305301400");
            createTime(patient1, "202305301500","202305301700");

            WearableEquipment time = createTime(patient1, "202305311200", "202305311300");
            WearableEquipment time1 = createTime(patient1, "202305311300", "202305311400");
            WearableEquipment time2 = createTime(patient1, "202305311500", "202305311700");

            // jun
            WearableEquipment time3 = createTime(patient1, "202306011200", "202306011600");

            WearableEquipment time4 = createTime(patient1, "202306021200", "202306021400");
            WearableEquipment time5 = createTime(patient1, "202306021500", "202306021700");

            WearableEquipment time6 = createTime(patient1, "202306031200", "202306031300");
            WearableEquipment time7 = createTime(patient1, "202306031300", "202306031400");
            WearableEquipment time8 = createTime(patient1, "202306031500", "202306031700");

            WearableEquipment time9 = createTime(patient1, "202306041100", "202306041300");
            WearableEquipment time10 = createTime(patient1, "202306041500", "202306041600");

            WearableEquipment time11 = createTime(patient1, "202306051200", "202306051300");
            WearableEquipment time12 = createTime(patient1, "202306051300", "202306051400");
            WearableEquipment time13 = createTime(patient1, "202306051500", "202306051600");
            WearableEquipment time14 = createTime(patient1, "202306051700", "202306051800");

            WearableEquipment time15 = createTime(patient1, "202306061300", "202306061500");
            WearableEquipment time16 = createTime(patient1, "202306061600", "202306061800");

            WearableEquipment time17 = createTime(patient1, "202306071200", "202306071500");
            WearableEquipment time18 = createTime(patient1, "202306071600", "202306071800");

            WearableEquipment time19 = createTime(patient1, "202306082000", "202306082100");

            WearableEquipment time20 = createTime(patient1, "202306091200", "202306091400");

            WearableEquipment time21 = createTime(patient1, "202306101200", "202306101300");
            WearableEquipment time22 = createTime(patient1, "202306101300", "202306101400");

            WearableEquipment time23 = createTime(patient1, "202306111200", "202306111300");
            WearableEquipment time24 = createTime(patient1, "202306111300", "202306111400");
            WearableEquipment time25 = createTime(patient1, "202306111500", "202306111600");

            WearableEquipment time26 = createTime(patient1, "202306121200", "202306121500");

            WearableEquipment time27 = createTime(patient1, "202306131200", "202306131300");
            WearableEquipment time28 = createTime(patient1, "202306131300", "202306131400");
            WearableEquipment time29 = createTime(patient1, "202306131500", "202306131600");
            WearableEquipment time30 = createTime(patient1, "202306131800", "202306131900");

            WearableEquipment time31 = createTime(patient1, "202306141200", "202306141600");

            WearableEquipment time32 = createTime(patient1, "202306151200", "202306151600");

            WearableEquipment time33 = createTime(patient1, "202306161200", "202306161600");

            WearableEquipment time34 = createTime(patient1, "202306171200", "202306171600");

            WearableEquipment time35 = createTime(patient1, "202306181200", "202306181400");
            WearableEquipment time36 = createTime(patient1, "202305181600", "202305181600");

            WearableEquipment time37 = createTime(patient1, "202306191200", "202306191300");
            WearableEquipment time38 = createTime(patient1, "202306191400", "202306191700");
            WearableEquipment time39 = createTime(patient1, "202306191800", "202306191900");

            WearableEquipment time40 = createTime(patient1, "202306201200", "202306201500");
            WearableEquipment time41 = createTime(patient1, "202306201600", "202306201700");

            WearableEquipment time42 = createTime(patient1, "202306211200", "202306211400");
            WearableEquipment time43 = createTime(patient1, "202306211500", "202306211600");
            WearableEquipment time44 = createTime(patient1, "202306211700", "202306211800");

            WearableEquipment time45 = createTime(patient1, "202306221200", "202306221600");
            WearableEquipment time46 = createTime(patient1, "202306221700", "202306221800");

            WearableEquipment time47 = createTime(patient1, "202306231200", "202306231300");
            WearableEquipment time48 = createTime(patient1, "202306231300", "202306231400");
            WearableEquipment time49 = createTime(patient1, "202306231500", "202306231700");




        }

        private WearableEquipment createTime(Patient patient, String startTime, String endTime) {
            WearableEquipment wearableEquipment = new WearableEquipment();
            wearableEquipment.setStartTime(startTime);
            wearableEquipment.setEndTime(endTime);
            wearableEquipment.setPatient(patient);
            em.persist(wearableEquipment);
            return wearableEquipment;

        }

        public void dbInit2() {

        }

        private static Patient createPatient(String patientName, String birthday, String gender,
                                             String guardianPhoneNumber, Long correctionTime, Long wearingTime,
                                             Long correctionDay, Long wearingDay, int timeToWear) {
            Patient patient = new Patient();
            patient.setPatientName(patientName);
            patient.setBirthday(birthday);
            patient.setGuardianPhoneNumber(guardianPhoneNumber);
            patient.setCorrectionDay(correctionDay);
            patient.setCorrectionTime(correctionTime);
            patient.setWearingTime(wearingTime);
            patient.setWearingDay(wearingDay);
            patient.setGender(gender);
            patient.setTimeToWear(timeToWear);
            return patient;
        }
    }
}

