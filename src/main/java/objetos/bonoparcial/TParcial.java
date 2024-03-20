package objetos.bonoparcial;

import java.awt.Component;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumnModel;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.util.ArrayList;
import java.io.IOException;


public class TParcial extends JFrame {
    private Path projectRoot = FileSystems.getDefault().getPath("").toAbsolutePath();
    private FileReader csvReader = new FileReader();

    private String windowName;
    private JTable tabla; // Referencia a la Tabla

    /**
     * Genera un Icono para la Ventana apartir del icono guardado en el proyecto
     * @author Martín Hernández
     * @return ImageIcon
     * @see    ImageIcon
     * @see    FileSystems
     */
    private Image generateWindowIcon () {
      return new ImageIcon(projectRoot.toString() + "/src/main/resources/icon.png").getImage();
    }
    
    /**
     * Genera apartir un archivo CSV un TableModel para una JTable
     *
     * @param   String dataFile: Path en src/main/resources/csv/ de un archivo de datos csv
     * @return  TableModel
     * @author  Martín Hernández (mahernandezor@unal.edu.co)
     * @see     CSVTableModel
     * @see     FileReader
     */
    private TableModel getTableData (String dataFile) {
      CSVTableModel tableData;
      
      try {
        ArrayList<ArrayList<String>> rawData = csvReader.readCSVFile(dataFile);
        
        tableData = new CSVTableModel(rawData);

      } catch (IOException err) {
        System.err.printf("ERROR WHILE LOADING CSV TABLE DATA FILE: %s", err.toString());
        return null;
      }

      return tableData;
    }

    /**
     * 'Decorador' al metodo getTableData para obtener archivos CSV locales
     *
     * @author Martín Hernández (mahernandezor@unal.edu.co)
     * @param String dataFile: Path del archivo CSV en /src/main/resources/csv/
     * @return TableModel con los datos del archivo CSV
     */
    private TableModel getLocalTableData (String dataFile) {
      return getTableData(projectRoot.toString() + "/src/main/resources/csv/" + dataFile);
    } 

    /**
     * Actualiza la tabla del MainFrame apartir de los datos de un nuevo archivo CSV dado
     *
     * @author Martín Hernández (mahernandezor@unal.edu.co)
     * @param String dataFile: Path del nuevo archivo csv a leer
     */
    public void updateTableData (String dataFile) {
      if (dataFile == null) {
        return;
      }

      TableModel newTableModel = getTableData(dataFile);
      this.tabla.setModel(newTableModel);
      configTable(this.tabla);
    }

    /**
     * Genera un menu de acciones y opciones para la ventana
     * @author Martín Hernández (mahernandezor@unal.edu.co)
     * @see JMenuBar
     */
    private JMenuBar generateWindowMenu () {
      JMenuBar menuBar = new WindowMenu(this, csvReader);
      return menuBar;
    }

    /**
     * Genera el Main Frame o Frame Base y lo configura
     * @return JFrame
     */
    private JFrame generateMainFrame () {
      JFrame mainFrame = new JFrame(this.windowName);

      mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); // Termina programa al cerrar la ventana
      mainFrame.setResizable(false); // Deshabilita cambio de tamaño y botón []
      mainFrame.setSize(600, 400); // Tamaño
      mainFrame.setLocationRelativeTo(null); // Ubicación
      mainFrame.setIconImage(generateWindowIcon());

      return mainFrame;
    }

    /**
     * Apartir de la referencia de una JTable configura su diseño y estilo
     * @author Martín Hernández (mahernandezor@unal.edu.co)
     * @param JTable tableRef: Referencia a la tabla
     */
    private void configTable (JTable tableRef) {
      tableRef.getTableHeader().setResizingAllowed(false);
      tableRef.getTableHeader().setReorderingAllowed(false);
      tableRef.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // No permite que se ajuste el tamaño de las columnas
      tableRef.setRowHeight(24);
      TableColumnModel modelo_Columnas = tableRef.getColumnModel();
      for (int i = 0; i < modelo_Columnas.getColumnCount(); i++) {
        modelo_Columnas.getColumn(i).setPreferredWidth(150);
      }
    }

    /**
     * Genera la tabla y la configura, con datos apartir un archivo csv
     * @param String dataFilePath: Path del archivo csv
     * @return JTable
     */
    private JTable generateTable (String dataFilePath) {
      TableModel tableData = getLocalTableData(dataFilePath);
      JTable table = new JTable(tableData);
      
      configTable(table);
      return table;
    }

    /**
     * Genera el Panel con los scrollbars y lo configura, agrega un componente hijo
     * @param Component childElement: Componente a agregar al Panel
     * @return JScrollPane
     */
    private JScrollPane generatePanel (Component childElement) {
        JScrollPane panel = new JScrollPane(
            childElement,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        return panel;
    }

    /**
     * Constructor de la Ventana, configura, estructura y genera sus contenidos
     * @param String windowName: Nombre de la ventana
     * @return TParcial
     */

    public TParcial (String windowName) {
      this.windowName = windowName;
      
      this.tabla = generateTable("base_data.csv");
    
      JFrame ventana = generateMainFrame();
      JScrollPane panel = generatePanel(tabla);
      JMenuBar menu = generateWindowMenu();

      ventana.setJMenuBar(menu);
      ventana.add(panel);
      ventana.setVisible(true);
    }  
}
