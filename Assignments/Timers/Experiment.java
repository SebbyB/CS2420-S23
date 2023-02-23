package Timers;

import java.util.Hashtable;

public abstract class Experiment extends Thread {

    public String experimentName;
    String[] data;
    public Experiment(int index,String[] dataArr, int[] Parameters){
        this.data = dataArr;
    }

    @Override
    public void run() {
        super.run();
    }
}
