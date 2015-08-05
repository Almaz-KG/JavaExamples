package designpatterns.mvc;

import designpatterns.mvc.controller.Controller;
import designpatterns.mvc.controller.ControllerImpl;
import designpatterns.mvc.model.Model;
import designpatterns.mvc.model.ModelImpl;
import designpatterns.mvc.view.JFrameView;
import designpatterns.mvc.view.View;

import javax.swing.*;

/**
 * Created by Almaz on 05.08.2015.
 */
public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Model model = new ModelImpl();
            View view = new JFrameView(model);

            ControllerImpl controller = new ControllerImpl(model, view);

            view.setLoginListener(controller);

        });
    }
}
