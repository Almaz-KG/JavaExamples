package art.java.javamail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Properties;

public class EmailClient extends JFrame{
    private MessagesTableModel tableModel;
    private JTable table;
    private JTextArea messageTextArea;
    private JSplitPane splitPane;
    private JButton replyButton, forwardButton, deleteButton;
    private Message selectedMessage;
    private boolean deleting;
    private Session session;

    public EmailClient() {
        setTitle("E-mail client");
        setSize(640, 480);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JPanel buttonPanel = new JPanel();
        JButton newButton = new JButton();
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionNew();
            }
        });
        buttonPanel.add(newButton);
        tableModel = new MessagesTableModel();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
               @Override
               public void valueChanged(ListSelectionEvent e) {
                   tableSelectionChanged();
               }
           });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel emailPanel = new JPanel();
        emailPanel.setBorder(BorderFactory.createTitledBorder("E-mails"));
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(table), new JScrollPane(messageTextArea));
        emailPanel.setLayout(new BorderLayout());
        emailPanel.add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel2 = new JPanel();
        replyButton = new JButton("Reply");
        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionReply();
            }
        });
        replyButton.setEnabled(false);
        buttonPanel2.add(replyButton);
        forwardButton = new JButton("Forward");
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionForward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel2.add(forwardButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDelete();
            }
        });
        buttonPanel2.add(deleteButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(emailPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel2, BorderLayout.SOUTH);
    }


    @Override
    public void show(){
        super.show();
        splitPane.setDividerLocation(0.5);
    }
    private void showError(String message, boolean exit) {
        JOptionPane.showMessageDialog(this,
                message,"Error", JOptionPane.ERROR_MESSAGE);
        if(exit)
            actionExit();

    }
    private void showSelectedMessage() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try{
            messageTextArea.setText(getMessageContent(selectedMessage));
            messageTextArea.setCaretPosition(0);
        } catch (Exception e){
            showError("Unable to load message", false);
            e.printStackTrace();
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void actionForward() {
        sendMessage(MessageDialog.FORWARD, selectedMessage);
    }
    private void actionReply() {
        sendMessage(MessageDialog.REPLY, selectedMessage);
    }
    private void actionNew() {
        sendMessage(MessageDialog.NEW, null);
    }
    private void actionDelete() {
        deleting = true;
        try{
            selectedMessage.setFlag(javax.mail.Flags.Flag.DELETED, true);
            Folder folder = selectedMessage.getFolder();
            folder.close(true);
            folder.open(Folder.READ_WRITE);
        } catch (Exception e){
            showError("Unable to delete message", false);
            e.printStackTrace();
        }
        tableModel.deleteMessage(table.getSelectedRow());
        messageTextArea.setText("");
        deleting = false;
        selectedMessage = null;
        updateButtons();
    }
    private void actionExit() {
        System.exit(0);
    }

    public void connect(){
        ConnectDialog dialog = new ConnectDialog(this);
        dialog.setVisible(true);
        StringBuffer connectionUrl = new StringBuffer();
        connectionUrl.append(dialog.getProtocolType() + "://");
        connectionUrl.append(dialog.getUsername() + ":");
        connectionUrl.append(dialog.getPassword() + "@");
        connectionUrl.append(dialog.getServer() + "/");

        final DownloadingDialog downloadingDialog = new DownloadingDialog(this);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                downloadingDialog.setVisible(true);
            }
        });
        Store store = null;
        try{
            Properties props = new Properties();
            //props.put("mail.smtp.host", dialog.getSmtpServer());
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", "pop.yandex.ru");
            props.setProperty("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(props, null);

            URLName urlName = new URLName(connectionUrl.toString());
            store = session.getStore();
            store.connect();
        } catch (Exception e){
            downloadingDialog.dispose();
            System.out.println(e.getMessage());
            showError("Unable to connect", true);
        }
        try{
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            Message[] messages = folder.getMessages();
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            folder.fetch(messages, profile);
            tableModel.setMessages(messages);
        }catch (Exception e){
            downloadingDialog.dispose();
            showError("Unable to downlad messages", true);
            e.printStackTrace();
        }
        downloadingDialog.dispose();
    }
    private void sendMessage(int type, Message message) {
        MessageDialog dialog;
        try{
            dialog = new MessageDialog(this, type, message);
            if(!dialog.display())
                return;
        } catch (Exception e){
            showError("Unable to send message.", false);
            e.printStackTrace();
            return;
        }

        try{
            Message newMessage = new MimeMessage(session);
            newMessage.setFrom(new InternetAddress(dialog.getFrom()));
            newMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(dialog.getTo()));
            newMessage.setSubject(dialog.getSubject());
            newMessage.setSentDate(new Date());
            newMessage.setText(dialog.getContent());

            Transport.send(newMessage);
        }catch (Exception e){
            showError("Unable to send message", false);
            e.printStackTrace();
        }
    }
    private void tableSelectionChanged(){
        if(!deleting){
            selectedMessage = tableModel.getMessage(table.getSelectedRow());
            showSelectedMessage();
            updateButtons();
        }
    }
    private void updateButtons() {
        if(selectedMessage != null){
            replyButton.setEnabled(true);
            forwardButton.setEnabled(true);
            deleteButton.setEnabled(true);
        } else{
            replyButton.setEnabled(false);
            forwardButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
    }

    public static String getMessageContent(Message message) throws Exception{
        Object content = message.getContent();
        if(content instanceof Multipart){
            StringBuffer messageContent = new StringBuffer();
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                Part part = (Part) multipart.getBodyPart(i);
                if(part.isMimeType("text/plain"))
                    messageContent.append(part.getContent().toString());
            }
            return messageContent.toString();
        } else {
            return content.toString();
        }
    }

    public static void main(String[] args) throws Exception{
//        WebLookAndFeel.install();
//        WebLookAndFeel.setDecorateAllWindows(true);

        EmailClient client = new EmailClient();
        client.show();

        client.connect();
    }
}
