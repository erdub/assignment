package com.example.assignment.openfda.domain;

import com.example.assignment.openfda.payload.OpenFdaSearchRequest;
import com.example.assignment.openfda.payload.Page;
import com.example.assignment.openfda.payload.SearchQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled("these tests use real API - created for development purposes, should be replaced by mocks")
class OpenFdaQueryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturn200() throws Exception {
        OpenFdaSearchRequest searchRequest = new OpenFdaSearchRequest(new SearchQuery("Padagis Israel Pharmaceuticals Ltd", null), new Page(0L, 1L));
        String json = mapper.writeValueAsString(searchRequest);

        this.mockMvc.perform(post("/openfda/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404() throws Exception {
        OpenFdaSearchRequest searchRequest = new OpenFdaSearchRequest(new SearchQuery("evilcorp", null), new Page(0L, 1L));
        String json = mapper.writeValueAsString(searchRequest);

        this.mockMvc.perform(post("/openfda/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}