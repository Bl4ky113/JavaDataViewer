package objetos.bonoparcial;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.table.*;

import java.net.URL;

import java.util.ArrayList;

import java.io.IOException;

public class TParcial extends JFrame {
    
    public URL icono_URL = getClass().getClassLoader().getResource("icono.png");
    public ImageIcon icono = new ImageIcon(icono_URL);
    //ImageIcon icono = new ImageIcon("C:\\Users\\jcver\\OneDrive\\Documents\\NetBeansProjects\\TParcial\\src\\main\\java\\objetos\\bonoparcial\\icono.png"); // OG Version
    
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
    
    public TParcial(String nombre_Ventana) throws IOException {
        /*
         * Ventana
         */
        JFrame ventana = new JFrame(nombre_Ventana); // Crea JFrame
        ventana.setDefaultCloseOperation(EXIT_ON_CLOSE); // Termina programa al cerrar la ventana
        ventana.setResizable(false); // Deshabilita cambio de tamaño y botón []
        ventana.setSize(600, 400); // Tamaño
        ventana.setLocationRelativeTo(null); // Ubicación
        ventana.setIconImage(icono.getImage());
        
        /*
         * Tabla
         */
        TableModel tableData = getTableData("base_data.csv");

        JTable tabla = new JTable(tableData);
        //tabla.setBounds(0, 0, 300, 400);
        //tabla.setRowHeight(5); // No creo que sea necesario
        TableColumnModel modelo_Columnas = tabla.getColumnModel();
        for (int i = 0; i < modelo_Columnas.getColumnCount(); i++) {
          modelo_Columnas.getColumn(i).setPreferredWidth(150);
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // No permite que se ajuste el tamaño de las columnas
        tabla.getTableHeader().setReorderingAllowed(false);
        /*
         * Panel
         */
        JScrollPane panel = new JScrollPane(tabla,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        ventana.add(panel);
        //pack(); // ¿Es necesario usarlo?
        ventana.setVisible(true);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        System.out.println("Mostrando ventana en pantalla...");
        TParcial ventana = new TParcial("TParcial");
        System.out.println("Finalizando.");
    }
}
