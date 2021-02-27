package br.com.wwwgomes.hrpayroll.entities;

import java.io.Serializable;

public class Worker implements Serializable {

    private Long id;
    private String name;
    private Double dailyIncome;

    public Worker() {
    }

    public Worker(Long id, String name, Double dailyIncome) {
        this.id = id;
        this.name = name;
        this.dailyIncome = dailyIncome;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getDailyIncome() {
        return dailyIncome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Worker worker = (Worker) obj;

        return id.equals(worker.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
