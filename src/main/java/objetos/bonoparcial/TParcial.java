package objetos.bonoparcial;

import java.awt.Component;
import java.awt.Image;

import javax.swing.JFrame;
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

    private String windowName;

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
        FileReader csvReader = new FileReader();
        ArrayList<ArrayList<String>> rawData = csvReader.readCSVFile(dataFile);
        
        tableData = new CSVTableModel(rawData);

      } catch (IOException err){
        System.err.printf("ERROR WHILE LOADING CSV TABLE DATA FILE: %s", err.toString());
        return null;
      }

      return tableData;
    }

    /**
     * Configuración de JFrame
     */
    private void configWindow () {
      return;
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
     * Genera la tabla y la configura, con datos apartir un archivo csv
     * @param String dataFilePath: Path del archivo csv
     * @return JTable
     */
    private JTable generateTable (String dataFilePath) {
      TableModel tableData = getTableData(dataFilePath);
      JTable table = new JTable(tableData);
      
      table.setRowHeight(24);
      TableColumnModel modelo_Columnas = table.getColumnModel();
      for (int i = 0; i < modelo_Columnas.getColumnCount(); i++) {
        modelo_Columnas.getColumn(i).setPreferredWidth(150);
      }

      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table.getTableHeader().setReorderingAllowed(false);

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
        configWindow();

        JFrame ventana = generateMainFrame();
        JTable tabla = generateTable("base_data.csv");
        JScrollPane panel = generatePanel(tabla);    

        ventana.add(panel);
        ventana.setVisible(true);
    }  
}
