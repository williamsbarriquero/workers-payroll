package br.com.wwwgomes.hrworker.repositories;

import br.com.wwwgomes.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
