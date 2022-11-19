package br.com.method.the.gamer.core.api.repository;

import br.com.method.the.gamer.core.api.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
