import java.io.IOException;

public class SmartLamp extends Lamps{
    public SmartLamp(String name,String status, String kelvin, String brightness) {
        this.name  = name ;
        if(status.equals("On")){
        this.status=true;}
        else if(status.equals("Off")){
            this.status=false;}
        this.kelvin = Integer.parseInt(kelvin);
        this.brightness = Integer.parseInt(brightness);// default kelvin value
        ; // default brightness value
    }
    public SmartLamp(String name){
        this.name  = name;
        status = false;
        kelvin = 4000;
        brightness = 100;
    }
    public SmartLamp(String name, String status){
        this.name  = name;
        if(status.equals("On")){
            this.status=true;}
        else if(status.equals("Off")){
            this.status=false;}
        kelvin = 4000;
        brightness = 100;
    }
    @Override
    public void zreport(String timesituation) throws IOException {
        ReaderAndWriter.filewriter("Smart Lamp "+ name + " is " + getStatusinStringFormat()+" and its kelvin value is " + kelvin + "K with " + brightness + "% brightness, and its time to switch its status is "+ timesituation + ".");
    }





}



