package services;

import interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class FancyGreetingService implements GreetingService {

    @Logged
    @Override
    public String greet() {
        return "Hello from FANCY";
    }
}

