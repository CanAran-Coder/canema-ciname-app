package org.test.canema.service;

import org.test.canema.dto.request.ShowTimeRequest;
import org.test.canema.entity.Showtime;

public interface ShowTimeService {
    void addShowTime(ShowTimeRequest request);
}
