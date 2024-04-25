import java.io.IOException;

public class SmartColorLamp extends Lamps {
    public String colorCode;
    public boolean colorMode;

    public SmartColorLamp(String name) {
        this.name = name;
        this.status = false;
        this.kelvin =4000;
        brightness=100;
        colorMode = false;
    }
    public SmartColorLamp(String name,String status){
        this.name = name;
        if(status.equals("On")){
            this.status=true;}
        else if(status.equals("Off")){
            this.status=false;}
        kelvin =4000;
        brightness=100;
        colorMode = false;
    }
    public SmartColorLamp(String name, String status, String kelvinorcolorcode, String brightness){
        this.name = name;
        if(status.equals("On")){
            this.status=true;}
        else if(status.equals("Off")){
            this.status=false;}
        if(!checker(kelvinorcolorcode)){
            kelvin= Integer.parseInt(kelvinorcolorcode);
        }
        else{
            colorCode = kelvinorcolorcode;
        }

        this.brightness= Integer.parseInt(brightness);
        if(checker(kelvinorcolorcode)){
            colorMode = true;
        }
        else{
            colorMode= false;
        }
    }
    public  Boolean getColorMode(){ return colorMode;}
    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {this.colorCode = colorCode;}

    public void setColorMode(boolean colorMode) {
        this.colorMode = colorMode;
    }
    public static boolean checker(String kelvinorcolorcode){
        if(kelvinorcolorcode.substring(0,2).equals("0x")){
            return true;
        }
        else{
            return false;
        }
    }
    public void setBrightness(int thebrightness){brightness = thebrightness;}
    public static boolean controller(String kelvinorcolorcode) throws IOException {
        if(kelvinorcolorcode.substring(0,2).equals("0x")){
            char[] chars = kelvinorcolorcode.split("x")[1].toCharArray();
            if(chars.length>6){
                ReaderAndWriter.filewriter("ERROR: Color code value must be in range of 0x0-0xFFFFFF!");
                return false;
            }
            char[] karakterler = {'A', 'B', 'C', 'D', 'E', 'F', '0', '1', '2', '3', '4', '5', '6'};
            String karakterler1 = new String(karakterler);
            for(char word : chars ){
                if(karakterler1.contains(String.valueOf(word))) {
                    continue;
                }
                    else{
                    ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                        return false;

                    }

            }
        }
        else {
            try {
                if (2000 <= Integer.parseInt(kelvinorcolorcode) && Integer.parseInt(kelvinorcolorcode) <= 6500) {
                    return true;
                } else {
                    ReaderAndWriter.filewriter("ERROR: Kelvin value must be in range of 2000K-6500K!");
                    return false;
                }
            }
            catch(NumberFormatException e){
                ReaderAndWriter.filewriter(kelvinorcolorcode + "format is not suitable");
                return false;
            }

        }
        return true;
    }
    @Override
    public void zreport(String timesituation) throws IOException {
        if(!colorMode) {
            ReaderAndWriter.filewriter("Smart Color Lamp " + name + " is " + getStatusinStringFormat() + " and its color value is " + kelvin + "K with " + brightness + "% brightness, and its time to switch its status is " + timesituation  + ".");
        }
        else{
            ReaderAndWriter.filewriter("Smart Color Lamp " + name + " is " + getStatusinStringFormat() + " and its color value is " + colorCode + " with " + brightness + "% brightness, and its time to switch its status is " + timesituation  + ".");

        }
    }

}




