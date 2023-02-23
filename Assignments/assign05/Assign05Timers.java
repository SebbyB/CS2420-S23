package assign05;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Assign05Timers {


    enum Color {
        //Color end string, color reset
        RESET("\033[0m"),

        // Regular Colors. Normal color, no bold, background color etc.
        BLACK("\033[0;30m"),    // BLACK
        RED("\033[0;31m"),      // RED
        GREEN("\033[0;32m"),    // GREEN
        YELLOW("\033[0;33m"),   // YELLOW
        BLUE("\033[0;34m"),     // BLUE
        MAGENTA("\033[0;35m"),  // MAGENTA
        CYAN("\033[0;36m"),     // CYAN
        WHITE("\033[0;37m"),    // WHITE

        // Bold
        BLACK_BOLD("\033[1;30m"),   // BLACK
        RED_BOLD("\033[1;31m"),     // RED
        GREEN_BOLD("\033[1;32m"),   // GREEN
        YELLOW_BOLD("\033[1;33m"),  // YELLOW
        BLUE_BOLD("\033[1;34m"),    // BLUE
        MAGENTA_BOLD("\033[1;35m"), // MAGENTA
        CYAN_BOLD("\033[1;36m"),    // CYAN
        WHITE_BOLD("\033[1;37m"),   // WHITE

        // Underline
        BLACK_UNDERLINED("\033[4;30m"),     // BLACK
        RED_UNDERLINED("\033[4;31m"),       // RED
        GREEN_UNDERLINED("\033[4;32m"),     // GREEN
        YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
        BLUE_UNDERLINED("\033[4;34m"),      // BLUE
        MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
        CYAN_UNDERLINED("\033[4;36m"),      // CYAN
        WHITE_UNDERLINED("\033[4;37m"),     // WHITE

        // Background
        BLACK_BACKGROUND("\033[40m"),   // BLACK
        RED_BACKGROUND("\033[41m"),     // RED
        GREEN_BACKGROUND("\033[42m"),   // GREEN
        YELLOW_BACKGROUND("\033[43m"),  // YELLOW
        BLUE_BACKGROUND("\033[44m"),    // BLUE
        MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
        CYAN_BACKGROUND("\033[46m"),    // CYAN
        WHITE_BACKGROUND("\033[47m"),   // WHITE

        // High Intensity
        BLACK_BRIGHT("\033[0;90m"),     // BLACK
        RED_BRIGHT("\033[0;91m"),       // RED
        GREEN_BRIGHT("\033[0;92m"),     // GREEN
        YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
        BLUE_BRIGHT("\033[0;94m"),      // BLUE
        MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
        CYAN_BRIGHT("\033[0;96m"),      // CYAN
        WHITE_BRIGHT("\033[0;97m"),     // WHITE

        // Bold High Intensity
        BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
        RED_BOLD_BRIGHT("\033[1;91m"),      // RED
        GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
        YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
        BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
        MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
        CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
        WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

        // High Intensity backgrounds
        BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     // BLACK
        RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
        GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
        YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
        BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
        MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
        CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
        WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    private static boolean printProgress = false;
    public static String separate = "========================";

    public void setPrintProgress(boolean condition) {
        printProgress = condition;
    }

    private static ArrayList<Integer> ascendingIntList(int size){
        ArrayList<Integer> ascendingList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            ascendingList.add(i);
        }
        return ascendingList;
    }
    
    static private ArrayList<Integer> permutedIntList(int size){
        ArrayList<Integer> list = ascendingIntList(size);
        Collections.shuffle(list);
        return list;
    }
    static private ArrayList<Integer> descendingIntList(int size){
        ArrayList<Integer> descendingList = new ArrayList<>();
        for (int i = size - 1; i >= 0 ; i--){
            descendingList.add(i);
        }
        return descendingList;
    }
    public static String mergeSort(int timesToLoop, int threshold, ArrayList<Integer> list) {
        
        ArrayListSorter.setThreshold(threshold);
        Long startTime = System.nanoTime();
        for (int timesLooped = 0; timesLooped < timesToLoop; timesLooped++) {
            ArrayListSorter.mergesort(list);
        }
        Long midPointTime = System.nanoTime();
        for (int timesLooped = 0; timesLooped < timesToLoop; timesLooped++) {

        }
        Long endTime = System.nanoTime();

        Long avgTime = ((endTime - midPointTime) + (midPointTime - startTime)) / timesToLoop;

        String timeTaken = Long.toString(avgTime);
        return timeTaken;
    }

    public static String quickSort(int timesToLoop, int pivot, ArrayList<Integer> list) {

        ArrayListSorter.setThreshold(10);
        ArrayListSorter.setPivotType(pivot);
        Long startTime = System.nanoTime();
        for (int timesLooped = 0; timesLooped < timesToLoop; timesLooped++) {
            ArrayListSorter.quicksort(list);
        }
        Long midPointTime = System.nanoTime();
        for (int timesLooped = 0; timesLooped < timesToLoop; timesLooped++) {

        }
        Long endTime = System.nanoTime();

        Long avgTime = ((endTime - midPointTime) + (midPointTime - startTime)) / timesToLoop;

        String timeTaken = Long.toString(avgTime);
        return timeTaken;
    }
    


    public static void generateData(int sizeN, int timesToLoop, int increment, String File) {

        System.out.print(Color.GREEN_BOLD);
        System.out.println(separate);
        System.out.println("Data Collection for Assignment 5.");
        System.out.println(separate);

        ArrayList<String[]> data = new ArrayList<>();
        System.out.println("Collecting Data with Parameters:");
        String[] parameters = {"SizeN", "MergeSort Threshold 1 time(ns) Ascending", "MergeSort Threshold 5 time(ns) Ascending", "MergeSort Threshold 10 time(ns) Ascending", "MergeSort Threshold 25 time(ns) Ascending", "MergeSort Threshold 50 time(ns) Ascending",
                "MergeSort Threshold 1 time(ns) Descending", "MergeSort Threshold 5 time(ns) Descending", "MergeSort Threshold 10 time(ns) Descending", "MergeSort Threshold 25 time(ns) Descending", "MergeSort Threshold 50 time(ns) Descending",
                "MergeSort Threshold 1 time(ns) Permuted", "MergeSort Threshold 5 time(ns) Permuted", "MergeSort Threshold 10 time(ns) Permuted", "MergeSort Threshold 25 time(ns) Permuted", "MergeSort Threshold 50 time(ns) Permuted",
                "Quicksort Pivot Default time(ns) Ascending","Quicksort Pivot Default time(ns) Descending","Quicksort Pivot Default time(ns) Permuted",
                "Quicksort Pivot 1 time(ns) Ascending","Quicksort Pivot 1 time(ns) Descending","Quicksort Pivot 1 time(ns) Permuted",
                "Quicksort Pivot 2 time(ns) Ascending","Quicksort Pivot 2 time(ns) Descending","Quicksort Pivot 2 time(ns) Permuted"
        };
        data.add(parameters);
        System.out.print(Color.BLUE_UNDERLINED);
        for (String parameter : parameters) {
            System.out.println(parameter);
        }
        System.out.print(Color.GREEN_BOLD);
        if (printProgress) {
            System.out.println("Print Progress is " + Color.BLUE_UNDERLINED + "On" + Color.GREEN_BOLD + ".");
            System.out.println(separate);
        } else {
            System.out.println("Print Progress is " + Color.BLUE_UNDERLINED + "Off" + Color.GREEN_BOLD + ".");
            System.out.println(separate);
        }

        System.out.println("Starting Data Collection...");
        for (int size = 1; size <= sizeN; size += increment) {


            if (printProgress) {
                System.out.println(Color.BLUE_UNDERLINED + Integer.toString(size));
            }


           
            String N = Integer.toString(size);
            String MS1A = mergeSort(timesToLoop,1,ascendingIntList(size));
            String MS5A = mergeSort(timesToLoop,5,ascendingIntList(size));
            String MS10A = mergeSort(timesToLoop,10,ascendingIntList(size));
            String MS25A = mergeSort(timesToLoop,25,ascendingIntList(size));
            String MS50A = mergeSort(timesToLoop,50,ascendingIntList(size));
            String MS1D = mergeSort(timesToLoop,1,descendingIntList(size));
            String MS5D = mergeSort(timesToLoop,5,descendingIntList(size));
            String MS10D = mergeSort(timesToLoop,10,descendingIntList(size));
            String MS25D = mergeSort(timesToLoop,25,descendingIntList(size));
            String MS50D = mergeSort(timesToLoop,50,descendingIntList(size));
            String MS1P = mergeSort(timesToLoop,1,permutedIntList(size));
            String MS5P = mergeSort(timesToLoop,5,permutedIntList(size));
            String MS10P = mergeSort(timesToLoop,10,permutedIntList(size));
            String MS25P = mergeSort(timesToLoop,25,permutedIntList(size));
            String MS50P = mergeSort(timesToLoop,50,permutedIntList(size));

//            String QSP0A = "0";
            String QSP0A = quickSort(timesToLoop,0,ascendingIntList(size));
//            String QSP1A = "1";
            String QSP1A = quickSort(timesToLoop,1,ascendingIntList(size));
//            String QSP2A = "2";
            String QSP2A = quickSort(timesToLoop,2,ascendingIntList(size));
//            String QSP0D = "0";
            String QSP0D = quickSort(timesToLoop,0,descendingIntList(size));
//            String QSP1D ="1";
            String QSP1D = quickSort(timesToLoop,1,descendingIntList(size));
//            String QSP2D = "2";
            String QSP2D = quickSort(timesToLoop,2,descendingIntList(size));
            String QSP0P = quickSort(timesToLoop,0,permutedIntList(size));
            String QSP1P = quickSort(timesToLoop,1,permutedIntList(size));
            String QSP2P = quickSort(timesToLoop,2,permutedIntList(size));

            String[] dataPoint = new String[]{N, MS1A,MS5A,MS10A,MS25A,MS50A,MS1D,MS5D,MS10D,MS25D,MS50D,MS1P,MS5P,MS10P,MS25P,MS50P,QSP0A,QSP0D,QSP0P,QSP1A,QSP1D,QSP1P,QSP2A,QSP2D,QSP2P};

            if (size == 1) {
                size--;
            }
            data.add(dataPoint);
        }

        System.out.println(Color.GREEN_BOLD + "Data Collection Finished.");
        System.out.println(separate);


        String filePath = File + ".csv";
        System.out.println("Creating a CSV file at the file path " + Color.BLUE_UNDERLINED + filePath + Color.GREEN_BOLD + " ...");

        java.io.File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(data);
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("Done!");
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print(Color.WHITE_UNDERLINED);
        System.out.println(separate);
        System.out.println("Input the Max Size n...");
        System.out.println(separate);
        System.out.print(Color.YELLOW);
        int n = scanner.nextInt();
        System.out.print(Color.WHITE_UNDERLINED);
        System.out.println(separate);
        System.out.println("Input the Times to Loop for the Average...");
        System.out.println(separate);
        System.out.print(Color.YELLOW);
        int timesToLoop = scanner.nextInt();
        System.out.print(Color.WHITE_UNDERLINED);
        System.out.println(separate);
        System.out.println("Input the desired Increment...");
        System.out.println(separate);
        System.out.print(Color.YELLOW);
        int increment = scanner.nextInt();
        System.out.print(Color.WHITE_UNDERLINED);
        System.out.println(separate);
//        System.out.println("Input the desired FileName...");
//        System.out.println("Exclude the file extension.");
//        System.out.println(separate);
        System.out.print(Color.YELLOW);
        String fileName = "Assign05Data";
        System.out.print(Color.WHITE_UNDERLINED);
        System.out.println(separate);
        System.out.println("PrintProgress?");
        System.out.println(separate);
        System.out.print(Color.YELLOW);
        printProgress = scanner.nextBoolean();


        generateData(n, timesToLoop, increment, fileName);


    }

}
