package art.java.minibrowser.swing;

//import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 07.06.2015.
 */
public class MiniBrowser extends JFrame implements HyperlinkListener{
    private JButton backButton, forwardButton;
    private JTextField locationTextField;
    private JEditorPane displayEditorPane;
    private List<String> pageList = new ArrayList<>();

    public MiniBrowser(){
        super("Mini browser");
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
        backButton = new JButton("< Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionBack();
            }
        });
        backButton.setEnabled(false);
        buttonPanel.add(backButton);
        forwardButton = new JButton("Forward >");
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionForward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel.add(forwardButton);
        locationTextField = new JTextField(35);
        locationTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    actionGo();
            }
        });
        buttonPanel.add(locationTextField);
        JButton goButton = new JButton("GO");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionGo();
            }
        });
        buttonPanel.add(goButton);

        displayEditorPane = new JEditorPane();
        displayEditorPane.setContentType("text/html");
        displayEditorPane.setEditable(false);
        displayEditorPane.addHyperlinkListener(this);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(displayEditorPane),BorderLayout.CENTER);
    }
    private void actionExit() {
        System.exit(0);
    }
    private void actionBack() {
        URL currentURL = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentURL.toString());
        try{
            showPage(new URL(pageList.get(pageIndex - 1)), false);
        } catch (MalformedURLException e){
            showError(e.getMessage());
        }
    }
    private void actionForward() {
        URL currentURL = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentURL.toString());
        try{
            showPage(new URL(pageList.get(pageIndex + 1)), false);
        } catch (MalformedURLException e){
            showError(e.getMessage());
        }
    }
    private void actionGo() {
        URL verifiedURL = verifyUrl(locationTextField.getText());
        if(verifiedURL != null)
            showPage(verifiedURL, true);
        else
            showError("Invalid url");
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private URL verifyUrl(String url) {
        if(!url.toLowerCase().startsWith("http://"))
            return null;

        URL verifiedURL = null;
        try{
            verifiedURL = new URL(url);
        } catch (MalformedURLException e){
            showError(e.getMessage());
            return null;
        }

        return verifiedURL;
    }
    private void showPage(URL url, boolean addToList) {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try{
            URL currentUrl = displayEditorPane.getPage();
            displayEditorPane.setPage(url);
            URL newUrl = displayEditorPane.getPage();

            if(addToList) {
                int listSize = pageList.size();
                if (listSize > 0) {
                    int pageIndex = pageList.indexOf(currentUrl.toString());
                    if (pageIndex < listSize - 1)
                        for (int i = listSize - 1; i > pageIndex; i--)
                            pageList.remove(i);
                }
                pageList.add(newUrl.toString());
            }
            locationTextField.setText(newUrl.toString());
            updateButtons();
        } catch (IOException e){
            showError(e.getMessage());
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    private void updateButtons() {
        if(pageList.size() < 2){
            backButton.setEnabled(false);
            forwardButton.setEnabled(false);
        } else{
            URL currentUrl = displayEditorPane.getPage();
            int pageIndex = pageList.indexOf(currentUrl.toString());
            backButton.setEnabled(pageIndex > 0);
            forwardButton.setEnabled(pageIndex < (pageList.size() - 1));
        }
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        HyperlinkEvent.EventType eventType = e.getEventType();
        if(eventType == HyperlinkEvent.EventType.ACTIVATED){
            if(e instanceof HTMLFrameHyperlinkEvent){
                HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument document = (HTMLDocument)displayEditorPane.getDocument();
                document.processHTMLFrameHyperlinkEvent(linkEvent);
            }
        } else{
            showPage(e.getURL(), true);
        }
    }

    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        WebLookAndFeel.setDecorateAllWindows(true);

        MiniBrowser browser = new MiniBrowser();
        browser.setVisible(true);
    }
}
