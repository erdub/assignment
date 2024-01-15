package com.example.assignment.openfda.domain;

import com.example.assignment.exception.ApiLimitExceededException;
import com.example.assignment.exception.NotFoundException;
import com.example.assignment.openfda.payload.OpenFdaSearchRequest;
import com.example.assignment.openfda.payload.OpenFdaSearchResponse;
import com.example.assignment.openfda.payload.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class OpenFdaQueryService {

    private final WebClient client;

    public OpenFdaSearchResponse findByManufacturerNameAndOptionalBrandName(OpenFdaSearchRequest request) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.add("search", buildQuery(request.query()));
        queryParams.add("limit", String.valueOf(request.page().limit()));
        queryParams.add("skip", String.valueOf(request.page().skip()));

        return client.get().uri(uriBuilder -> uriBuilder
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .onStatus(status -> status.value() == 404, response -> {throw new NotFoundException();})
                .onStatus(HttpStatusCode::is4xxClientError, response -> {throw new ApiLimitExceededException();})
                .bodyToMono(OpenFdaSearchResponse.class)
                .block();
    }

    private String buildQuery(SearchQuery query) {
        StringBuilder sb = new StringBuilder("openfda.manufacturer_name.exact:");
        sb.append(query.manufacturerName().replace(" ", "+"));
        if (StringUtils.isNotBlank(query.brandName())) {
            sb.append("openfda.brand_name:").append(query.brandName().replace(" ", "+"));
        }
        return sb.toString();
    }
}
