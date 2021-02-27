package br.com.wwwgomes.hrpayroll.service;

import br.com.wwwgomes.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(Long workerId, Integer days) {
        return new Payment("Bob", 200.0, days);
    }
}
