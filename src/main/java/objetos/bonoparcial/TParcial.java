package objetos.bonoparcial;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Component;

import javax.swing.*;
import javax.swing.table.*;

import java.net.URL;

import java.util.ArrayList;

import java.io.IOException;

public class TParcial extends JFrame {
    private URL icono_URL = getClass().getClassLoader().getResource("icono.png");
    private ImageIcon icono = new ImageIcon(icono_URL);
    private String windowName;
    
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

    private void configWindow () {
      return;
    }

    private JFrame generateMainFrame () {
      JFrame mainFrame = new JFrame(this.windowName);

      mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); // Termina programa al cerrar la ventana
      mainFrame.setResizable(false); // Deshabilita cambio de tamaño y botón []
      mainFrame.setSize(600, 400); // Tamaño
      mainFrame.setLocationRelativeTo(null); // Ubicación
      mainFrame.setIconImage(icono.getImage());

      return mainFrame;
    }

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

    private JScrollPane generatePanel (Component childElement) {
        JScrollPane panel = new JScrollPane(
            childElement,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        return panel;
    }
    
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
