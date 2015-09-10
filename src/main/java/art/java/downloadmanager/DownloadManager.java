package art.java.downloadmanager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DownloadManager extends JFrame 
	implements Observer{
	private static final long serialVersionUID = 805122856726456947L;
	private JTextField addTextField;
	private DownloadsTableModel tableModel;
	private JTable table;
	private JButton pauseButton;
	private JButton resumeButton;
	private JButton cancelButton;
	private JButton clearButton;
	private Download selectedDownload;
	private boolean clearing;
	
	public DownloadManager(){
		setTitle("Download manager");
		setSize(640,480);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				actionExit();
			}
		});
		JMenuBar menuBar = new  JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem fileExitMenuItem = new JMenuItem("Exit",
				KeyEvent.VK_X);
		fileExitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionExit();
			}
		});
		fileMenu.add(fileExitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		JPanel addPanel = new JPanel();
		addTextField = new JTextField(30);
		addPanel.add(addTextField);
		JButton addButton = new JButton("Add Download");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionAdd();
			}
		});
		addPanel.add(addButton);
		tableModel = new DownloadsTableModel();
		table = new JTable(tableModel);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				tableSelectionChanged();
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ProgressRenderer renderer = new ProgressRenderer(0, 100);
		renderer.setStringPainted(true);
		table.setDefaultRenderer(JProgressBar.class, renderer);
		
		table.setRowHeight(
			(int) renderer.getPreferredSize().getHeight());
		JPanel downloadsPanel = new JPanel();
		downloadsPanel.setBorder(
				BorderFactory.createTitledBorder("Downloads"));
	
		downloadsPanel.setLayout(new BorderLayout());
		downloadsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionPause();
			}
		});
		pauseButton.setEnabled(false);
		buttonsPanel.add(pauseButton);
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionResume();
			}
		});
		resumeButton.setEnabled(false);
		buttonsPanel.add(resumeButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionCancel();
			}
		});
		cancelButton.setEnabled(false);
		buttonsPanel.add(cancelButton);
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				actionClear();
			}
		});
		clearButton.setEnabled(false);
		buttonsPanel.add(clearButton);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(addPanel, BorderLayout.NORTH);
		getContentPane().add(downloadsPanel, BorderLayout.CENTER);
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private void actionExit(){
		System.exit(0);
	}
	private void actionAdd(){
		URL verifiedURL = verifyURL(addTextField.getText());
		if(verifiedURL != null){
			JFileChooser saveOption = new JFileChooser();
			saveOption.showSaveDialog(this);
			File file = saveOption.getSelectedFile();
			if(file != null){
				tableModel.addDownload(new Download(verifiedURL, file.getAbsolutePath()));
				addTextField.setText("");
			}
		} else{
			JOptionPane.showMessageDialog(this, "Invalid download URL",
					"Error", JOptionPane.ERROR_MESSAGE);
			;
		}
	}
	private URL verifyURL(String url) {
		if(!url.toLowerCase().startsWith("http://"))
			return null;
		URL verifiedUrl = null;
		try{
			verifiedUrl = new URL(url);
		} catch(MalformedURLException e){
			System.out.println(e.getMessage());
			return null;
		}
		
		if(verifiedUrl.getFile().length() < 2)
			return null;
		return verifiedUrl;
	}
	private void tableSelectionChanged(){
		if(selectedDownload != null)
			selectedDownload.deleteObserver(DownloadManager.this);
	
		if(!clearing){
			selectedDownload = tableModel.getDownload(table.getSelectedRow());
			selectedDownload.addObserver(DownloadManager.this);
			updateButtons();
		}
	}
	private void actionPause() {
		selectedDownload.pause();
		updateButtons();
	}
	private void actionResume(){
		selectedDownload.resume();
		updateButtons();
	}
	private void actionCancel(){
		selectedDownload.cancel();
		updateButtons();
	}
	private void actionClear(){
		clearing = true;
		tableModel.clearDownload(table.getSelectedRow());
		clearing = false;
		selectedDownload = null;
		updateButtons();
	}
	private void updateButtons() {
		if(selectedDownload != null){
			int status = selectedDownload.getStatus();
			switch(status){
			case Download.DOWNLOADING:
				pauseButton.setEnabled(true);
				resumeButton.setEnabled(false);
				cancelButton.setEnabled(true);
				clearButton.setEnabled(false);
				break;
			case Download.PAUSED:
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(true);
				cancelButton.setEnabled(true);
				clearButton.setEnabled(false);
				break;
			case Download.ERROR:
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(true);
				cancelButton.setEnabled(false);
				clearButton.setEnabled(true);
				break;
			default:
				pauseButton.setEnabled(false);
				resumeButton.setEnabled(false);
				cancelButton.setEnabled(false);
				clearButton.setEnabled(true);
			} 
		} else{
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(false);
			cancelButton.setEnabled(false);
			clearButton.setEnabled(false);
		}
	}
	public void update(Observable o, Object arg) {
		if(selectedDownload != null &&
			selectedDownload.equals(o))
			updateButtons();
	}

	public static void main(String[] args) {
		try {
//			//UIManager.setLookAndFeel(new AluminiumLookAndFeel());
//            WebLookAndFeel.install();
//            WebLookAndFeel.setDecorateAllWindows(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		DownloadManager manager = new DownloadManager();
		manager.setVisible(true);
	}
}

















