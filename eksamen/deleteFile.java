package eksamen;

import java.io.File;

class deleteFile {
    File folder = new File("./");{
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                file.delete();
            }
        }
    }
}