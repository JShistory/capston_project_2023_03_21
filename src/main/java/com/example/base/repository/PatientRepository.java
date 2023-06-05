package com.example.base.repository;

import com.example.base.domain.Patient;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class PatientRepository{
    private final EntityManager em;

    public void save(Patient patient){
        em.persist(patient);
    }

    public Patient findOne(Long id){
        return em.find(Patient.class, id);
    }

    public void delete(Long id){
        Patient patient = findOne(id);
        em.remove(patient);
    }

    public List<Patient> findAll(PatientSearch patientSearch){
        //language=JPAQL
        String jpql = "select p From Patient p";
        boolean isFirstCondition = true;

        if (StringUtils.hasText(patientSearch.getPatient())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " p.patientName like '%'||:patientName||'%' ORDER BY p.patientName ASC, p.birthday ASC";

        }

        if (StringUtils.hasText(patientSearch.getBirthday())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " p.birthday like '%'||:birthday||";

        }

        TypedQuery<Patient> query = em.createQuery(jpql, Patient.class)
                .setMaxResults(1000); //최대 1000건
        if (StringUtils.hasText(patientSearch.getPatient())) {
            query = query.setParameter("patientName", patientSearch.getPatient());
        }
        if (StringUtils.hasText(patientSearch.getBirthday())) {
            query = query.setParameter("birthday", patientSearch.getBirthday());
        }

        return query.getResultList();
    }
}
