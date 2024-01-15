package com.example.assignment.drugs.persistence.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugRecord {

    @Id
    private Long id;

    @NotBlank
    private String manufacturerName;

    @NotBlank
    private String substanceName;

    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_number", joinColumns = @JoinColumn(name = "drug_record_id"))
    @NotNull
    private List<Long> productNumbers = new ArrayList<>();
}


