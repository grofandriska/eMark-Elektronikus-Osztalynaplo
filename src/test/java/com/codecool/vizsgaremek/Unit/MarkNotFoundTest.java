package com.codecool.vizsgaremek.Unit;

import com.codecool.vizsgaremek.controller.MarkController;
import com.codecool.vizsgaremek.exception.types.MarkException;
import com.codecool.vizsgaremek.service.MarkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MarkController.class)
class MarkNotFoundTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarkService markService;

    @Test
    void returns404WithErrorBodyWhenMarkNotFound() throws Exception {
        when(markService.getMarkById(99L)).thenThrow(new MarkException(99L));

        mockMvc.perform(get("/mark/getMark/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message", containsString("99")));
    }
}

