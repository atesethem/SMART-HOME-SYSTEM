import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;

public class SmartCamera extends HomeDevices {
    public String name;//the name of the camera
    public double storageUsed;// the storage of the camera
    public double megabytesPerMinute;//  megabytes used per minute
    public  LocalDateTime timeforclass; // when the device is added

    SmartCamera(String name, double megabytesperMinute) {
        this.name= name;//it shows device's name
        this.megabytesPerMinute= megabytesperMinute; // default megabytes per minute
        status = false;// device is closed for now
        timeforclass = TimeAdjuster.getLocalDateTime();// it is for getting time for opening
        storageUsed = 0; // default storage used
        timeforclass = TimeAdjuster.getLocalDateTime();// it is for getting time for opening
    }
    SmartCamera(String name, double megabytesperMinute, String Status){
        this.name= name;//it shows device's name
        megabytesPerMinute= megabytesperMinute; // default megabytes per minute
        if(Status.equals("On")){
            status = true;
        }
        else if(Status.equals("Off")){
            status = false;
        }
        timeforclass = TimeAdjuster.getLocalDateTime();// it is for getting time for opening
        storageUsed = 0; // default storage used

    }

    public double getStorageUsed() {
        return storageUsed;//to get storage in other functions
    }

    public double getMegabytesPerMinute() {
       return megabytesPerMinute; // to get megabytesPerMinute in other functions
    }
    public String getName() {
        return name;
    }
    public int minutes(LocalDateTime now){
        if(!status){
            return 0;
        }
        else{
            Duration zamanfarki = Duration.between(timeforclass,now);
            return (int) zamanfarki.toMinutes();
        }
    }
    public void updateStorageUsed(double minutes) {
        storageUsed += megabytesPerMinute * minutes;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void zreport(String timesituation) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String rounded = decimalFormat.format(storageUsed);
        ReaderAndWriter.filewriter("Smart Camera "+ name + " is " + getStatusinStringFormat()+" and used " + rounded + " MB of storage so far (excluding current status), and its time to switch its status is " + timesituation + ".");
    }
}
