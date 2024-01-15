package com.example.assignment.openfda.domain;

import com.example.assignment.openfda.payload.OpenFdaSearchRequest;
import com.example.assignment.openfda.payload.OpenFdaSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenFdaQueryController {

    private final OpenFdaQueryService openFdaQueryService;

    @Operation(summary = "Search OpenFDA for drug record applications details")
    @PostMapping("/openfda/search")
    public OpenFdaSearchResponse queryOpenFdaApi(@RequestBody @Valid OpenFdaSearchRequest request) {
        return openFdaQueryService.findByManufacturerNameAndOptionalBrandName(request);
    }
}
