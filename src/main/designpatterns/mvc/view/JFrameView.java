package designpatterns.mvc.view;

import designpatterns.mvc.controller.Controller;
import designpatterns.mvc.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Almaz on 05.08.2015.
 */
public class JFrameView extends JFrame implements View, ActionListener{
    private static final String DEFAULT_TITLE = "MVC Pattern";
    private static final int DEFAULT_FIELD_SIZE = 10;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 480;

    protected Model model;
    protected LoginListener loginListener;

    protected JButton okButton;
    protected JTextField loginField;
    protected JPasswordField passwordField;



    public JFrameView(Model model) {
        super(DEFAULT_TITLE);
        this.model = model;

        init();
    }

    private void init(){
        this.okButton = new JButton("Login!");
        this.loginField = new JTextField(DEFAULT_FIELD_SIZE);
        this.passwordField = new JPasswordField(DEFAULT_FIELD_SIZE);

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LAST_LINE_END;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(100, 0, 0, 10);
        constraints.fill = GridBagConstraints.NONE;
        add(new Label("Login"), constraints);

        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(100, 0, 0, 0);
        constraints.fill = GridBagConstraints.NONE;
        add(loginField, constraints);

        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.fill = GridBagConstraints.NONE;
        add(new Label("Password"), constraints);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.NONE;
        add(passwordField, constraints);

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.weightx = 1;
        constraints.weighty = 100;
        constraints.fill = GridBagConstraints.NONE;
        add(okButton, constraints);

        okButton.addActionListener(this);


        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String login = loginField.getText();
        String password = new String(passwordField.getPassword());

        fireLoginEvent(new LoginEvent(login, password));
    }
    @Override
    public void fireLoginEvent(LoginEvent event){
        if(this.loginListener != null){
            this.loginListener.loginPerformed(event);
        }
    }
    @Override
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
    @Override
    public LoginListener getLoginListener() {
        return loginListener;
    }
    @Override
    public void setModel(Model model) {
        this.model = model;
    }
    @Override
    public Model getModel() {
        return this.model;
    }


}
