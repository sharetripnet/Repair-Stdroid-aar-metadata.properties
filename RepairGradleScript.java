import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


/*** @Brief:
 * Instructions: create an `input.txt` file in the current folder. it is added in the gitignore file,
 * so no need to worry about git. Your input.txt should look like input.txt.example.
 * Now fire up the terminal, and:

 ```
    $  javac RepairGradleScript.java
    $  java RepairGradleScript < input.txt
 ```

 Quick tutorial to setup javac on Fedora to use from terminal:
 https://docs.fedoraproject.org/en-US/quick-docs/installing-java/
 */

public class RepairGradleScript {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read input file names
        ArrayList<String> inputFiles = new ArrayList<>();
        while (sc.hasNextLine()) {
            String fileName = sc.nextLine();
            if(!fileName.isEmpty()) {
                inputFiles.add(fileName);
            }
        }

        for (String aarMetadataPropertyFile: inputFiles) {
            editFile(aarMetadataPropertyFile);
        }

        System.out.println("Android project should run properly!");
    }

    private static void editFile(String fileName) {
        File aarMetaDataProperties = new File(fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(aarMetaDataProperties, false);  // true -> append, false -> overwrite
            byte[] bytes = getBytes();
            fileOutputStream.write(bytes);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if(fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException ioException1) {
                    ioException1.printStackTrace();
                }
            }
        }
    }

    private static byte[] getBytes() throws UnsupportedEncodingException {
        String content="";
        content +=  "aarFormatVersion=1.0\n";
        content += "aarMetadataVersion=1.0\n";
        content += "minCompileSdk=32\n";
        content += "minCompileSdkExtension=0\n";
        content += "minAndroidGradlePluginVersion=1.0.0\n";

        return content.getBytes();
    }
}
