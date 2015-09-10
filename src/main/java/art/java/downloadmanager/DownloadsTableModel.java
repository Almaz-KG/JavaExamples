package art.java.downloadmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;

public class DownloadsTableModel extends AbstractTableModel
	implements Observer {
	private static final long serialVersionUID = 385368505006906109L;
	private static final String[] COLUMN_NAMES = {
		"URL", "Size", "Progress", "Status"
	};
	private static final Class<?>[] COLUMN_CLASSES = { 
		String.class, String.class, JProgressBar.class, String.class
	};
	private List<Download> downloadList = new ArrayList<>();
	
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}
	@Override
	public int getRowCount() {
		return this.downloadList.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		Download download = (Download)this.downloadList.get(row);
		switch(col){
		case 0:
			return download.getUrl();
		case 1: 
			int size = download.getSize();
			return (size == -1)? "" : Integer.toString(size);
		case 2:
			return new Double(download.getProgress());
		case 3:
			return Download.STATUSES[download.getStatus()];
		}
		
		return "";
	}

	@Override
	public void update(Observable o, Object arg) {
		int index = this.downloadList.indexOf(o);
		fireTableCellUpdated(index, index);
	}
	
	public void addDownload(Download download){
		download.addObserver(this);
		this.downloadList.add(download);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}
	public Download getDownload(int row){
		return (Download) this.downloadList.get(row);
	}
	public void clearDownload(int row){
		this.downloadList.remove(row);
		this.fireTableRowsDeleted(row, row);
	}
}





























