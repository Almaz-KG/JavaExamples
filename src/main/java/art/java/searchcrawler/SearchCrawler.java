package art.java.searchcrawler;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Almaz on 07.06.2015.
 */
public class SearchCrawler extends JFrame{
    private static final String[] MAX_URLS = {"50", "100", "500", "1000"};
    private Map<String, List<String>> disallowListCache = new HashMap<>();
    private JTextField startTextField;
    private JComboBox maxComboBox;
    private JCheckBox limitCheckBox;
    private JTextField logTextField;
    private JTextField searchTextField;
    private JCheckBox caseCheckBox;
    private JButton searchButton;

    private JLabel crawlingLabel2;
    private JLabel crawledLabel2;
    private JLabel toCrawlLabel2;
    private JProgressBar progressBar;
    private JLabel matchesLabel2;
    private JTable table;
    private boolean crawling;
    private PrintWriter logFileWriter;

    public SearchCrawler() {
        setTitle("Search crawler");
        setSize(600, 600);
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

        JPanel searchPanel = new JPanel();
        GridBagConstraints constraints;
        GridBagLayout layout = new GridBagLayout();
        searchPanel.setLayout(layout);

        JLabel startLabel = new JLabel("Start URL: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(startLabel, constraints);
        searchPanel.add(startLabel);
        startTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(startTextField, constraints);
        searchPanel.add(startTextField);

        JLabel maxLabel = new JLabel("Max URLs to crawl: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(maxLabel, constraints);
        searchPanel.add(maxLabel);
        maxComboBox = new JComboBox(MAX_URLS);
        maxComboBox.setEditable(true);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(maxComboBox, constraints);
        searchPanel.add(maxComboBox);

        limitCheckBox = new JCheckBox("Limit crawling to start URL site: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 10, 0, 0);
        layout.setConstraints(limitCheckBox, constraints);
        searchPanel.add(limitCheckBox);

        JLabel blankLabel = new JLabel();
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(blankLabel, constraints);
        searchPanel.add(blankLabel);

        JLabel logLabel = new JLabel("Matches log file: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(logLabel, constraints);
        searchPanel.add(logLabel);

        String file = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "crawler"+System.currentTimeMillis() + ".log";
        logTextField = new JTextField(file);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(logTextField, constraints);
        searchPanel.add(logTextField);

        JLabel searchLabel = new JLabel("Search string: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(searchLabel, constraints);
        searchPanel.add(searchLabel);

        searchTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 0, 0);
        constraints.gridwidth = 2;
        constraints.weightx = 1.0D;
        layout.setConstraints(searchTextField, constraints);
        searchPanel.add(searchTextField);

        caseCheckBox = new JCheckBox("Case sensitive");
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(caseCheckBox, constraints);
        searchPanel.add(caseCheckBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSearch();
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(searchButton, constraints);
        searchPanel.add(searchButton);

        JSeparator separator = new JSeparator();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(separator, constraints);
        searchPanel.add(separator);

        JLabel crawlingLabel1 = new JLabel("Crawling: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(crawlingLabel1, constraints);
        searchPanel.add(crawlingLabel1);

        crawlingLabel2 = new JLabel();
        crawlingLabel2.setFont(crawlingLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(crawlingLabel2, constraints);
        searchPanel.add(crawlingLabel2);

        JLabel crawledLabel1 = new JLabel("Crawled URLs: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(crawledLabel1, constraints);
        searchPanel.add(crawledLabel1);

        crawledLabel2 = new JLabel();
        crawledLabel2.setFont(crawledLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(crawledLabel2, constraints);
        searchPanel.add(crawledLabel2);

        JLabel toCrawlLabel1 = new JLabel("URLs to crawl: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(toCrawlLabel1, constraints);
        searchPanel.add(toCrawlLabel1);

        toCrawlLabel2 = new JLabel();
        toCrawlLabel2.setFont(toCrawlLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(toCrawlLabel2, constraints);
        searchPanel.add(toCrawlLabel2);

        JLabel progressLabel = new JLabel("Crawling progress: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(progressLabel, constraints);
        searchPanel.add(progressLabel);

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setStringPainted(true);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(progressBar, constraints);
        searchPanel.add(progressBar);


        JLabel matchesLabel = new JLabel("Search matches: ");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 10, 0);
        layout.setConstraints(matchesLabel, constraints);
        searchPanel.add(matchesLabel);

        matchesLabel2 = new JLabel();
        matchesLabel2.setFont(matchesLabel.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 10, 5);
        layout.setConstraints(matchesLabel2, constraints);
        searchPanel.add(matchesLabel2);

        table = new JTable(new DefaultTableModel(new Object[][]{},
            new String[]{"URL"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        JPanel matchesPanel = new JPanel();
        matchesPanel.setBorder(BorderFactory.createTitledBorder("Matches"));
        matchesPanel.setLayout(new BorderLayout());
        matchesPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(matchesPanel, BorderLayout.CENTER);

        pack();
    }
    private void actionExit() {
        System.exit(0);
    }
    private void actionSearch() {
        if(crawling){
            crawling = false;
            return;
        }
        List<String> errorList = new ArrayList<>();
        String startUrl = startTextField.getText().trim();
        if(startUrl.length() < 1)
            errorList.add("Missing start url");
        else if(verifyUrl(startUrl) == null)
            errorList.add("Invalid start url");

        int maxUrls = 0;
        String max = ((String)(maxComboBox.getSelectedItem())).trim();
        if (max.length() > 0)
            try{
                maxUrls = Integer.parseInt(max);
                if(maxUrls < 1)
                    errorList.add("Invalid max urls value");
            } catch (NumberFormatException e){
                errorList.add("Invalid max urls value");
            }

        String logFile = logTextField.getText().trim();
        if(logFile.length() < 1)
            errorList.add("Missing matches log file");
        String searchString = searchTextField.getText().trim();
        if(searchString.length() < 1)
            errorList.add("Missing search string");

        if(errorList.size() > 0){
            StringBuffer message = new StringBuffer();
            for (int i = 0; i < errorList.size(); i++) {
                message.append(errorList.get(i));

                if(i + 1 < errorList.size())
                    message.append("\n");
            }

            showError("Ooooops! Some errors",message.toString());
            return;
        }

        startUrl = removeWWWfromUrl(startUrl);
        search(logFile, startUrl, maxUrls, searchString);
    }

    private void search(final String logFile,
                        final String startUrl,
                        final int maxUrls,
                        final String searchString) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                startTextField.setEnabled(false);
                maxComboBox.setEnabled(false);
                limitCheckBox.setEnabled(false);
                logTextField.setEnabled(false);
                searchTextField.setEnabled(false);
                caseCheckBox.setEnabled(false);

                searchButton.setText("Stop");
                table.setModel(new DefaultTableModel(new Object[][]{},
                        new String[]{"URL"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                updateStats(startUrl, 0, 0, maxUrls);
                try{
                    logFileWriter = new PrintWriter(new FileWriter(logFile));
                } catch (IOException e){
                    showError("Unable to open matches log file", e.getMessage());
                    return;
                }
                crawling = true;
                crawl(startUrl, maxUrls, limitCheckBox.isSelected(),
                        searchString, caseCheckBox.isSelected());

                crawling = false;
                logFileWriter.close();
                crawlingLabel2.setText("Done");

                startTextField.setEnabled(true);
                maxComboBox.setEnabled(true);
                limitCheckBox.setEnabled(true);
                logTextField.setEnabled(true);
                searchTextField.setEnabled(true);
                caseCheckBox.setEnabled(true);
                searchButton.setText("Search");
                setCursor(Cursor.getDefaultCursor());

                if(table.getRowCount() == 0){
                    JOptionPane.showMessageDialog(SearchCrawler.this,
                        "Your search string was not found. Please try another",
                        "Search string not found",
                         JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        thread.start();
    }



    private void showError(String shortMessage, String message) {
        JOptionPane.showMessageDialog(this, shortMessage + "\n\n" + message,
                "Error", JOptionPane.ERROR_MESSAGE);
        
    }
    private void updateStats(String crawling, int crawled,
                             int toCrawl, int maxUrls) {
        crawlingLabel2.setText(crawling);
        crawledLabel2.setText("" + crawled);
        toCrawlLabel2.setText("" + toCrawl);

        if(maxUrls == -1)
            progressBar.setMaximum(crawled + toCrawl);
        else
            progressBar.setMaximum(maxUrls);

        progressBar.setValue(crawled);
        matchesLabel2.setText("" + table.getRowCount());
    }
    private void addMatch(String url){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{url});

        logFileWriter.println(url);
    }
    private URL verifyUrl(String url){
        if(!url.toLowerCase().startsWith("http://"))
            return null;

        URL verifiedURL = null;
        try{
            verifiedURL = new URL(url);
        } catch (MalformedURLException e){
            return null;
        }
        return verifiedURL;
    }
    private boolean isRobotAllowed(URL urlToCheck){
        String host = urlToCheck.getHost().toLowerCase();
        List<String> disallowList = disallowListCache.get(host);
        if(disallowList == null){
            disallowList = new ArrayList<>();
            try{
                URL robotsFileUrl = new URL("http://" + host + "/robots.txt");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(robotsFileUrl.openStream()));

                String line;
                while((line = reader.readLine()) != null){
                    if(line.indexOf("Disallow:") == 0){
                        String disallowPath = line.substring("Disallow:".length());
                        int commentIndex = disallowPath.indexOf("#");
                        if(commentIndex != -1)
                            disallowPath.substring(0, commentIndex);

                        disallowPath = disallowPath.trim();
                        disallowList.add(disallowPath);
                    }
                }
                disallowListCache.put(host, disallowList);
            } catch (IOException e){
                return true;
            }
        }

        String file = urlToCheck.getFile();
        for (int i = 0; i < disallowList.size(); i++) {
            String disallow = disallowList.get(i);
            if(file.startsWith(disallow))
                return false;
        }
        return true;
    }
    private String downloadPage(URL pageUrl){
        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(pageUrl.openStream()));

            String line;
            StringBuffer pageBuffer = new StringBuffer();
            while((line = reader.readLine()) != null){
                pageBuffer.append(line);
            }

            return pageBuffer.toString();
        } catch (IOException e){
            showError("Unable open connection", e.getMessage());
        }
        return null;
    }
    private String removeWWWfromUrl(String url){
        int index = url.indexOf("://www.");
        if(index != -1)
            return url.substring(0, index + 3) + url.substring(index + 7);

        return url;
    }
    private List<String> retrieveLinks(URL pageUrl, String pageContents,
                                       Set<String> crawledList, boolean limitHost){
        Pattern p = Pattern.compile("<a\\s+href\\s*-\"?(.*?)[\"|>]",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(pageContents);
        List<String> linkList = new ArrayList<>();
        while(m.find()){
            String link = m.group(1).trim();
            if(link.length() < 1)
                continue;
            if(link.charAt(0) == '#')
                continue;
            if(link.indexOf("mailto:") != -1)
                continue;
            if(link.toLowerCase().indexOf("javascript") != -1)
                continue;

            if(link.indexOf("://") == -1) {
                if (link.charAt(0) == '/') {
                    link = "http://" + pageUrl.getHost() + link;
                } else {
                    String file = pageUrl.getFile();
                    if (file.indexOf('/') == -1)
                        link = "http://" + pageUrl.getHost() + "/" + link;
                    else {
                        String path = file.substring(0, file.lastIndexOf('/') + 1);
                        link = "http://" + pageUrl.getHost() + path + link;
                    }
                }
            }

            int index = link.indexOf("#");
            if(index != -1)
                link = link.substring(0, index);
            link = removeWWWfromUrl(link);
            URL verifiedLink = verifyUrl(link);
            if(verifiedLink == null)
                continue;


            if(limitHost && !pageUrl.getHost().toLowerCase().equals(
                    verifiedLink.getHost().toLowerCase()))
                continue;
            if(crawledList.contains(link))
                continue;

            linkList.add(link);
        }

        return linkList;
    }
    private boolean searchStringMatches(String pageContents, String searchString,
                                        boolean caseSensitive){
        String searchContents = pageContents;
        if(!caseSensitive)
            searchContents = pageContents.toLowerCase();

        Pattern p = Pattern.compile("[\\s]+");
        String[] terms = p.split(searchString);

        for (int i = 0; i < terms.length; i++) {
            if(caseSensitive) {
                if (searchContents.indexOf(terms[i]) == -1)
                    return false;
            } else {
                if(searchContents.indexOf(terms[i].toLowerCase())== -1)
                    return false;
            }
        }
        return true;
    }
    private void crawl(String startUrl, int maxUrls, boolean limitHost,
                       String searchString, boolean caseSensitive) {
        Set<String> crawledList = new HashSet<>();
        Set<String> toCrawlList = new LinkedHashSet<>();
        toCrawlList.add(startUrl);

        while (crawling && toCrawlList.size() > 0){
            if(maxUrls != -1)
                if(crawledList.size() == maxUrls)
                    break;

            String url = toCrawlList.iterator().next();
            toCrawlList.remove(url);
            URL verifiedUrl = verifyUrl(url);

            if(!isRobotAllowed(verifiedUrl))
                continue;

            updateStats(url, crawledList.size(), toCrawlList.size(), maxUrls);
            crawledList.add(url);
            String pageContents = downloadPage(verifiedUrl);
            if(pageContents != null && pageContents.length() > 0){
                List<String> links = retrieveLinks(verifiedUrl, pageContents, crawledList, limitHost);

                toCrawlList.addAll(links);
                if(searchStringMatches(pageContents, searchString, caseSensitive))
                    addMatch(url);
            }
            updateStats(url, crawledList.size(), toCrawlList.size(), maxUrls);
        }
    }

    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        WebLookAndFeel.setDecorateAllWindows(true);

        SearchCrawler crawler = new SearchCrawler();
        crawler.setVisible(true);
    }
}