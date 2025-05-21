package services;

import interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class VerySpecialGreetingService extends DefaultGreetingService{

    @Logged
    @Override
    public String greet(){
        return "Very Special Greeting";
    }
}
