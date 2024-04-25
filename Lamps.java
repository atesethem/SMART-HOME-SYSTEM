import java.io.IOException;

public abstract class Lamps extends HomeDevices {
    public String name;
    public int brightness;
    public  int kelvin;
    /** The color temperature of the lamp, in Kelvin (2000-6500) */
    public String getKelvinforWriting() {
        return kelvin + "K";
    }

    public int getKelvin() {
        return kelvin;
    }

    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }
    public  void setName(String name) {
        this.name = name;
    }

    public int getBrightness() {
        return brightness;
    }

    public String getName() {
        return name;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public static void ChangeKelvin(String theline) throws IOException {
        /**
         * Changes the color temperature of a smart lamp with the specified name to the specified value.
         * Writes an error message to the log file if the name does not correspond to a smart lamp.
         * it gives errors when the inputs dont provide some conditions.
         */
        String[] words = theline.split("\t");
        String name = words[1];
        String kelvin = words[2];
        boolean control = false;
        try {
            for (SmartLamp mylamp : Arrays.SmartLamps) {
                if (name.equals(mylamp.getName())) {
                    if (2000 <= Integer.parseInt(kelvin) && Integer.parseInt(kelvin) <= 6500) {
                        mylamp.setKelvin(Integer.parseInt(kelvin));
                        control = true;
                        break;
                    } else {
                        ReaderAndWriter.filewriter("ERROR: Kelvin value must be in range of 2000K-6500K!");
                        control = true;
                        break;
                    }
                }
            }
            for (SmartColorLamp mylamp : Arrays.SmartColorLamps) {
                if (name.equals(mylamp.getName())) {
                    if (mylamp.getColorMode() == false) {
                        if (2000 <= Integer.parseInt(kelvin) && Integer.parseInt(kelvin) <= 6500) {
                            mylamp.setKelvin(Integer.parseInt(kelvin));
                            control=true;
                            break;
                        } else {
                            ReaderAndWriter.filewriter("ERROR: Kelvin value must be in range of 2000K-6500K!");
                            control=true;
                            break;
                        }
                    } else {
                        ReaderAndWriter.filewriter("This lamp's color mode is active.");
                        control=true;
                        break;
                    }
                }
            }
            if(!control){
                ReaderAndWriter.filewriter("ERROR: This device is not a smart lamp!");
            }

        } catch (NumberFormatException e) {
            ReaderAndWriter.filewriter("You should give the Kelvin as Integer.");
        }
    }

    public static void ChangeBrightness(String theline) throws IOException {
        try {
            String[] words = theline.split("\t");
            String name = words[1];
            String brightness = words[2];
            boolean control = false;
            if (!control) {
                for (SmartLamp mylamp : Arrays.SmartLamps) {
                    if (name.equals(mylamp.getName())) {
                        if (0 <= Integer.parseInt(brightness) && Integer.parseInt(brightness) <= 100) {
                            mylamp.setBrightness(Integer.parseInt(brightness));
                            control = true;
                            break;
                        } else {
                            ReaderAndWriter.filewriter("ERROR: Brightness value must be in range of 0-100!");
                            control = true;
                            break;
                        }
                    }
                }
            }
            if(!control){
                ReaderAndWriter.filewriter("ERROR: This device is not a smart lamp!");
            }
        }
        catch (NumberFormatException e) {
            ReaderAndWriter.filewriter("You should give the brightness as Integer.");
        }
    }
    public static void ChangeWhite(String theline) throws IOException {
        String[] words = theline.split("\t");
        try{
    String name = words[1];
    String kelvin = words[2];
    String brightness = words[3];
    Boolean control = false;
    if(!control){
        boolean kelvincontroller = 2000 <= Integer.parseInt(kelvin) && Integer.parseInt(kelvin) <= 6500;
        boolean brightnesscontroller = 0 <= Integer.parseInt(brightness) && Integer.parseInt(brightness) <= 100;
        for(SmartLamp mylamp:Arrays.SmartLamps){
        if (name.equals(mylamp.getName())) {
            if (kelvincontroller) {
                if (brightnesscontroller) {
                    mylamp.setKelvin(Integer.parseInt(kelvin));
                    mylamp.setBrightness(Integer.parseInt(brightness));
                    control = true;
                } else {
                    ReaderAndWriter.filewriter("ERROR: Brightness must be in range of 0%-100%!");
                    control = true;
                }

            } else {
                ReaderAndWriter.filewriter("ERROR: Kelvin value must be in range of 2000K-6500K!");
                control = true;
            }

        }
    }
        for(SmartColorLamp mylamp:Arrays.SmartColorLamps) {
        if (name.equals(mylamp.getName())) {
                if (kelvincontroller) {
                    if (brightnesscontroller) {
                        mylamp.setColorMode(false);
                        mylamp.setKelvin(Integer.parseInt(kelvin));
                        mylamp.setBrightness(Integer.parseInt(brightness));
                        control = true;
                    } else {
                        ReaderAndWriter.filewriter("ERROR: Brightness must be in range of 0%-100%!");
                        control = true;
                    }

                } else {
                    ReaderAndWriter.filewriter("ERROR: Kelvin value must be in range of 2000K-6500K!");
                    control = true;
                }
            }

        }
    }
    if(!control) {
        ReaderAndWriter.filewriter("ERROR: This device is not a smart lamp!");
    }
}
        catch (NumberFormatException e){
            ReaderAndWriter.filewriter("You should write variables as suitable format.");
        }
        }

    public static void ChangeColorcode(String theline) throws IOException {
        try {
            String[] words = theline.split("\t");
            String name = words[1];
            String colorcode = words[2];
            boolean control = true;
            if (Arrays.SmartColorLamps.size() > 0) {
                 for (SmartColorLamp mylamp : Arrays.SmartColorLamps) {
                     if (name.equals(mylamp.getName())) {
                         if (mylamp.getColorMode() == true) {
                             if (SmartColorLamp.controller(colorcode)) {
                                 mylamp.setColorCode(colorcode);
                                 break;
                             } else {
                                 ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                             }
                         } else {
                             ReaderAndWriter.filewriter("This lamp's color code is not active");
                             break;
                         }
                     } else {
                         control = false;
                     }
                 }
                if(!control) {
                    ReaderAndWriter.filewriter("ERROR: This device is not a smart color lamp!");
                }
            }
        else{ReaderAndWriter.filewriter("ERROR: This device is not a smart color lamp!");}
    }
        catch (NumberFormatException e){
            ReaderAndWriter.filewriter("You should write variables as suitable format.");
        }
    }
    public static void ChangeColor(String theline) throws IOException {
        try {
            String[] words = theline.split("\t");
            String name = words[1];
            String colorcode = words[2];
            String brightness = words[3];
            boolean control = true;
            if(Arrays.SmartColorLamps.size()>0){
            for (SmartColorLamp mylamp : Arrays.SmartColorLamps) {
                if (name.equals(mylamp.getName())) {
                        if (SmartColorLamp.controller(colorcode)) {
                            if (Integer.parseInt(brightness) >= 0 && Integer.parseInt(brightness) <= 100) {
                                mylamp.setColorMode(true);
                                mylamp.setColorCode(colorcode);
                                mylamp.setBrightness(Integer.parseInt(brightness));
                                control=true;
                                break;
                            } else {
                                ReaderAndWriter.filewriter("ERROR: Brightness must be in range of 0%-100%!");
                                control=true;
                                break;
                            }
                        }
                        else{
                            control=true;
                        }
                    }
                else {control = false;}
                }
            }
            else{
                ReaderAndWriter.filewriter("ERROR: This device is not a smart color lamp!");
            }
            if(!control){
                ReaderAndWriter.filewriter("ERROR: This device is not a smart color lamp!");
            }
        }
        catch (NumberFormatException e){
            ReaderAndWriter.filewriter("You should write variables as suitable format.");
        }
    }


    public abstract void zreport(String timesituation) throws IOException;
}
