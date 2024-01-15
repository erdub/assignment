package com.example.assignment.openfda.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Page(@NotNull @Min(0) Long skip, @NotNull @Min(1) @Max(99) Long limit) {
}
