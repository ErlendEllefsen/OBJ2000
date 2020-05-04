package eksamen;

import java.io.FileWriter;
import java.io.IOException;
 

class NewFile {
    NewFile(String name, String sex, int age, String city, String phone, String intrest1, String intrest2, String intrest3){
        try {
            System.out.println(age);
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write(name);
            writer.write("\r\n");   // write new line
            writer.write(sex);
            writer.write("\r\n");   // write new line
            writer.write(age);
            writer.write("\r\n");   // write new line
            writer.write(city);
            writer.write("\r\n");   // write new line
            writer.write(phone);
            writer.write("\r\n");   // write new line
            writer.write(intrest1);
            writer.write("\r\n");   // write new line
            writer.write(intrest2);
            writer.write("\r\n");   // write new line
            writer.write(intrest3);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}