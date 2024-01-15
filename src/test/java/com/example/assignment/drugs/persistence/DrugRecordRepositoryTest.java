package com.example.assignment.drugs.persistence;

import com.example.assignment.drugs.persistence.model.DrugRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DrugRecordRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldSaveDrugRecord() throws Exception {
        final DrugRecord record = DrugRecord.builder()
                .id(1L)
                .manufacturerName(randomAlphabetic(8))
                .substanceName(randomAlphabetic(8))
                .productNumbers(List.of(new Random().nextLong()))
                .build();

        final String json = mapper.writeValueAsString(record);

        this.mockMvc.perform(post("/drugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindById() throws Exception {
        final Long id = 123L;

        final DrugRecord record = DrugRecord.builder()
                .id(id)
                .manufacturerName(randomAlphabetic(8))
                .substanceName(randomAlphabetic(8))
                .productNumbers(List.of(new Random().nextLong()))
                .build();

        final String json = mapper.writeValueAsString(record);

        this.mockMvc.perform(post("/drugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/drugs/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("drugs/" + id)));
    }

    @Test
    void shouldFindByManufacturerName() throws Exception {
        final Long id = 234L;
        final String manufacturerName = "manufacturerName";

        final DrugRecord record = DrugRecord.builder()
                .id(id)
                .manufacturerName(manufacturerName)
                .substanceName(randomAlphabetic(8))
                .productNumbers(List.of(new Random().nextLong()))
                .build();

        final String json = mapper.writeValueAsString(record);

        this.mockMvc.perform(post("/drugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/drugs/search/manufacturer-name")
                        .param("name", manufacturerName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("drugs/" + id)));
    }

    @Test
    void shouldFindByProductNumber() throws Exception {
        final Long id = 345L;
        final Long productNumber = 123L;
        final DrugRecord record = DrugRecord.builder()
                .id(id)
                .manufacturerName(randomAlphabetic(8))
                .substanceName(randomAlphabetic(8))
                .productNumbers(List.of(productNumber))
                .build();

        final String json = mapper.writeValueAsString(record);

        this.mockMvc.perform(post("/drugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/drugs/search/product-number")
                        .param("number", String.valueOf(productNumber)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("drugs/" + id)));
    }
}
