import java.io.IOException;
import java.text.DecimalFormat;

public abstract class HomeDevices{
    public boolean status;
    public boolean getStatus() {
        return status;
    }
    public String getStatusinStringFormat(){
        if(status){
            return "on";
        }
        else{
            return "off";
        }
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract void zreport(String timesituation) throws IOException;
}
