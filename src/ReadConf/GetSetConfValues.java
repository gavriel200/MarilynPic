package ReadConf;

import java.util.HashMap;

public class GetSetConfValues {

    private Value[] values = { new Value("TL"), new Value("TR"), new Value("DL"), new Value("DR") };

    // read conf file.
    public GetSetConfValues(String dir) throws Exception {
        ReadConfFile confFile = new ReadConfFile(dir);
        confFile.readFile();
        checkIfVars(confFile.getData());
        checkIfValues(confFile.getData());
        putValues(confFile.getData());
    }

    // check if all varubales exist.
    public void checkIfVars(HashMap<String, String> data) throws Exception {
        for (Value value : this.values) {
            if (!(data.containsKey(value.toString()))) {
                throw new Exception("Missing var " + value.toString());
            }
        }
    }

    // check that all the varuables have data.
    public void checkIfValues(HashMap<String, String> data) throws Exception {
        int commas;
        char[] letters;
        for (Value value : this.values) {
            commas = 0;
            letters = data.get(value.toString()).toCharArray();
            if (data.get(value.toString()).equals("")) {
                throw new Exception("Missing value for " + value.toString());
            }
            for (char letter : letters) {
                if (letter == ',') {
                    commas++;
                }
            }
            if (commas != 2) {
                throw new Exception("Bad values for " + value.toString()
                        + ", the value should consist of three values seperated by 2 commas");
            }
        }
    }

    // set values.
    public void putValues(HashMap<String, String> data) throws Exception {
        for (Value value : this.values) {
            value.setRED(data.get(value.toString()).split(",", 3)[0]);
            value.setGREEN(data.get(value.toString()).split(",", 3)[1]);
            value.setBLUE(data.get(value.toString()).split(",", 3)[2]);
        }
    }

    // get values.
    public Value getTL() {
        for (Value value : this.values) {
            if (value.toString().equals("TL")) {
                return value;
            }
        }
        return null;
    }

    public Value getTR() {
        for (Value value : this.values) {
            if (value.toString().equals("TR")) {
                return value;
            }
        }
        return null;
    }

    public Value getDL() {
        for (Value value : this.values) {
            if (value.toString().equals("DL")) {
                return value;
            }
        }
        return null;
    }

    public Value getDR() {
        for (Value value : this.values) {
            if (value.toString().equals("DR")) {
                return value;
            }
        }
        return null;
    }
}
