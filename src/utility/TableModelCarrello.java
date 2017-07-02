package utility;

import javax.swing.table.DefaultTableModel;

public class TableModelCarrello extends DefaultTableModel{

	public boolean isCellEditable(int row, int column) {
		return column == 3 || column == 4 ? true : false;
	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub

		super.removeRow(row);

	}
}
