package services;

import usecases.AsyncBean;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncService {

    @Asynchronous
    public void calculateAsync() {
        try {
            System.out.println("Async calculation started...");
            Thread.sleep(5000); // Simulate long task
            System.out.println("Async calculation done.");
        } catch (InterruptedException e) {
            System.out.println("Async calculation interrupted.");
        }
    }
}

