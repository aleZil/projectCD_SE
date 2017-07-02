package utility;

import javax.swing.table.DefaultTableModel;

public class TableModelCarrello extends DefaultTableModel{
	
    public boolean isCellEditable(int row, int column) {
        return column == 2 || column == 3 ? true : false;
    }
}
