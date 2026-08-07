package org.test.canema.dto.response;

import java.util.List;

public record GeminiResponse(List<Candidate> candidates) {
    public record Candidate(Content content) {}
    public record Content(List<Part> parts) {}
    public record Part(String text) {}

    public String getFirstCandidateText() {
        if (candidates != null && !candidates.isEmpty()) {
            var parts = candidates.get(0).content().parts();
            if (parts != null && !parts.isEmpty()) {
                return parts.get(0).text();
            }
        }
        return "Gemini'den yanıt alınamadı.";
    }
}