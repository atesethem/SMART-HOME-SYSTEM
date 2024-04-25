import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
/**
 * A class to adjust time and control smart devices based on their set schedules.
 */
public class TimeAdjuster {
    public String timeString;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
    public static LocalDateTime localDateTime;
    public static LocalDateTime getLocalDateTime(){
        LocalDateTime now = localDateTime;
        return now;
    }
    public void setinitialtime(String initialtime) throws IOException {
        try {
            timeString = initialtime;
            localDateTime = LocalDateTime.parse(timeString, formatter);
        }
        catch(DateTimeParseException e) {
            ReaderAndWriter.filewriter("You wrote the wrong date format. Correct format : \"yyyy-MM-dd_HH:mm:ss\" ");
        }
    }
    public static LocalDateTime converttodate(String timestring){
        LocalDateTime converted = LocalDateTime.parse(timestring, formatter);
        return converted;
    }
    public void timechanger(LocalDateTime localDateTime,int minutes){
        timeString = String.valueOf(localDateTime.plusMinutes(minutes));
        TimeAdjuster.localDateTime = LocalDateTime.parse(timeString);}
     public void SkipMinutes(String valuefromfile) throws IOException {
        if(Integer.parseInt(valuefromfile)>0){
        timechanger(TimeAdjuster.localDateTime, Integer.parseInt(valuefromfile));}
        //System.out.println(TimeAdjuster.localDateTime);
         Iterator<Object[]> iterator = Arrays.combinedList.iterator();
         while (iterator.hasNext()) {
             Object[] liste = iterator.next();
             LocalDateTime newtime = (LocalDateTime) liste[1];
             String name = (String) liste[0];
             if (newtime.isBefore(TimeAdjuster.localDateTime) || newtime == TimeAdjuster.localDateTime) {
                 controllerandadder(name, newtime);
                 Arrays.oldlist.add(name);
                 iterator.remove();

             }
         }Arrays.sorterofcombinedlist();
        //System.out.println(Arrays.combinedList);
    }
    public static void setSwitchTime(String theline) throws IOException {
        try {
            String[] words = theline.split("\t");
            String thename = words[1];
            if(Arrays.Alldevices.contains(thename)){
            String thedate = words[2];
            LocalDateTime converted = converttodate(thedate);
            if(converted.isAfter(localDateTime)){
            Arrays.combinedList.add(new Object[]{thename, converted});
            Arrays.sorterofcombinedlist();
            Arrays.switchlist.add(thename);
            }
        }
            else{
                ReaderAndWriter.filewriter("There is no device named: "+ thename);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            ReaderAndWriter.filewriter("There is/are missing value(s)");
        }
    }
    public static void controllerandadder(String name, LocalDateTime thetime) throws IOException {
        boolean control = true;
        Duration timeinterval = Duration.between(thetime,TimeAdjuster.localDateTime);
        double timeintervalminutes = timeinterval.toMinutes();
        if(control){
            for(SmartLamp mylamp: Arrays.SmartLamps){
                if(mylamp.name.equals(name)){
                    if(mylamp.getStatus() == false){
                     mylamp.setStatus(true);
                        control=false;}

                    if(mylamp.getStatus() == true){
                        mylamp.setStatus(false);
                        control=false;
                    }

                }
            }

        }
        if(control){
            for(SmartColorLamp myColorlamp: Arrays.SmartColorLamps){
                if(myColorlamp.name.equals(name)){
                    if(myColorlamp.getStatus() == false){
                        myColorlamp.setStatus(true);
                        control=false;}
                    if(myColorlamp.getStatus() == true){
                        myColorlamp.setStatus(false);
                        control=false;}
                }
            }

        }
        if(control){
            for(SmartPlug myplug: Arrays.SmartPlugs){
                if(myplug.name.equals(name)){
                    if(!myplug.getStatus()){
                        myplug.setStatus(true);
                        myplug.setnewtime(thetime);
                        control=false;
                    }
                    else{
                        double minutes = myplug.minutes(TimeAdjuster.localDateTime)-timeintervalminutes;
                        double energy = myplug.ampere * myplug.voltage * (minutes / 60);
                        myplug.updateTotalEnergyConsumption(energy);
                        myplug.setnewtime(thetime);
                        myplug.setStatus(false);
                        control=false;
                    }
                }
            }

        }
        if(control){
            for(SmartCamera mycamera: Arrays.SmartCameras){
                if(mycamera.name.equals(name)){
                    if(!mycamera.getStatus()){
                        mycamera.setStatus(true);
                        mycamera.timeforclass = thetime;
                        control=false;
                    }
                    else{
                        mycamera.status = false;
                        double theminutes = mycamera.minutes(TimeAdjuster.localDateTime)-timeintervalminutes;
                        mycamera.timeforclass = thetime;
                        double megabytes =  (theminutes*(mycamera.getMegabytesPerMinute()));
                        mycamera.updateStorageUsed(megabytes);
                        mycamera.setStatus(false);
                        control=false;
                    }
                }
            }


        }
        if(control){
            ReaderAndWriter.filewriter("There is no device named: " + name);
        }
    }
    public static void Settime(String theline) throws IOException {
        try {
            String[] words = theline.split("\t");
            String time = words[1];
            Arrays.sorterofcombinedlist();
            if(localDateTime.isBefore(LocalDateTime.parse(time, formatter))) {
                TimeAdjuster.localDateTime = LocalDateTime.parse(time, formatter);
                Iterator<Object[]> iterator = Arrays.combinedList.iterator();
                while (iterator.hasNext()) {
                    Object[] liste = iterator.next();
                    LocalDateTime newtime = (LocalDateTime) liste[1];
                    String name = (String) liste[0];
                    if (newtime.isBefore(TimeAdjuster.localDateTime) || newtime == TimeAdjuster.localDateTime) {
                        controllerandadder(name, newtime);
                        Arrays.oldlist.add(name);
                        iterator.remove();

                    }
                }Arrays.sorterofcombinedlist();
            }
            else{
                ReaderAndWriter.filewriter("ERROR: Time cannot be reversed!");
            }
        } catch (DateTimeParseException e) {
            ReaderAndWriter.filewriter("ERROR: Time format is not correct!");
        } catch (NumberFormatException e) {
            ReaderAndWriter.filewriter("You should write variables in suitable format.");
        }

    }
    public static void nop() throws IOException {
        if(Arrays.combinedList.size()>0){
            LocalDateTime time = (LocalDateTime) Arrays.combinedList.get(0)[1];
            localDateTime = time;
            Iterator<Object[]> iterator = Arrays.combinedList.iterator();
            while (iterator.hasNext()) {
                Object[] liste = iterator.next();
                LocalDateTime newtime = (LocalDateTime) liste[1];
                String name = (String) liste[0];
                if (newtime.isBefore(TimeAdjuster.localDateTime) || newtime == TimeAdjuster.localDateTime) {
                    controllerandadder(name, newtime);
                    Arrays.oldlist.add(name);
                    iterator.remove();

                    }
            }Arrays.sorterofcombinedlist();
        }
        else{
            ReaderAndWriter.filewriter("ERROR: There is nothing to switch!");
        }

    }
}
