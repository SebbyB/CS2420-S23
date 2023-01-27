package Timers;

public class ParameterList {


    private String[] params;
    private String dataName;


    public ParameterList(int size){
        params = new String[size];
    }

    public void setParam(int pos, String param){

        if(pos <= 0){
            throw new IllegalArgumentException();
        }else{
            params[pos] = param;
        }


    }

    public void setDataName(String name){
        this.dataName = name;
    }



    public static void main(String[] args){
        ParameterList list = new ParameterList(Integer.parseInt(args[0]));

        list.setDataName(args[1]);
        int j = 0;
        for(int i = 2; i < args.length; i++){
            list.setParam(j,args[i]);
            j++;
        }



    }




}
