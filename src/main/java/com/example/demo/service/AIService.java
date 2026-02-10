package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String askAI(String question) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = Map.of(
                    "contents", List.of(
                            Map.of(
                                    "parts", List.of(
                                            Map.of("text", question)
                                    )
                            )
                    )
            );

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

            Map<String, Object> responseBody = response.getBody();

            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");

            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");

            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

            String text = parts.get(0).get("text").toString();

            return text.split("\\s+")[0];

        } catch (Exception e) {
            return null;
        }
    }
}
