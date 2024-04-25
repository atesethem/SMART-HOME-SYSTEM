import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Arrays {
    public static List<SmartLamp> SmartLamps = new ArrayList<>();
    public static List<SmartColorLamp> SmartColorLamps = new ArrayList<>();
    public static List<SmartPlug> SmartPlugs = new ArrayList<>();
    public static List<SmartCamera> SmartCameras = new ArrayList<>();
    public static List<String> Alldevices = new ArrayList<>();
    public static ArrayList<Object[]> combinedList = new ArrayList<>();
    public static List<String> zreport = new ArrayList<>();
    public static List<String> oldlist = new ArrayList<>();
    public static List<String> switchlist = new ArrayList<>();

    public static <I> void Addingelement(List<I> list, I element) {
        list.add(element);
    }

    public static void sorterofcombinedlist() {
        Collections.sort(Arrays.combinedList, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {

                if (o1[1] instanceof LocalDateTime && o2[1] instanceof LocalDateTime) {
                    LocalDateTime dt1 = (LocalDateTime) o1[1];
                    LocalDateTime dt2 = (LocalDateTime) o2[1];
                    if(dt2 == dt1){
                        int index1 = switchlist.indexOf(o1[0]);
                        int index2 = switchlist.indexOf(o2[0]);
                        return Integer.compare(index2, index1);
                    }
                    return dt1.compareTo(dt2);
                }
                return 0;

            }
        });
    }


    public static void adding(String theline) throws IOException {
        String[] words = theline.split("\t");
        Boolean truth = true;
        for (String device : Arrays.Alldevices) {
            if (words[2].equals(device)) {
                truth = false;
                if (words[1].equals("SmartLamp")) {
                    if (words.length < 3 || words.length > 6) {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    } else {
                        ReaderAndWriter.filewriter("ERROR: There is already a smart device with same name!");
                    }
                } else if (words[1].equals("SmartColorLamp")) {
                    if (words.length < 3 || words.length > 6) {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    } else {
                        ReaderAndWriter.filewriter("ERROR: There is already a smart device with same name!");
                    }
                } else if (words[1].equals("SmartPlug")) {
                    if (words.length < 3 || words.length > 5) {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    } else {
                        ReaderAndWriter.filewriter("ERROR: There is already a smart device with same name!");
                    }
                } else if (words[1].equals("SmartCamera")) {
                    if (words.length < 4 || words.length > 5) {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    } else {
                        ReaderAndWriter.filewriter("ERROR: There is already a smart device with same name!");
                    }
                } else {
                    ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                }

            }
        }
        if (truth) {
            if (words[1].equals("SmartLamp")) {
                if (words.length == 3) {
                    SmartLamp mylamp = new SmartLamp(words[2]);
                    Arrays.SmartLamps.add(mylamp);
                    Arrays.Alldevices.add(words[2]);
                } else if (words.length == 4) {
                    if (words[3].equals("On") || words[3].equals("Off")) {
                        SmartLamp mylamp = new SmartLamp(words[2], words[3]);
                        Arrays.SmartLamps.add(mylamp);
                        Arrays.Alldevices.add(words[2]);
                    } else {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    }
                } else if (words.length == 6) {
                    try {
                        if (words[3].equals("On") || words[3].equals("Off")) {
                            if (2000 <= Integer.parseInt(words[4]) && Integer.parseInt(words[4]) <= 6500) {
                                if (0 <= Integer.parseInt(words[5]) && Integer.parseInt(words[5]) <= 100) {
                                    SmartLamp mylamp = new SmartLamp(words[2], words[3], words[4], words[5]);
                                    Arrays.SmartLamps.add(mylamp);
                                    Arrays.Alldevices.add(words[2]);
                                } else {
                                    ReaderAndWriter.filewriter("ERROR: Brightness must be in range of 0%-100%!");
                                }
                            } else {
                                ReaderAndWriter.filewriter("ERROR: Kelvin value must be in range of 2000K-6500K!");
                            }
                        } else {
                            ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                        }
                    } catch (NumberFormatException e) {
                        ReaderAndWriter.filewriter("You should give the values as integer");
                    }


                } else {
                    ReaderAndWriter.filewriter("You entered extra value/s");
                }
            }
            if (words[1].equals("SmartColorLamp")) {
                if (words.length == 3) {
                    SmartColorLamp mylamp = new SmartColorLamp(words[2]);
                    Arrays.SmartColorLamps.add(mylamp);
                    Arrays.Alldevices.add(words[2]);
                } else if (words.length == 4) {
                    if (words[3].equals("On") || words[3].equals("Off")) {
                        SmartColorLamp mylamp = new SmartColorLamp(words[2], words[3]);
                        Arrays.SmartColorLamps.add(mylamp);
                        Arrays.Alldevices.add(words[2]);
                    } else {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    }
                } else if (words.length == 6) {
                    try {
                        if (words[3].equals("On") || words[3].equals("Off")) {
                            if (SmartColorLamp.controller(words[4])) {
                                SmartColorLamp mylamp = new SmartColorLamp(words[2], words[3], words[4], words[5]);
                                Arrays.SmartColorLamps.add(mylamp);
                                Arrays.Alldevices.add(words[2]);
                            } else {

                            }
                        } else {
                            ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                        }
                    } catch (NumberFormatException e) {
                        ReaderAndWriter.filewriter("You should give the values as suitable format");
                    }
                } else {
                    ReaderAndWriter.filewriter("You entered extra value/s");
                }
            }
            if (words[1].equals("SmartPlug")) {
                if (words.length == 3) {
                    SmartPlug myplug = new SmartPlug(words[2]);
                    Arrays.SmartPlugs.add(myplug);
                    Arrays.Alldevices.add(words[2]);
                } else if (words.length == 4) {
                    if (words[3].equals("On") || words[3].equals("Off")) {
                        SmartPlug myplug = new SmartPlug(words[2], words[3]);
                        Arrays.SmartPlugs.add(myplug);
                        Arrays.Alldevices.add(words[2]);
                    } else {
                        ReaderAndWriter.filewriter("ERROR: Erroneous command!");
                    }
                } else if (words.length == 5) {
                    try {
                        if (words[3].equals("On") || words[3].equals("Off")) {
                            if (Double.parseDouble(words[4]) > 0) {
                                SmartPlug myplug = new SmartPlug(words[2], words[3], Double.parseDouble(words[4]));
                                Arrays.SmartPlugs.add(myplug);
                                Arrays.Alldevices.add(words[2]);
                            } else {
                                ReaderAndWriter.filewriter("ERROR: Ampere value must be a positive number!");
                            }
                        }
                    } catch (NumberFormatException e) {
                        ReaderAndWriter.filewriter("You should give the values as double/integer");
                    }
                } else {
                    ReaderAndWriter.filewriter("You entered extra value/s");
                }
            }
            if (words[1].equals("SmartCamera")) {
                if (words.length == 4) {
                    try {
                        if (Double.parseDouble(words[3]) > 0) {
                            SmartCamera mycamera = new SmartCamera(words[2], Double.parseDouble(words[3]));
                            Arrays.SmartCameras.add(mycamera);
                            Arrays.Alldevices.add(words[2]);
                        } else {
                            ReaderAndWriter.filewriter("ERROR: Megabyte value must be a positive number!");
                        }
                    } catch (NumberFormatException e) {
                        ReaderAndWriter.filewriter("You should give the values as suitable format");
                    }
                } else if (words.length == 5) {
                    try {
                        if (words[4].equals("On") || words[4].equals("Off")) {
                            if (Double.parseDouble(words[3]) > 0) {
                                SmartCamera mycamera = new SmartCamera(words[2], Double.parseDouble(words[3]), words[4]);
                                Arrays.SmartCameras.add(mycamera);
                                Arrays.Alldevices.add(words[2]);
                            } else {
                                ReaderAndWriter.filewriter("You should give the values as suitable format");
                            }
                        }
                    } catch (NumberFormatException e) {
                        ReaderAndWriter.filewriter("You should give the values as suitable format");
                    }
                }

            }

        }
    }
    /**
     * Switch method commands are debugged as you can see following code.
     * There were a lot of errors to handle until we solved.
     * @param theline
     * @throws IOException
     */

    public static void switchmethod(String theline) throws IOException {
        String[] words = theline.split("\t");
        int index = -1;
        if (Arrays.Alldevices.contains(words[1])) {
            for (int i = 0; i < Arrays.SmartLamps.size(); i++) {
                if (Arrays.SmartLamps.get(i).name.equals(words[1])) {
                    SmartLamp mylamp = Arrays.SmartLamps.get(i);
                    index = i;
                    if (words[2].equals("On")) {
                        if (!mylamp.status) {
                            mylamp.status = true;
                        } else {

                            ReaderAndWriter.filewriter("ERROR: This device is already switched on!");
                        }
                    }
                    if (words[2].equals("Off")) {
                        if (mylamp.status) {
                            mylamp.status = false;
                        } else {
                            ReaderAndWriter.filewriter("ERROR: This device is already switched off!");
                        }
                    }
                }
            }
            for (int i = 0; i < Arrays.SmartColorLamps.size(); i++) {
                if (Arrays.SmartColorLamps.get(i).name.equals(words[1])) {
                    SmartColorLamp mycolorlamp = Arrays.SmartColorLamps.get(i);
                    index = i;
                    if (words[2].equals("On")) {
                        if (!mycolorlamp.status) {
                            mycolorlamp.status = true;
                        } else {
                            ReaderAndWriter.filewriter("ERROR: This device is already switched on!");
                        }
                    }
                    if (words[2].equals("Off")) {
                        if (mycolorlamp.status) {
                            mycolorlamp.status = false;

                        } else {
                            ReaderAndWriter.filewriter("ERROR: This device is already switched off!");
                        }
                    }
                }
            }
            for (int i = 0; i < Arrays.SmartPlugs.size(); i++) {
                if (Arrays.SmartPlugs.get(i).name.equals(words[1])) {
                    SmartPlug myplug = Arrays.SmartPlugs.get(i);
                    index = i;
                    if (words[2].equals("On")) {
                        if (!myplug.status) {
                            myplug.status = true;
                            myplug.timeforclass = TimeAdjuster.localDateTime;
                        } else {

                            ReaderAndWriter.filewriter("ERROR: This device is already switched on!");
                        }
                    }
                    if (words[2].equals("Off")) {
                        if (myplug.status) {
                            myplug.status = false;
                            double minutes = myplug.minutes(TimeAdjuster.localDateTime);
                            double energy = (myplug.ampere) * (myplug.voltage) * (minutes / 60);
                            myplug.updateTotalEnergyConsumption(energy);
                            myplug.timeforclass = TimeAdjuster.localDateTime;
                        } else {
                            ReaderAndWriter.filewriter("ERROR: This device is already switched off!");
                        }
                    }
                }
            }
            for (int i = 0; i < Arrays.SmartCameras.size(); i++) {
                //SmartCamera currentCamera = Arrays.SmartCameras.get(i);
                if (SmartCameras.get(i).name.equals(words[1])) {
                    SmartCamera mycamera = Arrays.SmartCameras.get(i);
                    index = i;
                    if (words[2].equals("On")) {
                        if (!mycamera.status) {
                            mycamera.status = true;
                            mycamera.timeforclass = TimeAdjuster.localDateTime;
                        } else {

                            ReaderAndWriter.filewriter("ERROR: This device is already switched on!");
                        }
                    }
                    if (words[2].equals("Off")) {
                        if (mycamera.status) {
                            mycamera.setStatus(false);
                            double theminutes = mycamera.minutes(TimeAdjuster.localDateTime);
                            mycamera.timeforclass = TimeAdjuster.localDateTime;
                            double megabytes = theminutes * (mycamera.getMegabytesPerMinute());
                            mycamera.updateStorageUsed(megabytes);
                        } else {
                            ReaderAndWriter.filewriter("ERROR: This device is already switched off!");
                        }
                    }
                }
            }
        } else {
            ReaderAndWriter.filewriter("ERROR: There is not such a device!");
        }
    }

    /**
     * It checks plugedin conditions.
     * @param theline
     * @throws IOException
     */
    public static void PlugedIn(String theline) throws IOException {
        String[] words = theline.split("\t");
        Boolean control = true;
        if (words.length <= 3) {
            try {
                String name = words[1];
                int ampere = Integer.parseInt(words[2]);
                for (int i = 0; i < SmartPlugs.size(); i++) {
                    if (name.equals(SmartPlugs.get(i).getName())) {
                        if (ampere > 0) {
                            if (!SmartPlugs.get(i).plugstatus) {
                                SmartPlugs.get(i).setPlugstatus(true);
                                SmartPlugs.get(i).setAmpere(ampere);
                                control = true;
                                break;
                            } else {
                                ReaderAndWriter.filewriter("ERROR: There is already an item plugged in to that plug!");
                                control=true;
                                break;
                            }
                        } else {
                            ReaderAndWriter.filewriter("ERROR: Ampere value must be a positive number!");
                            control=true;
                            break;
                        }
                    }
                    else{
                        control = false;
                    }
                    }
                if(!control){
                    ReaderAndWriter.filewriter("ERROR: This device is not a smart plug!");
                }
            } catch (IndexOutOfBoundsException e) {
                ReaderAndWriter.filewriter("You entered missing data.");
            }
        } else {
            ReaderAndWriter.filewriter("You entered extra data.");
        }
    }

    /**
     * It checks plugedout conditions.
     * @param theline
     * @throws IOException
     */
    public static void PlugedOut(String theline) throws IOException {
        String[] words = theline.split("\t");
        Boolean control = true;
        if (words.length <= 2) {
            try {
                String name = words[1];
                for (int i = 0; i < SmartPlugs.size(); i++) {
                    if (name.equals(SmartPlugs.get(i).getName())) {
                        if (SmartPlugs.get(i).plugstatus == true) {
                            if (SmartPlugs.get(i).status == true) {
                                double minutes = SmartPlugs.get(i).minutes(TimeAdjuster.localDateTime);
                                double energy = (SmartPlugs.get(i).ampere) * (SmartPlugs.get(i).voltage) * (minutes / 60);
                                SmartPlugs.get(i).updateTotalEnergyConsumption(energy);
                                SmartPlugs.get(i).setnewtime(TimeAdjuster.localDateTime);
                                control = true;
                                break;
                            }
                            SmartPlugs.get(i).setPlugstatus(false);
                            control =true;
                            break;
                        } else {
                            ReaderAndWriter.filewriter("ERROR: This plug has no item to plug out from that plug!");
                            control =true;
                            break;
                        }
                    } else {
                        control = false;
                    }
                }
                if(!control){
                    ReaderAndWriter.filewriter("ERROR: This device is not a smart plug!");
                }
            } catch (IndexOutOfBoundsException e) {
                ReaderAndWriter.filewriter("You entered missing data.");
            }
        } else {
            ReaderAndWriter.filewriter("You entered extra data.");
        }
    }

    public static void ChangeName(String theline) throws IOException {
        try {
            String[] words = theline.split("\t");
            String previousname = words[1];
            String nextname = words[2];
            if (Arrays.Alldevices.contains(previousname) && !previousname.equals(nextname)) {
                if (!Arrays.Alldevices.contains(nextname)) {
                    for (SmartLamp mylamp : SmartLamps) {
                        if (previousname.equals(mylamp.getName())) {
                            mylamp.setName(nextname);
                            int index = Alldevices.indexOf(previousname);
                            if (index != -1) {
                                Alldevices.set(index, nextname);
                            }
                        }
                    }
                    for (SmartColorLamp mylamp : SmartColorLamps) {
                        if (previousname.equals(mylamp.getName())) {
                            mylamp.setName(nextname);
                            int index = Alldevices.indexOf(previousname);
                            if (index != -1) {
                                Alldevices.set(index, nextname);
                            }
                        }
                    }
                    for (SmartPlug myplug : SmartPlugs) {
                        if (previousname.equals(myplug.getName())) {
                            myplug.setName(nextname);
                            int index = Alldevices.indexOf(previousname);
                            if (index != -1) {
                                Alldevices.set(index, nextname);
                            }
                        }
                        }
                    for (SmartCamera mycamera : SmartCameras) {
                        if (previousname.equals(mycamera.getName())) {
                            mycamera.setName(nextname);
                            int index = Alldevices.indexOf(previousname);
                            if (index != -1) {
                                Alldevices.set(index, nextname);
                            }
                        }
                    }
                    for (Object[] myobject : combinedList) {
                        if (previousname.equals(myobject[0])) {
                            myobject[0] = nextname;
                        }
                    }
                } else {
                    ReaderAndWriter.filewriter("ERROR: There is already a smart device with same name!");
                }
            }
            else if(!Arrays.Alldevices.contains(previousname) && previousname.equals(nextname)){
                ReaderAndWriter.filewriter("ERROR: Both of the names are the same, nothing changed!");
            }
            else if(Arrays.Alldevices.contains(previousname) && previousname.equals(nextname)){
                ReaderAndWriter.filewriter("ERROR: Both of the names are the same, nothing changed!");
            }
            else {
                ReaderAndWriter.filewriter("ERROR: There is not such a device!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ReaderAndWriter.filewriter("ERROR: Erroneous command!");
        }
    }

    public static String stringtime(String nameofdevice) {
        for (int i = 0; i < combinedList.size(); i++) {
            Object[] theobject = combinedList.get(i);
            if (nameofdevice.equals(theobject[0])) {
                LocalDateTime realtime = (LocalDateTime) theobject[1];
                String thetime=realtime.format(TimeAdjuster.formatter);
                return (String) thetime;
            }
        }
        return "null";
    }

    public static void ZReport() throws IOException {
        zreport.clear();
        String thetime=TimeAdjuster.localDateTime.format(TimeAdjuster.formatter);
        ReaderAndWriter.filewriter("Time is:\t" + thetime);
        boolean control = false;
        if (combinedList.size() > 0) {
            for (int i = 0; i < combinedList.size(); i++) {
                Object[] theobject = combinedList.get(i);
                String thename = (String) theobject[0];
                for(int j = 0 ; j<SmartLamps.size();j++){
                if (SmartLamps.get(j).name.equals(thename)) {
                    SmartLamp mylamp = SmartLamps.get(j);
                    zreport.add(mylamp.name);
                    String namesituation = stringtime(thename);
                    mylamp.zreport(namesituation);
                }}
                for(int j = 0 ; j<SmartColorLamps.size();j++){
                if (SmartColorLamps.get(j).name.equals(thename)) {
                    SmartColorLamp mycolorlamp = SmartColorLamps.get(j);
                    zreport.add(mycolorlamp.name);
                    String namesituation = stringtime(thename);
                    mycolorlamp.zreport(namesituation);
                }}
                for(int j = 0 ; j<SmartPlugs.size();j++){
                if (SmartPlugs.get(j).name.equals(thename)) {
                    SmartPlug myplug = SmartPlugs.get(j);
                    zreport.add(myplug.name);
                    String namesituation = stringtime(thename);
                    myplug.zreport(namesituation);
                }}
                for(int j = 0 ; j<SmartCameras.size();j++){
                if (SmartCameras.get(j).name.equals(thename)) {
                    SmartCamera mycamera = SmartCameras.get(j);
                    zreport.add(mycamera.name);
                    String namesituation = stringtime(thename);
                    mycamera.zreport(namesituation);
                }}
            }
        }
        if(oldlist.size()>0) {
            for(int i = oldlist.size()-1; i>=0; i--){
                String thename = oldlist.get(i);
                if (!zreport.contains(thename)){
                    for (int j = 0; j < SmartLamps.size(); j++) {
                        SmartLamp mylamp = SmartLamps.get(j);
                        if (mylamp.name.equals(thename)) {
                            String namesituation = stringtime(thename);
                            zreport.add(mylamp.name);
                            mylamp.zreport(namesituation);
                        }
                    }
                    for (int j = 0; j < SmartColorLamps.size(); j++) {
                        SmartColorLamp mycolorlamp = SmartColorLamps.get(j);
                        if (mycolorlamp.name.equals(thename)) {
                            String namesituation = stringtime(thename);
                            zreport.add(mycolorlamp.name);
                            mycolorlamp.zreport(namesituation);
                        }
                    }
                    for (int j = 0; j < SmartPlugs.size(); j++) {
                        SmartPlug myplug = SmartPlugs.get(j);
                        if (myplug.name.equals(thename)) {
                            String namesituation = stringtime(thename);
                            zreport.add(myplug.name);
                            myplug.zreport(namesituation);
                        }
                    }
                    for (int j = 0; j < SmartCameras.size(); j++) {
                        SmartCamera mylamp = SmartCameras.get(j);
                        if (mylamp.name.equals(thename)) {
                            String namesituation = stringtime(thename);
                            zreport.add(mylamp.name);
                            mylamp.zreport(namesituation);
                        }
                    }
                }
            }
        }
        if (Alldevices.size() > 0) {
            for (int i = 0; i < Alldevices.size(); i++) {
                String thename = Alldevices.get(i);
                if (!zreport.contains(thename)){
                    for (int j = 0; j < SmartLamps.size(); j++) {
                        SmartLamp mylamp = SmartLamps.get(j);
                        if (mylamp.name.equals(thename)) {
                            String namesituation = stringtime(thename);
                            mylamp.zreport(namesituation);
                        }
                    }
                for (int j = 0; j < SmartColorLamps.size(); j++) {
                    SmartColorLamp mycolorlamp = SmartColorLamps.get(j);
                    if (mycolorlamp.name.equals(thename)) {
                        String namesituation = stringtime(thename);
                        mycolorlamp.zreport(namesituation);
                    }
                }
                for (int j = 0; j < SmartPlugs.size(); j++) {
                    SmartPlug myplug = SmartPlugs.get(j);
                    if (myplug.name.equals(thename)) {
                        String namesituation = stringtime(thename);
                        myplug.zreport(namesituation);
                    }
                }
                for (int j = 0; j < SmartCameras.size(); j++) {
                    SmartCamera mylamp = SmartCameras.get(j);
                    if (mylamp.name.equals(thename)) {
                        String namesituation = stringtime(thename);
                        mylamp.zreport(namesituation);
                    }
                }
            }
        }
        }
        else{
            ReaderAndWriter.filewriter("ERROR: Erroneous command!");
            }
        }
        public static void setInitialtime(String line) throws IOException {
        String[] words = line.split("\t");
        String command = words[0];
        String time = words[1];
            try {
                TimeAdjuster timeAdjuster = new TimeAdjuster();
                timeAdjuster.setinitialtime(line.split("\t")[1].trim());
                ReaderAndWriter.num = ReaderAndWriter.num - 1000;
                ReaderAndWriter.filewriter("SUCCESS: Time has been set to " + time + "!");


            } catch (ArrayIndexOutOfBoundsException e) {
                ReaderAndWriter.filewriter("You forgot to write initial time.");

            }
        }
        public static void remove(String line) throws IOException {
        try{
            String[] words = line.split("\t");
            String previousname = words[1];
            if (Arrays.Alldevices.contains(previousname)) {
                Iterator<SmartLamp> lampIterator = SmartLamps.iterator();
                while (lampIterator.hasNext()) {
                    SmartLamp mylamp = lampIterator.next();
                    if (previousname.equals(mylamp.getName())) {
                        ReaderAndWriter.filewriter("SUCCESS: Information about removed smart device is as follows:");
                        String namesituation = stringtime(mylamp.name);
                        mylamp.zreport(namesituation);
                        lampIterator.remove();
                        Alldevices.remove(mylamp.name);
                    }
                }
                Iterator<SmartColorLamp> colorLampIterator = SmartColorLamps.iterator();
                while (colorLampIterator.hasNext()) {
                    SmartColorLamp mylamp = colorLampIterator.next();
                    if (previousname.equals(mylamp.getName())) {
                        ReaderAndWriter.filewriter("SUCCESS: Information about removed smart device is as follows:");
                        String namesituation = stringtime(mylamp.name);
                        mylamp.zreport(namesituation);
                        colorLampIterator.remove();
                        Alldevices.remove(mylamp.name);
                    }
                }
                Iterator<SmartCamera> cameraiterator = SmartCameras.iterator();
                while (cameraiterator.hasNext()) {
                    SmartCamera mylamp = cameraiterator.next();
                    if (previousname.equals(mylamp.getName())) {
                        ReaderAndWriter.filewriter("SUCCESS: Information about removed smart device is as follows:");
                        String namesituation = stringtime(mylamp.name);
                        mylamp.zreport(namesituation);
                        cameraiterator.remove();
                        Alldevices.remove(mylamp.name);
                    }
                }
                Iterator<SmartPlug> iterator = SmartPlugs.iterator();
                while (iterator.hasNext()) {
                    SmartPlug mylamp = iterator.next();
                    if (previousname.equals(mylamp.getName())) {
                        ReaderAndWriter.filewriter("SUCCESS: Information about removed smart device is as follows:");
                        String namesituation = stringtime(mylamp.name);
                        mylamp.zreport(namesituation);
                        iterator.remove();
                        Alldevices.remove(mylamp.name);
                    }
                }
                Iterator<Object[]> objectitetator = combinedList.iterator();
                while (objectitetator.hasNext()) {
                    Object[] mylamp = objectitetator.next();
                    if (previousname.equals(mylamp[0])) {
                        objectitetator.remove();
                    }
                }
                sorterofcombinedlist();
            }
            else{
                ReaderAndWriter.filewriter("ERROR: Erroneous command!");
            }
            }
        catch(ArrayIndexOutOfBoundsException e){
            ReaderAndWriter.filewriter("ERROR: Erroneous command!");
        }
        }
        }









