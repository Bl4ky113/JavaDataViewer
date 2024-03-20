
package objetos.bonoparcial;

import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

import java.io.IOException;

/** 
 * Clase que nos permite leer archivos CSV de una forma sencilla
 *
 * @author Martín Hernández (mahernandezor@unal.edu.co)
 * @see    Files
 * @see    FileSystems
 * @see    Path
 * @see    Paths
 */
public class FileReader {
  private Path projectRoot = FileSystems.getDefault().getPath("").toAbsolutePath();

  public FileReader () {}

  /**
   * Genera un ArrayList con los records de un archivo csv en el OS
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   * @param  String fileFolder: Path del folder del archivo CSV
   * @param  String fileName: Nombre del archivo CSV
   * @return ArrayList con valores de los records
   */
  public ArrayList<ArrayList<String>> readCSVFile (String fileStrPath) throws IOException {
    Path filePath = Paths.get(fileStrPath);
    
    // Si el archivo no existe, tira un error
    if (Files.notExists(filePath)) {
      throw new IOException("ERROR OPENING FILE" + filePath.getFileName());
    }

    ArrayList<ArrayList<String>> fileContent = new ArrayList<ArrayList<String>>();
    List<String> fileRecords = Files.readAllLines(filePath);
    Iterator<String> recordsIterator = fileRecords.iterator();

    // Iterar a lo largo del archivo
    // tomando cada registro, separandolo en cada columna
    while (recordsIterator.hasNext()) {
      String record = recordsIterator.next();
      ArrayList<String> recordData = new ArrayList<String>(Arrays.asList(record.split(",")));
      fileContent.add(recordData);
    }

    return fileContent;
  }

  /**
   * Abre un JFileChooser para elegir un archivo CSV y retornar su path.
   *
   * @author Martín Hernández (mahernandezor@unal.edu.co)
   * @param Component parentComponent: Componente padre del Dialog
   * @return Path del archivo CSV
   */
  public String openAndReadCSVFile (Component parentComponent) {
    JFileChooser csvChooser = new JFileChooser(projectRoot + "/src/main/resources/csv/");
    FileFilter csvFilter = new FileNameExtensionFilter("Archivos CSV", "csv");

    csvChooser.setDialogTitle("Selecciona un archivo CSV");
    csvChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    csvChooser.setFileFilter(csvFilter);

    int resultChooser = csvChooser.showOpenDialog(parentComponent);

    if (resultChooser != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    File csvFile = csvChooser.getSelectedFile();
    return csvFile.getPath();
  }
}
