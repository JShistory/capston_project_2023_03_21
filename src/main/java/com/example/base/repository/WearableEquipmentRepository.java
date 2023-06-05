package com.example.base.repository;


import com.example.base.domain.WearableEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WearableEquipmentRepository extends JpaRepository<WearableEquipment, Long> {
}
