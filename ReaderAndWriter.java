import java.io.*;

public class ReaderAndWriter {
    public BufferedReader reader;
    public static BufferedWriter writer;
    public ReaderAndWriter(String fileName, String outputfilename) {
        try {
            reader = new BufferedReader(new FileReader(fileName));
            writer = new BufferedWriter(new FileWriter(outputfilename));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static int  num = 1;
    public void readFile() throws IOException {
    String line;
    String lasline = "";

        while ((line = reader.readLine()) != null) {
            lasline = line;
        if (line.isEmpty()) {
            num++;
            continue;
        }
        filewriter("COMMAND: " + line);
        if (num > 0 && line.split("\t")[0].trim().equals("SetInitialTime")) {
            Arrays.setInitialtime(line);
        }
        else if ((num > 0) && line.split("\t")[0].trim() != ("SetInitialTime")) {
           filewriter("First line must include Setinitialtime");
        }
        else if ((num < 0) && line.split("\t")[0].trim() == ("SetInitialTime")) {
            filewriter("ERROR: Erroneous command!");
        }
        //System.out.println(TimeAdjuster.localDateTime);
        else if (line.split("\t")[0].trim().equals("SetSwitchTime")) {
            TimeAdjuster.setSwitchTime(line);

        }
        else if (line.split("\t")[0].trim().equals("SkipMinutes")) {
            TimeAdjuster timeAdjuster = new TimeAdjuster();
            timeAdjuster.SkipMinutes(line.split("\t")[1].trim());

            //System.out.println(4);

        }
        else if(line.split("\t")[0].trim().equals("Add")){
            Arrays.adding(line);
        }
        else if(line.split("\t")[0].trim().equals("Switch")){
            Arrays.switchmethod(line);
            //System.out.println(Arrays.SmartLamps.get(0).status);
        }
        else if(line.split("\t")[0].trim().equals("PlugIn")) {
            Arrays.PlugedIn(line);

            //System.out.println(Arrays.SmartPlugs.get(0).name);
        }
        else if(line.split("\t")[0].trim().equals("PlugOut")){
            Arrays.PlugedOut(line);
        }
        else if(line.split("\t")[0].trim().equals("SetKelvin")){
            Lamps.ChangeKelvin(line);
        }
        else if(line.split("\t")[0].trim().equals("SetBrightness")){
            Lamps.ChangeBrightness(line);
        }
        else if(line.split("\t")[0].trim().equals("SetWhite")){
            Lamps.ChangeWhite(line);
        }
        else if(line.split("\t")[0].trim().equals("SetColor")){
            Lamps.ChangeColor(line);
        }
        else if(line.split("\t")[0].trim().equals("SetColorCode")){
            Lamps.ChangeColorcode(line);
        }
        else if(line.split("\t")[0].trim().equals("SetTime")){

            TimeAdjuster.Settime(line);
        }
        else if(line.split("\t")[0].trim().equals("Nop")){
            TimeAdjuster.nop();
        }
        else if(line.split("\t")[0].trim().equals("ChangeName")){
            Arrays.ChangeName(line);
        }
        else if(line.split("\t")[0].trim().equals("a")){
            filewriter(String.valueOf(Arrays.SmartPlugs.get(0).totalEnergyConsumption));
            filewriter(String.valueOf(Arrays.SmartPlugs.get(0).status));
        }
        else if(line.split("\t")[0].trim().equals("ZReport")){
            Arrays.ZReport();
        }
        else if(line.split("\t")[0].trim().equals("Remove")){
            Arrays.remove(line);
        }
        else{
            filewriter("ERROR: Erroneous command!");
        }
    }
        System.out.println(lasline);
        writer.close();
}
public static void filewriter(String writtesomething) throws IOException {
        writer.write(writtesomething);
        writer.newLine();

}
public static void fileclose() throws IOException {
        writer.close();
}
}
