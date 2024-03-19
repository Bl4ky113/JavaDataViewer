
package objetos.bonoparcial;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
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
class FileReader {
  /**
   * Directorio base, o root, del proyecto
   */
  private Path projectRoot = FileSystems.getDefault().getPath("").toAbsolutePath();

  public FileReader () {}

  /**
   * Genera un ArrayList con los valores de los Records dentro de un archivo csv
   *
   * @param   String. Path del archivo CSV dentro de /src/main/resources/csv/
   * @return  Arraylist con valores de los Records
   */
  public ArrayList<ArrayList<String>> readCSVFile (String fileStrPath) throws IOException {
    Path filePath = Paths.get(projectRoot.toString(), "src/main/resources/csv/", fileStrPath);
    
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
}


