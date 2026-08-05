package org.test.canema.mappers;

import org.mapstruct.Mapper;
import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;

@Mapper(componentModel = "spring")
public interface HallMapper {
    HallResponse toResponse(Hall hall);
}
