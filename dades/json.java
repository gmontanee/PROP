package dades;

import org.json.JSONException;
import org.json.JSONObject;
 
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class json {
    
    public void read(){

    }

    public void write(){
        String path = "companies.json";
 
        JSONObject json = new JSONObject();
        try {
            json.put("name", "Google");
            json.put("employees", 140000);
            json.put("offices", List.of("Mountain View", "Los Angeles", "New York"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        write();
    }
}