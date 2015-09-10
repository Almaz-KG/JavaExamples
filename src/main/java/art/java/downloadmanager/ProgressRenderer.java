package art.java.downloadmanager;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ProgressRenderer extends JProgressBar
	implements TableCellRenderer{
	private static final long serialVersionUID = 2660562591524589283L;

	public ProgressRenderer(int min, int max){
		super(min, max);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, 
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setValue((int)((Double)value).doubleValue());
		return this;
	}

}
