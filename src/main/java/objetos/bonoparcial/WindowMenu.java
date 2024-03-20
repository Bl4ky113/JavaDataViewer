
package objetos.bonoparcial;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.JFileChooser;

import java.util.ArrayList;

public class WindowMenu extends JMenuBar implements ActionListener, ItemListener {
  FileReader csvReader;
  TParcial mainFrame;
  
  /**
   * Genera un item para los JMenu
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   * @param String label: Texto del item
   * @param String iconName: nombre de los Icon defaults de Swing
   * @param int mnemonic: código del mnemonic
   * @return JMenuItem 
   */
  private JMenuItem generateMenuItem (String label, String iconName, int mnemonic) {
    JMenuItem item = new JMenuItem();
    Icon itemIcon = UIManager.getIcon(iconName);

    item.setText(label);
    item.setIcon(itemIcon);
    item.setMnemonic(mnemonic);
    
    return item;
  }

  /**
   * Genera un menu en el MainFrame para poder cambiar de datos y archivo CSV
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   * @return JMenu de selección de archivos
   */
  
  private JMenu generateFileChooserMenu () {
    JMenu fileMenu = new JMenu();
    fileMenu.setText("Archivos");
    fileMenu.setMnemonic(KeyEvent.VK_A);

    JMenuItem openFileItem = generateMenuItem("Abrir archivo csv", "FileView.fileIcon", KeyEvent.VK_C);
    openFileItem.addAction Listener(this);

    fileMenu.add(openFileItem);
    return fileMenu;
  }

  /**
   * Constructor del MenuBar del MainFrame
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   * @param TParcial mainFrameRef: Referencia del MainFrame
   * @param FileReader csvReaderRef: Referencia del FileReader
   * @return WindowMenu
   */
  public WindowMenu (TParcial mainFrameRef, FileReader csvReaderRef) {
    super();
    this.csvReader = csvReaderRef;
    this.mainFrame = mainFrameRef;
    
    JMenu fileChooserMenu = generateFileChooserMenu();

    this.add(fileChooserMenu);
  }

  /**
   * Handler del evento para abrir un buscador de archivos y actualizar la tabla del MainFrame
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   */
  public void openCSVFile () {
    String filePath = csvReader.openAndReadCSVFile(mainFrame);
    this.mainFrame.updateTableData(filePath);
  }

  /**
   * EventHandler para las acciones de los Menus
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   * @param ActionEvent event: Evento de la accion
   */
  public void actionPerformed (ActionEvent event) {
    JMenuItem selectedItem = (JMenuItem)event.getSource();

    switch (selectedItem.getMnemonic()) {
      case (KeyEvent.VK_C):
        openCSVFile();
        break;
    }
  }

  public void itemStateChanged (ItemEvent event) {
    System.out.println(event);
  }
}
