package Timers;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Scanner;

public class DataCollect {


    private int MAX_N, INCREMENT, TIMES_TO_LOOP;
    private Hashtable<String,Long[]> dataTable;
    private String[] Parameters;


    public void setParameters(String[] params){
        this.Parameters = params;
    }

    private int TableSize(){
        return this.MAX_N*this.INCREMENT;
    }

    public DataCollect(int maxN, int inc, int ttl){
        this.MAX_N = maxN;
        this.INCREMENT = inc;
        this.TIMES_TO_LOOP = ttl;

    }

    public static String[] parseParams(String pathFile){


        try {
            new JsonParser().parse(pathFile);
            // Valid.
        } catch (JsonParseException e) {
            // Invalid.
        }

    }
    public static void main(String[] args){



        DataCollect collection = new DataCollect(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        collection.setParameters();



    }





}
