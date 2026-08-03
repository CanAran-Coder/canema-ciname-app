package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.canema.dto.request.HallRequest;
import org.test.canema.dto.response.HallResponse;
import org.test.canema.entity.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall,Long> {
    public Hall findHallByName(String name);
}
