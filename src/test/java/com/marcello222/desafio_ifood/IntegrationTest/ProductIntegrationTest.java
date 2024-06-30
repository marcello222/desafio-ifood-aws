package com.marcello222.desafio_ifood.IntegrationTest;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcello222.desafio_ifood.domain.CategoryDto;
import com.marcello222.desafio_ifood.domain.ProductDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import com.marcello222.desafio_ifood.entities.product.Product;
import com.marcello222.desafio_ifood.repositories.ProductRepository;
import com.marcello222.desafio_ifood.services.aws.AwsSnsService;
import com.marcello222.desafio_ifood.template.CategoryDtoTemplate;
import com.marcello222.desafio_ifood.template.ProductDtoTemplate;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AwsSnsService awsSnsService;

    private AmazonSNS amazonSNSMock;

    @BeforeEach
    public void setup() {
        // Create a mock of AmazonSNS
        amazonSNSMock = Mockito.mock(AmazonSNS.class);

        // Create a mock PublishResult
        PublishResult mockPublishResult = new PublishResult();
        mockPublishResult.setMessageId("mockMessageId");

        // When publish is called on the mock AmazonSNS, return the mock PublishResult
        when(amazonSNSMock.publish(any(PublishRequest.class))).thenReturn(mockPublishResult);

        // Set the mock AmazonSNS in the AwsSnsService
        awsSnsService.setAmazonSNS(amazonSNSMock);

        productRepository.deleteAll();
    }

    @Test
    public void testPostAndGetProduct() throws Exception {
        CategoryDto postCategoryDto = CategoryDtoTemplate.CategoryTemplate();

        MvcResult categoryResult = mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postCategoryDto)))
                .andExpect(status().isOk())
                .andReturn();

        String categoryResponseBody = categoryResult.getResponse().getContentAsString();
        Category category = objectMapper.readValue(categoryResponseBody, Category.class);

        ProductDto postProductDto = ProductDtoTemplate.ProductTemplate();
        postProductDto.setCategoryId(category.getId()); // Set the category ID
        MvcResult postResult = mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postProductDto)))
                .andExpect(status().isOk())
                .andReturn();

        String postResponseBody = postResult.getResponse().getContentAsString();

        Product postProduct = objectMapper.readValue(postResponseBody, Product.class);

        MvcResult getResult = mockMvc.perform(get("/api/product/" + postProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String getResponseBody = getResult.getResponse().getContentAsString();
        Product getProduct = objectMapper.readValue(getResponseBody, Product.class);

        assertEquals(postProduct, getProduct);
    }

}
