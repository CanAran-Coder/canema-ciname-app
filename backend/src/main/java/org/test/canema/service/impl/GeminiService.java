package org.test.canema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.test.canema.dto.request.GeminiRequest;
import org.test.canema.dto.response.GeminiResponse;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestClient restClient = RestClient.create();

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    public String askGemini(String prompt) {
        GeminiRequest requestBody = GeminiRequest.of(prompt);

        GeminiResponse response = restClient.post()
                .uri(apiUrl + "?key=" + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(GeminiResponse.class);

        return response != null ? response.getFirstCandidateText() : "Hata oluştu.";
    }
}