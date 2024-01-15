package com.example.assignment.openfda.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record OpenFdaSearchRequest(@NotNull @Valid SearchQuery query, @NotNull @Valid Page page) {
}
