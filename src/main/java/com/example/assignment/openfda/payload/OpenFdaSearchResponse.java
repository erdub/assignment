package com.example.assignment.openfda.payload;

import java.util.List;
import java.util.Map;

public record OpenFdaSearchResponse(Map<String, Object> meta, List<Map<String, Object>> results) {
}
