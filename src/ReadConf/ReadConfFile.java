package ReadConf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadConfFile {

    private HashMap<String, String> data = new HashMap<String, String>();
    private final String fileName;

    // set dir to conf file.
    public ReadConfFile(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    // read conf file and get vars and values
    public void readFile() throws Exception {
        File myFile = new File(this.fileName);
        Scanner myReader = new Scanner(myFile);
        int lineNum = 0;
        String var;
        String value;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            char[] letters = line.toCharArray();
            lineNum++;
            if (line.equals("") || letters[0] == '#') {
                continue;
            } else if (letters[0] == '\u0020') {
                checkEmptyLine(letters, lineNum);
            } else {
                for (char letter : letters) {
                    if (letter == '#') {
                        line = line.split("#", 2)[0];
                        break;
                    }
                }
                checkLine(letters, lineNum);
                var = line.split("=", 2)[0].replaceAll("\\s+", "");
                value = line.split("=", 2)[1].replaceAll("\\s+", "");
                data.put(var, value);
            }

        }
        myReader.close();
    }

    // check lines for invalid chars
    private void checkEmptyLine(char[] letters, int lineNum) throws Exception {
        for (char letter : letters) {
            if (letter != '\u0020') {
                if (letter == '#') {
                    break;
                } else {
                    throw new Exception("Invalid line at line " + lineNum + ".");
                }
            }
        }
    }

    // check if var doesn't have a value
    private void checkLine(char[] letters, int lineNum) throws Exception {
        if (!(new String(letters).contains("="))) {
            throw new Exception(
                    "Invalid var/value at line " + lineNum + " you need to have '=' between var and value.");
        }
    }

    // get data.
    public HashMap<String, String> getData() {
        return this.data;
    }
}