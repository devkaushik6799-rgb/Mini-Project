package com.medicare;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MediCareApiIntegrationTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;

    @Test
    void healthEndpointReturnsUp() throws Exception {
        mvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void medicineCatalogueIsSeeded() throws Exception {
        mvc.perform(get("/api/medicines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void chatbotReturnsSafePlatformHelp() throws Exception {
        mvc.perform(post("/api/chat").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reply").isNotEmpty());
    }

    @Test
    void orderRejectsPrescriptionOnlyMedicine() throws Exception {
        String medicines = mvc.perform(get("/api/medicines")).andReturn().getResponse().getContentAsString();
        JsonNode list = mapper.readTree(medicines);
        long prescriptionMedicineId = -1;
        for (JsonNode item : list) {
            if (item.path("prescriptionRequired").asBoolean()) {
                prescriptionMedicineId = item.path("id").asLong();
                break;
            }
        }
        org.junit.jupiter.api.Assertions.assertTrue(prescriptionMedicineId > 0);
        String request = "{\"customerName\":\"Test User\",\"customerEmail\":\"test@example.com\",\"items\":[{\"medicineId\":" + prescriptionMedicineId + ",\"quantity\":1}]}";
        mvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(org.hamcrest.Matchers.containsString("Prescription review required")));
    }
}
