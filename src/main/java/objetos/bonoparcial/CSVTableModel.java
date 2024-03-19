
package objetos.bonoparcial;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

/**
 * Implementación de un Table Model abstracto, para los JTable de Swing, 
 * compatible con ArrayLists.
 * @author Martín Hernández (mahernandezor@unal.edu.co)
 * @see    AbstractTableModel 
 * @see    JTable
 */
class CSVTableModel extends AbstractTableModel {
  private ArrayList<String> columnNames;
  private ArrayList<ArrayList<String>> data;

  /**
   * Constructor para el modelo de los datos de la tabla
   * @param ArrayList<ArrayList<String>> tableData: Datos para la tabla
   * @see   ArrayList
   */
  public CSVTableModel (ArrayList<ArrayList<String>> tableData) {
    this.columnNames = tableData.getFirst();
    tableData.remove(0);

    this.data = tableData;
  }

  /**
   * ======================
   * METODOS ABSTRACTOS DE AbstractTableModel
   * ======================
   */

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
