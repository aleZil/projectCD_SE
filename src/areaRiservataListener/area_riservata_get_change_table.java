package areaRiservataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import utility.TableCellListener;

public class area_riservata_get_change_table implements Action{

	JTable table;
	
	public area_riservata_get_change_table(JTable tab) 
	{
		table=tab;
	}

	@Override
	public void doIt(FSM arg0, Input arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
