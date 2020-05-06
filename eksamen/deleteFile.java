package eksamen;

import java.io.File;
//Sletter alle filler der navnet ender på .txt
class deleteFile {
    File folder = new File("./");{
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                file.delete();
            }
        }
    }
}