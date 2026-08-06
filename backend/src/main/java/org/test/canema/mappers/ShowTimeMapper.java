package org.test.canema.mappers;

import jakarta.persistence.MappedSuperclass;
import org.mapstruct.Mapper;
import org.test.canema.dto.request.ShowTimeRequest;
import org.test.canema.entity.Showtime;

@Mapper(componentModel = "spring")
public interface ShowTimeMapper {
    Showtime toShowTime(ShowTimeRequest request);
}
