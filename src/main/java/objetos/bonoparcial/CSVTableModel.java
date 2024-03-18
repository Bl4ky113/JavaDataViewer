
package objetos.bonoparcial;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

class CSVTableModel extends AbstractTableModel {
  private ArrayList<String> columnNames;
  private ArrayList<ArrayList<String>> data;

  public CSVTableModel (ArrayList<String> columnsList, ArrayList<ArrayList<String>> dataList) {
    this.columnNames = columnsList;
    this.data = dataList;
  }

  public String getValueAt (int row, int column) {
    return data.get(row).get(column);
  }

  public int getRowCount () {
    return data.size();
  }

  public int getColumnCount () {
    return columnNames.size();
  }

  public String getColumnName (int index) {
    return columnNames.get(index);
  }
}
