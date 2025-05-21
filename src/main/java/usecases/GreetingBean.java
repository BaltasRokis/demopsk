package usecases;

import services.GreetingService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class GreetingBean {

    @Inject
    private GreetingService service;

    public String getGreeting() {
        return service.greet();
    }
}

