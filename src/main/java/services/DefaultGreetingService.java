package services;


import interceptors.Logged;

public class DefaultGreetingService implements GreetingService {

    @Logged
    @Override
    public String greet() {
        return "Hello from DEFAULT";
    }
}

