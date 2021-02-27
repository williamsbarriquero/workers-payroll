package br.com.wwwgomes.hrworker.resources;

import br.com.wwwgomes.hrworker.entities.Worker;
import br.com.wwwgomes.hrworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerResource(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        var workers = workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        return workerRepository.findById(id).
                map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
