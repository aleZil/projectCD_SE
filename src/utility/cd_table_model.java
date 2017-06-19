package utility;

import javax.swing.table.DefaultTableModel;

public class cd_table_model extends DefaultTableModel{
	
	private boolean[][] editableCell;
	
	public cd_table_model(int rows,int columns)
	{
		super(rows,columns);
		editableCell=new boolean[rows][columns];
	}
	
	public boolean isCellEditable(int row,int column)
	{
		if(column==0 || column==8 || column==9)
			return false;
		return true;
	}
}
