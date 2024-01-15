package com.example.assignment.drugs.persistence;

import com.example.assignment.drugs.persistence.model.DrugRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "drugs", path = "drugs")
public interface DrugRecordRepository extends JpaRepository<DrugRecord, Integer> {

    @RestResource(path = "manufacturer-name", rel = "manufacturerName")
    List<DrugRecord> findByManufacturerName(String name, Pageable p);

    @RestResource(path = "substance-name", rel = "substanceName")
    List<DrugRecord> findBySubstanceName(String name, Pageable p);

    @RestResource(path = "product-number", rel = "productNumber")
    List<DrugRecord> findByProductNumbers(Long number, Pageable p);
}
