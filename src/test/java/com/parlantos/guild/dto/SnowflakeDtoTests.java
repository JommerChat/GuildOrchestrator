package com.parlantos.guild.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnowflakeDtoTests {

    private static SnowflakeDto snowflakeDto;

    public static MockWebServer mockWebServer;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        snowflakeDto = new SnowflakeDto("http://localhost:" + mockWebServer.getPort() + "/guild/data");
    }

    @Test
    public void testGetSnowflakeId() throws JsonProcessingException {
        BigInteger mockSnowflakeValue = new BigInteger("1234");
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockSnowflakeValue))
                .addHeader("Content-Type", "application/json"));
        BigInteger result = snowflakeDto.getSnowflakeId().block();
        assertEquals(mockSnowflakeValue, result);
    }

}
