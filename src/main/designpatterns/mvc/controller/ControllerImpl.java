package designpatterns.mvc.controller;

import designpatterns.mvc.model.Model;
import designpatterns.mvc.view.LoginEvent;
import designpatterns.mvc.view.LoginListener;
import designpatterns.mvc.view.View;

/**
 * Created by Almaz on 05.08.2015.
 */
public class ControllerImpl implements Controller, LoginListener{
    protected View view;
    protected Model model;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loginPerformed(LoginEvent event) {
        System.out.println("Login event performed");
        System.out.println("Login: " + event.getLogin());
        System.out.println("Password: " + event.getPassword());
    }
}
