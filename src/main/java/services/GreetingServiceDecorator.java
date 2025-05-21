package services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class GreetingServiceDecorator implements GreetingService {

    @Inject
    @Delegate
    private GreetingService delegate;

    @Override
    public String greet() {
        String greeting = delegate.greet();
        System.out.println("Decorated Greeting: " + greeting);
        return greeting;
    }
}
