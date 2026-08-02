package org.test.canema.dto.response;

import java.util.List;

public record MovieWithShowtimeResponse(long Id, String title, String description, Integer durationMinutes, String imageURL, List<ShowtimeResponse> ShowtimeResponse) {
}
