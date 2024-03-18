
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

class FileReader {
  Path projectRoot = FileSystems.getDefault().getPath("").toAbsolutePath();

  public FileReader () {}

  public ArrayList<ArrayList<String>> readCSVFile (String fileStrPath) throws IOException {
    Path filePath = Paths.get(projectRoot.toString(), "src/main/resources/csv/", fileStrPath);

    if (Files.notExists(filePath)) {
      throw new IOException("ERROR OPENING FILE" + filePath.getFileName());
    }

    ArrayList<ArrayList<String>> fileContent = new ArrayList<ArrayList<String>>();
    List<String> fileRecords = Files.readAllLines(filePath);
    Iterator<String> recordsIterator = fileRecords.iterator();

    while (recordsIterator.hasNext()) {
      String record = recordsIterator.next();
      ArrayList<String> recordData = new ArrayList<String>(Arrays.asList(record.split(",")));
      fileContent.add(recordData);
    }

    return fileContent;
  }
}


