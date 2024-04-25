import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;


public class SmartPlug extends HomeDevices {
    public String name;
    public double voltage;
    public double ampere;
    public boolean plugstatus;
    public double totalEnergyConsumption;
    public LocalDateTime timeforclass;

    SmartPlug(String name) {
        this.name = name;
        status = false;
        ampere = 0; // default ampere value
        plugstatus = false; // default plugstatus vaule
        voltage = 220; // default voltage value
        totalEnergyConsumption = 0; // default total energy consumption
        timeforclass = TimeAdjuster.localDateTime;
    }
    SmartPlug(String name, String Status) {
        this.name = name;
        if(Status.equals("On")){
            status = true;
        }
        else if(Status.equals("Off")){
            status = false;
        }
        ampere = 0; // default ampere value
        plugstatus = false; // default plugstatus vaule
        voltage = 220; // default voltage value
        totalEnergyConsumption = 0; // default total energy consumption
        timeforclass = TimeAdjuster.localDateTime;
    }

    SmartPlug(String name, String Status, Double Ampere) {
        this.name = name;
        if(Status.equals("On")){
            status = true; // it shows if plugged in status
        }
        else if(Status.equals("Off")){
            status = false;
        } // it shows if plugged in status
        ampere = Ampere; // default ampere value
        plugstatus = false; // default plugstatus vaule
        voltage = 220; // default voltage value
        totalEnergyConsumption = 0; // default total energy consumption
        timeforclass = TimeAdjuster.localDateTime;
    }

    void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public void setAmpere(double Ampere) {
        ampere = Ampere;
    }
    public void setPlugstatus(boolean plugstatus) {this.plugstatus = plugstatus; }


    public void setnewtime(LocalDateTime time){
        timeforclass = time;
    }

    public double minutes(LocalDateTime now) {
            //System.out.println(timeforclass);
            Duration zamanfarki = Duration.between(timeforclass, now);
            return (double) zamanfarki.toMinutes();

    }

    public  void updateTotalEnergyConsumption(double value) {
        if (plugstatus) {
            totalEnergyConsumption += value;
        }
        /**
     * Updates the total energy consumption of the smart plug device
                */
    }

    public String getName() {
        return name;
    }
    /**
     * Gets the name of the smart plug device.
     * @return the name of the smart plug device
     */

    public void setName(String name) {
        this.name = name;
    }
    public double getEnergy(){
        return totalEnergyConsumption;
    }



    @Override
    public void zreport(String timesituation) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String rounded = decimalFormat.format(totalEnergyConsumption);
        ReaderAndWriter.filewriter("Smart Plug "+ name + " is " + getStatusinStringFormat()+" and consumed " + rounded + "W so far (excluding current device), and its time to switch its status is " + timesituation + ".");

    }
}




