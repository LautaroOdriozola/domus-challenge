package domus.challenge;

import domus.challenge.controller.MoviesController;
import domus.challenge.service.DirectorsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;


import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MoviesController.class)
public class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public DirectorsService directorsService() {
            return mock(DirectorsService.class);
        }
    }

    @Autowired
    private DirectorsService directorsService;

    @Test
    @DisplayName("Should return 200 OK and list of directors when threshold is valid")
    void testGetDirectorsAboveThreshold_validThreshold() throws Exception {
        Map<String, List<String>> mockResponse = Map.of("directors", List.of("Martin Scorsese", "Woody Allen"));
        when(directorsService.getDirectorsAboveThreshold(3)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/directors")
                        .param("threshold", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.directors").isArray())
                .andExpect(jsonPath("$.directors[0]").value("Martin Scorsese"))
                .andExpect(jsonPath("$.directors[1]").value("Woody Allen"));
    }

    @Test
    @DisplayName("Should return 400 Bad Request when threshold is missing")
    void testGetDirectorsAboveThreshold_missingThreshold() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/directors"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Threshold is required and must be a number"));
    }
}
