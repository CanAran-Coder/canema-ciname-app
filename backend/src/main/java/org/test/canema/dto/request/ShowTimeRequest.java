package org.test.canema.dto.request;

import java.util.List;

public record ShowTimeRequest(String title, String description, String imageURL, Integer durationMinutes, String hallName, Integer price,
                              List<String> showTime) {
}
