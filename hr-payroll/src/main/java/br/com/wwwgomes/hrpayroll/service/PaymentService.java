package br.com.wwwgomes.hrpayroll.service;

import br.com.wwwgomes.hrpayroll.entities.Payment;
import br.com.wwwgomes.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class PaymentService {

    @Value(value = "${hr-worker.host}")
    private String workerHost;

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment getPayment(Long workerId, Integer days) {
        var uriVariables = new HashMap<String, String>();
        uriVariables.put("id", String.valueOf(workerId));

        var worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
        assert worker != null;
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
