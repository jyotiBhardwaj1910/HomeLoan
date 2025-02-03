package com.example.home.loan;


import com.example.home.loan.Entity.CustomerEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JacksonDeserializationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCustomerEntityDeserialization() throws Exception {
        String json = "{\"id\":1,\"fname\":\"Eva\",\"lname\":\"Bhardwaj\",\"gender\":\"Female\",\"phone\":9876543219,\"email\":\"Eva@bhardwaj.com\",\"password\":\"password123\",\"salary\":50000.0,\"adhaar\":123456789012,\"panCard\":\"ABCDE1234F\",\"walletAmt\":1000.0}";

        CustomerEntity customer = objectMapper.readValue(json, CustomerEntity.class);
        assertNotNull(customer);
        assertEquals("Eva", customer.getFname());
        assertEquals("Eva@bhardwaj.com", customer.getEmail());
    }
}
