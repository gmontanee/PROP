package dades;

import java.util.Scanner;
import java.io.*;

public class csv {

    public static void main (String args[]){

    }

    //llegeir un cvs donat un path
    public void read(String path) {
        try {
            

            Scanner sc = new Scanner(new File(path));

            sc.useDelimiter(",");

            while(sc.hasNext()){
                System.out.println(sc.next());
            }

            sc.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
    }

    //guarda un cvs a partir de un path
    public void write(String path) {
        System.out.print(path);
        try {
            PrintWriter pw = new PrintWriter(new File(path + ".csv"));
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < 10; ++i) {
                sb.append("" + i +",");
                if(i%2 == 0) sb.append("\n");
            }

            pw.write(sb.toString());

            pw.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}