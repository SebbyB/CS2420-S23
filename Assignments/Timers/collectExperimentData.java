//package Timers;
//
//import com.opencsv.CSVWriter;
//import org.w3c.dom.Text;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Hashtable;
//
//public class collectExperimentData {
//
//    boolean printProgress = true;
//    static String separate = "";
//    private Path filePath, logPath;
//    private Experiment[] experiments;
//    private int[] parameters;
//    boolean logExperiment;
//    Hashtable<String,String[]> dataTable;
//
//    public collectExperimentData(Path path, Experiment[] exp, int[] params){
//        this.experiments = exp;
//        this.filePath = path;
//        this.parameters = params;
//        this.logExperiment = false;
//    }
//
//    public collectExperimentData(Path path, Experiment[] exp, int[] params,Path log){
//        this.experiments = exp;
//        this.filePath = path;
//        this.parameters = params;
//        logPath = log;
//        this.logExperiment = true;
//    }
//
//    public void generateData(){
//
//        int sizeN = parameters[0], increment = parameters[1], timesToLoop = parameters[2];
//
//        for (Experiment experiment: experiments) {
//
//        }
//
//
//
//    }
//
//    public static void generateData(int sizeN, int timesToLoop, int increment, String File) {
//
//        System.out.print(TextColors.GREEN_BOLD);
//        System.out.println(separate);
//        System.out.println("Data Collection for Assignment 3.");
//        System.out.println(separate);
//
//        ArrayList<String[]> data = new ArrayList<>();
//        System.out.println("Collecting Data with Parameters:");
////        String[] parameters = {"SizeN", "findMax time (ns)", "insert time Best (ns)", "insert time Worst (ns)"};
////        String[] parameters = {"SizeN", "findMax time (ns)", "insert time (ns)"};
//        String[] parameters = {"SizeN", "findMax time (ns)"};
//        data.add(parameters);
//        System.out.print(TextColors.BLUE_UNDERLINED);
//        for (String parameter : parameters) {
//            System.out.println(parameter);
//        }
//        System.out.print(TestColors.GREEN_BOLD);
//        if (printProgress) {
//            System.out.println("Print Progress is " + TestColors.BLUE_UNDERLINED + "On" + TestColors.GREEN_BOLD + ".");
//            System.out.println(separate);
//        } else {
//            System.out.println("Print Progress is " + TestColors.BLUE_UNDERLINED + "Off" + TestColors.GREEN_BOLD + ".");
//            System.out.println(separate);
//        }
//
//        System.out.println("Starting Data Collection...");
//        for (int size = 1; size <= sizeN; size += increment) {
//
//
//            if (printProgress) {
//                System.out.println(TestColors.BLUE_UNDERLINED + Integer.toString(size));
//            }
//
//
//            String N = Integer.toString(size);
//            String findMaxTiming = findMaxTiming(size,timesToLoop);
////            String insertBest = insertTimingBest(size,timesToLoop);
////            String insertWorst = insertTimingWorst(size,timesToLoop);
//
//            String[] dataPoint = new String[]{N, findMaxTiming};
////            String[] dataPoint = new String[]{N, findMaxTiming,insertBest};
////            String[] dataPoint = new String[]{N, findMaxTiming,insertBest,insertWorst};
//            if (size == 1) {
//                size--;
//            }
//            data.add(dataPoint);
//        }
//
//        System.out.println(TestColors.GREEN_BOLD + "Data Collection Finished.");
//        System.out.println(separate);
//
//
//        String filePath = File + ".csv";
//        System.out.println("Creating a CSV file at the file path " + TestColors.BLUE_UNDERLINED + filePath + TestColors.GREEN_BOLD + " ...");
//
//        java.io.File file = new File(filePath);
//
//        try {
//            // create FileWriter object with file as parameter
//            FileWriter outputFile = new FileWriter(file);
//
//            // create CSVWriter object filewriter object as parameter
//            CSVWriter writer = new CSVWriter(outputFile, ';',
//                    CSVWriter.NO_QUOTE_CHARACTER,
//                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
//                    CSVWriter.DEFAULT_LINE_END);
//            writer.writeAll(data);
//            // closing writer connection
//            writer.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            System.out.println("Done!");
//        }
//
//    }
//
//
//
//
//
//}
