package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.canema.entity.Ticket;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
