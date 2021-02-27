package br.com.wwwgomes.hrpayroll.service;

import br.com.wwwgomes.hrpayroll.entities.Payment;
import br.com.wwwgomes.hrpayroll.entities.Worker;
import br.com.wwwgomes.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    @Autowired
    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(Long workerId, Integer days) {

        var worker = workerFeignClient.findById(workerId).getBody();

        assert worker != null;
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
