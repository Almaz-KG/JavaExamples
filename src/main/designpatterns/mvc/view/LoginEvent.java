package designpatterns.mvc.view;

/**
 * Created by Almaz on 06.08.2015.
 */
public class LoginEvent {
    private String login;
    private String password;

    public LoginEvent(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
