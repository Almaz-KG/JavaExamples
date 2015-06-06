package art.java.javamail;

import javax.mail.Address;
import javax.mail.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Almaz on 06.06.2015.
 */
public class MessageDialog extends JDialog{
    public static final int NEW = 0;
    public static final int REPLY = 1;
    public static final int FORWARD = 2;

    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextField subjectTextField;
    private JTextArea contentTextArea;
    private boolean cancelled;

    public MessageDialog(Frame parent, int type, Message message) throws Exception {
        super(parent, true);
        String to = "", subject = "", content = "";
        switch (type){
            case REPLY:
                setTitle("Reply to message");
                Address[] senders = message.getFrom();
                if(senders != null || senders.length > 0)
                    to = senders[0].toString();
                to = message.getFrom()[0].toString();

                subject = message.getSubject();
                if(subject != null && subject.length() > 0)
                    subject = "RE: " + subject;
                else
                    subject = "RE: ";
                content = "\n------------------"+
                            "REPLIED TO MESSAGE"+
                           "-------------------\n"+
                        EmailClient.getMessageContent(message);
                break;
            case FORWARD:
                setTitle("Forward message");
                subject = message.getSubject();
                if(subject != null && subject.length() > 0)
                    subject = "FWD: " + subject;
                else
                    subject = "FWD: ";

                content = "\n------------------"+
                        "FORWARDED TO MESSAGE"+
                        "-------------------\n"+
                        EmailClient.getMessageContent(message);
                break;
            default:
                setTitle("New message");
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionCancel();
            }
        });
        JPanel fieldsPanel = new JPanel();
        GridBagConstraints constraints;
        GridBagLayout layout = new GridBagLayout();
        fieldsPanel.setLayout(layout);

        JLabel fromLabel = new JLabel("From: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(fromLabel, constraints);
        fieldsPanel.add(fromLabel);
        fromTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(fromTextField, constraints);
        fieldsPanel.add(fromTextField);

        JLabel toLabel = new JLabel("To:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(toLabel, constraints);
        fieldsPanel.add(toLabel);
        toTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 0);
        constraints.weightx = 1.0D;
        layout.setConstraints(toTextField, constraints);
        fieldsPanel.add(toTextField);


        JLabel subjectLabel = new JLabel("Subject:");
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 0);
        layout.setConstraints(subjectLabel, constraints);
        fieldsPanel.add(subjectLabel);
        subjectTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 0);
        layout.setConstraints(subjectTextField, constraints);
        fieldsPanel.add(subjectTextField);

        JScrollPane contentPane = new JScrollPane();
        contentTextArea = new JTextArea(content, 10, 50);
        contentPane.setViewportView(contentTextArea);

        JPanel buttonPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSend();
            }
        });
        buttonPanel.add(sendButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionCancel();
            }
        });
        buttonPanel.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(fieldsPanel, BorderLayout.NORTH);
        getContentPane().add(contentPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void actionSend() {
        if(fromTextField.getText().trim().length() < 1
            || toTextField.getText().trim().length() < 1
            || subjectTextField.getText().trim().length() < 1
            || contentTextArea.getText().trim().length() < 1 ) {
            JOptionPane.showMessageDialog(this,
                    "One or more fields is missing",
                    "Missing field(s)",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        dispose();
    }


    private void actionCancel() {
        cancelled = true;
        dispose();
    }
    public boolean display(){
        setVisible(true);
        return !cancelled;
    }

    public String getFrom(){
        return fromTextField.getText();
    }
    public String getTo(){
        return toTextField.getText();
    }
    public String getSubject(){
        return subjectTextField.getText();
    }
    public String getContent(){
        return contentTextArea.getText();
    }
}
