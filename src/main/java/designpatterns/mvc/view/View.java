package designpatterns.mvc.view;

import designpatterns.mvc.model.Model;

/**
 * Created by Almaz on 05.08.2015.
 */
public interface View {
    void setModel(Model model);
    Model getModel();

    void setLoginListener(LoginListener listener);
    LoginListener getLoginListener();
    void fireLoginEvent(LoginEvent event);
}
