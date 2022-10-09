package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SubjectController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // NOTE: @MockBean is a spring class. Not a Mockito class
    @MockBean
    private SubjectService subjectService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @SneakyThrows
    public void createSubject() {
        when(subjectService.createSubject(any())).thenReturn(1L);

        SubjectDto dto = new SubjectDto().setName("Matematika");
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(
                        post("/api/subjects")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", Matchers.equalTo(1)));

        verify(subjectService, times(1)).createSubject(any());
    }
}
