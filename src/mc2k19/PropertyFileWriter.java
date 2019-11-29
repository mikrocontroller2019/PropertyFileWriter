package mc2k19;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author #mikrocontroller2019
 */
public class PropertyFileWriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String fileName = args[0];
            if (!fileName.endsWith(".properties")) {
                fileName = fileName + ".properties";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]).append(System.getProperty("line.separator"));
            }
            Properties properties = new Properties();
            try {
                properties.load(new StringReader(sb.toString()));
                Path filePath = Paths.get(fileName);
                if (!filePath.isAbsolute()) {
                    filePath = Paths.get(System.getProperty("user.home"), fileName);
                }
                properties.store(new FileOutputStream(filePath.toFile()), fileName);
            } catch (IOException ex) {
                Logger.getLogger(PropertyFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
