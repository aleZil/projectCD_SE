package utility;

import javax.swing.table.DefaultTableModel;

public class tableModel extends DefaultTableModel{
	
    public boolean isCellEditable(int row, int column) {
        return column == 3 ? true : false;
    }
}
