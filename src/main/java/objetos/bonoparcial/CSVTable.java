
package objetos.bonoparcial;

import javax.swing.JTable;
import javax.swing.table.TableModel;

// Intento de implementar una SubClase de JTable para los CSVs.
// no se pudo :c

public class CSVTable extends JTable {
  private CSVTableModel model;

  public CSVTable (CSVTableModel tableModel) {
    super(tableModel);

    this.model = tableModel;
  }

  public CSVTableModel getModel () {
    return this.model;
  }
}
