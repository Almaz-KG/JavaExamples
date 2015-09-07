package art.java.javamail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectDialog extends JDialog {
    private static String[] protocolType = {"pop3", "imap"};

    private JButton connectButton;
    private JButton cancelButton;
    private JComboBox protocolTypeComboBox;
    private JPasswordField passwordField;
    private JTextField serverTextField;
    private JTextField usernameTextField;
    private JTextField smtpServerTextField;


    public ConnectDialog(JFrame parent) {
        super(parent, true);
        initComponents();
        setTitle("Connect");
    }
    private void initComponents() {
        JPanel connectionSettingsLabel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JLabel protocolLabel = new JLabel();
        JLabel serverLabel = new JLabel();
        JLabel usernameLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel smtpServerLabel = new JLabel();
        protocolTypeComboBox = new JComboBox(protocolType);
        serverTextField = new JTextField();
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        smtpServerTextField = new JTextField();

        connectButton = new JButton();
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectAction();
            }
        });
        cancelButton = new JButton();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelAction();
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(connectionSettingsLabel);
        connectionSettingsLabel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Connection settings"));

        protocolLabel.setText("Protocol");
        serverLabel.setText("Server");
        usernameLabel.setText("Username");
        passwordLabel.setText("Password");
        smtpServerLabel.setText("SMTP Server");

        GroupLayout jPanel2Layout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(protocolLabel)
                                        .addComponent(serverLabel)
                                        .addComponent(usernameLabel)
                                        .addComponent(passwordLabel)
                                        .addComponent(smtpServerLabel))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(protocolTypeComboBox, 0, 235, Short.MAX_VALUE)
                                        .addComponent(serverTextField)
                                        .addComponent(usernameTextField)
                                        .addComponent(passwordField)
                                        .addComponent(smtpServerTextField))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(protocolLabel)
                                        .addComponent(protocolTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(serverLabel)
                                        .addComponent(serverTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameLabel)
                                        .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(smtpServerLabel)
                                        .addComponent(smtpServerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        protocolLabel.getAccessibleContext().setAccessibleName("Protocol");
        protocolTypeComboBox.getAccessibleContext().setAccessibleName("protocolComboBox");
        serverTextField.getAccessibleContext().setAccessibleName("serverTextField");
        usernameTextField.getAccessibleContext().setAccessibleName("usernameTextField");
        passwordField.getAccessibleContext().setAccessibleName("passworldField");
        smtpServerTextField.getAccessibleContext().setAccessibleName("smtpTextField");

        connectButton.setText("Connect");
        cancelButton.setText("Cancel");

        GroupLayout jPanel3Layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(connectButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelButton)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(connectButton)
                                        .addComponent(cancelButton))
                                .addContainerGap())
        );

        connectButton.getAccessibleContext().setAccessibleName("connectButton");
        cancelButton.getAccessibleContext().setAccessibleName("cancelButton");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mainPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(7, 7, 7)
                                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    private void connectAction() {
        if(serverTextField.getText().trim().length() < 1
                || usernameTextField.getText().trim().length() < 1
                || passwordField.getPassword().length < 1
                || smtpServerTextField.getText().trim().length() < 1){
            JOptionPane.showMessageDialog(this,
                    "One or more settings is missing",
                    "Missing setting(s)",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        dispose();
    }
    private void cancelAction() {
        System.exit(0);
    }

    public String getProtocolType() {
        return (String)protocolTypeComboBox.getSelectedItem();
    }
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    public String getServer() {
        return serverTextField.getText();
    }
    public String getUsername() {
        return usernameTextField.getText();
    }
    public String getSmtpServer() {
        return smtpServerTextField.getText();
    }
}
