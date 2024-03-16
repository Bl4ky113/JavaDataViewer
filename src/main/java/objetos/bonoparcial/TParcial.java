package objetos.bonoparcial;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.table.*;
import java.net.URL;

public class TParcial extends JFrame {
    
    public URL icono_URL = getClass().getClassLoader().getResource("icono.png");
    public ImageIcon icono = new ImageIcon(icono_URL);
    //ImageIcon icono = new ImageIcon("C:\\Users\\jcver\\OneDrive\\Documents\\NetBeansProjects\\TParcial\\src\\main\\java\\objetos\\bonoparcial\\icono.png"); // OG Version
    
    public TParcial(String nombre_Ventana){
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
        JTable tabla = new JTable(informacion, columnas);
        //tabla.setBounds(0, 0, 300, 400);
        //tabla.setRowHeight(5); // No creo que sea necesario
        TableColumnModel modelo_Columnas = tabla.getColumnModel();
        modelo_Columnas.getColumn(0).setPreferredWidth(150);
        modelo_Columnas.getColumn(1).setPreferredWidth(150);
        modelo_Columnas.getColumn(2).setPreferredWidth(150);
        modelo_Columnas.getColumn(3).setPreferredWidth(150);
        modelo_Columnas.getColumn(4).setPreferredWidth(150);
        modelo_Columnas.getColumn(5).setPreferredWidth(150);
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
    
    public String[] columnas = {"1","2","3", "4", "5", "6"};
    public String[][] informacion = {{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}, 
                          {"un","deux","trois", "quatre", "cinq", "six"},    
                          {"eins","zwai","drei", "vier", "fünf", "sechs"},{"uno","dos","tres", "cuatro", "cinco", "seis"},
                          {"one", "two", "three", "four", "five", "six"}};
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Mostrando ventana en pantalla...");
        TParcial ventana = new TParcial("TParcial");
        System.out.println("Finalizando.");
    }
}
