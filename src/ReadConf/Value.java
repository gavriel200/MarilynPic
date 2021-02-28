package ReadConf;

public class Value {

    private String name;
    private int REDvalue;
    private String REDtype;
    private int GREENvalue;
    private String GREENtype;
    private int BLUEvalue;
    private String BLUEtype;

    public Value(String name) {
        this.name = name;
    }

    // setters.
    public void setRED(String value) throws Exception {
        if (checkValueNoChange(value)) {
            this.REDvalue = 0;
            this.REDtype = "X";
        } else if (chcekValuePercent(value)) {
            this.REDvalue = Integer.parseInt(value.split("%")[0]);
            this.REDtype = "%";
        } else if (chcekValueNormalRGB(value)) {
            this.REDvalue = Integer.parseInt(value);
            this.REDtype = "RGB";
        } else {
            throw new Exception("Invalid value for RED at " + this.name);
        }
    }

    public void setGREEN(String value) throws Exception {
        if (checkValueNoChange(value)) {
            this.GREENvalue = 0;
            this.GREENtype = "X";
        } else if (chcekValuePercent(value)) {
            this.GREENvalue = Integer.parseInt(value.split("%")[0]);
            this.GREENtype = "%";
        } else if (chcekValueNormalRGB(value)) {
            this.GREENvalue = Integer.parseInt(value);
            this.GREENtype = "RGB";
        } else {
            throw new Exception("Invalid value for GREEN at " + this.name);
        }
    }

    public void setBLUE(String value) throws Exception {
        if (checkValueNoChange(value)) {
            this.BLUEvalue = 0;
            this.BLUEtype = "X";
        } else if (chcekValuePercent(value)) {
            this.BLUEvalue = Integer.parseInt(value.split("%")[0]);
            this.BLUEtype = "%";
        } else if (chcekValueNormalRGB(value)) {
            this.BLUEvalue = Integer.parseInt(value);
            this.BLUEtype = "RGB";
        } else {
            throw new Exception("Invalid value for BLUE at " + this.name);
        }
    }

    // check types.
    public boolean checkValueNoChange(String value) {
        if (value.toUpperCase().equals("X")) {
            return true;
        }
        return false;
    }

    public boolean chcekValuePercent(String value) {
        if (value.endsWith("%")) {
            if (isInteger(value.split("%")[0])) {
                if (Integer.parseInt(value.split("%")[0]) >= 0 && Integer.parseInt(value.split("%")[0]) <= 100) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean chcekValueNormalRGB(String value) {
        if (isInteger(value)) {
            if (Integer.parseInt(value) >= 0 && Integer.parseInt(value) <= 255) {
                return true;
            }
        }
        return false;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    // get value name.
    public String toString() {
        return this.name;
    }

    // getters.
    public int getREDvalue() {
        return this.REDvalue;
    }

    public String getREDtype() {
        return this.REDtype;
    }

    public int getGREENvalue() {
        return this.GREENvalue;
    }

    public String getGREENtype() {
        return this.GREENtype;
    }

    public int getBLUEvalue() {
        return this.BLUEvalue;
    }

    public String getBLUEtype() {
        return this.BLUEtype;
    }

    public void printALL() {
        System.out.println(" - " + this.name + " RED:(" + this.REDtype + "," + this.REDvalue + ") GREEN:("
                + this.GREENtype + "," + this.GREENvalue + ") BLUE:(" + this.BLUEtype + "," + this.BLUEvalue + ")");
    }
}