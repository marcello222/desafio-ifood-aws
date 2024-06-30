package com.marcello222.desafio_ifood.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.marcello222.desafio_ifood.domain.CategoryDto;
import com.marcello222.desafio_ifood.repositories.CategoryRepository;
import com.marcello222.desafio_ifood.template.CategoryDtoTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void clearDatabase() {
        categoryRepository.deleteAll();
    }

    @Test
    public void testGetCategory() throws Exception {
        CategoryDto categoryDto = CategoryDtoTemplate.CategoryTemplate();

        MvcResult result = mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String createdCategoryId = JsonPath.parse(response).read("$.id");

        result = mockMvc.perform(get("/api/category/" + createdCategoryId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        response = result.getResponse().getContentAsString();
        CategoryDto returnedCategoryDto = objectMapper.readValue(response, CategoryDto.class);

        assertEquals(categoryDto.getTitle(), returnedCategoryDto.getTitle());
        assertEquals(categoryDto.getDescription(), returnedCategoryDto.getDescription());
        assertEquals(categoryDto.getOwnerId(), returnedCategoryDto.getOwnerId());
    }
}
