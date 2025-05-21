package usecases;

import services.AsyncService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AsyncBean implements Serializable {

    @EJB
    private AsyncService asyncService;

    private String message;

    public void startCalculation() {
        message = "Calculation started. Please check back later.";
        asyncService.calculateAsync();
    }

    public String getMessage() {
        return message;
    }
}

