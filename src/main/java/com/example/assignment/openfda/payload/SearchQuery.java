package com.example.assignment.openfda.payload;

import jakarta.validation.constraints.NotEmpty;

public record SearchQuery(@NotEmpty String manufacturerName, String brandName) {
}
