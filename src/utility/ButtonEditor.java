package utility;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
  
public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private String    label;
  private boolean   isPushed;
  private JTable table;
  private int row;
  private JFrame caller;
  
  public ButtonEditor(JCheckBox checkBox,JFrame caller) {
    super(checkBox);
    this.caller=caller;
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }
  
  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value ==null) ? "" : value.toString();
    button.setText( label );
    this.table=table;
    this.row=row;
    isPushed = true;
    return button;
  }
  
  public Object getCellEditorValue() {
    if (isPushed)  {
      //
      //
      JOptionPane.showMessageDialog(button ,row);
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String( label ) ;
  }
}