package br.com.wwwgomes.hrworker.resources;

import br.com.wwwgomes.hrworker.entities.Worker;
import br.com.wwwgomes.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerResource.class);

    @Value("${test.config}")
    private String testConfig;

    private final Environment environment;
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerResource(WorkerRepository workerRepository, Environment environment) {
        this.workerRepository = workerRepository;
        this.environment = environment;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> workers = workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        LOGGER.info("PORT = " + environment.getProperty("local.server.port"));

        return workerRepository.findById(id).
                map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs() {
        LOGGER.info(String.format("CONFIG %s", testConfig));

        return ResponseEntity.noContent().build();
    }
}
