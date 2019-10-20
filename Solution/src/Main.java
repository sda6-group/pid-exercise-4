import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static String height,id,weight;
    private static StringBuilder no_damage_from,no_damage_to,double_damage_from,double_damage_to,half_damage_from,half_damage_to;

    public static void main(String[] args) {

    run();

    }

    static void run(){

        while(true){

            System.out.println("1- Information about a Pokemon");
            System.out.println("2- Information about an Area");
            System.out.println("3- Exit");

            Scanner input = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int number = input.nextInt();

            //Option 1
            if (number==1){
                System.out.print("Enter the name of the Pokemon: ");
                Scanner s=new Scanner(System.in);
                String name=(s.nextLine()).toLowerCase();
                String response=getinfo("https://pokeapi.co/api/v2/pokemon/"+name);
                if (response == null) {
                    continue;
                }

                height=response.substring(response.indexOf("height")+8,response.indexOf("held_items")-2);
                id=response.substring(response.indexOf("\"id\"")+5,response.indexOf("is_default")-2);
                weight=response.substring(response.indexOf("weight")+8,response.length()-1);


                JSONObject obj = new JSONObject(response);
                String type=obj.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");

                JSONObject obj2=new JSONObject(getinfo("https://pokeapi.co/api/v2/type/"));
                JSONArray arr=obj2.getJSONArray("results");


                for (int i=0; i<arr.length() ; i++){
                    if((arr.getJSONObject(i).getString("name")).equals(type)){
                        JSONObject damaginObj=new JSONObject(getinfo(arr.getJSONObject(i).getString("url"))).getJSONObject("damage_relations");


                        no_damage_from=new StringBuilder();
                        JSONArray no_damage_from_arr=damaginObj.getJSONArray("no_damage_from");
                        if (no_damage_from_arr.length()!=0) {
                            for (int x = 0; x < no_damage_from_arr.length(); x++) {
                                no_damage_from.append(no_damage_from_arr.getJSONObject(x).getString("name"));
                                if (x != no_damage_from_arr.length() - 1) {
                                    no_damage_from.append(",");
                                }
                            }
                        }else{
                            no_damage_to.append("No One");
                        }

                        no_damage_to=new StringBuilder();
                        JSONArray no_damage_to_arr=damaginObj.getJSONArray("no_damage_to");
                        if (no_damage_to_arr.length()!=0) {
                            for (int x = 0; x < no_damage_to_arr.length(); x++) {
                                no_damage_to.append(no_damage_to_arr.getJSONObject(x).getString("name"));
                                if (x != no_damage_to_arr.length() - 1) {
                                    no_damage_to.append(",");
                                }
                            }
                        }else{
                            no_damage_to.append("No One");
                        }


                        double_damage_from=new StringBuilder();
                        JSONArray double_damage_from_arr=damaginObj.getJSONArray("double_damage_from");
                        if (double_damage_from_arr.length()!=0) {
                            for (int x = 0; x < double_damage_from_arr.length(); x++) {
                                double_damage_from.append(double_damage_from_arr.getJSONObject(x).getString("name"));
                                if (x != double_damage_from_arr.length() - 1) {
                                    double_damage_from.append(",");
                                }
                            }
                        }else{
                            half_damage_from.append("No One");
                        }



                        double_damage_to=new StringBuilder();
                        JSONArray double_damage_to_arr=damaginObj.getJSONArray("double_damage_to");
                        if (double_damage_to_arr.length()!=0){
                            for (int x=0;x<double_damage_to_arr.length();x++){
                                double_damage_to.append(double_damage_to_arr.getJSONObject(x).getString("name"));
                                if (x!=double_damage_to_arr.length()-1){
                                    double_damage_to.append(",");
                                }
                            }
                        }else{
                            double_damage_to.append("No One");
                        }


                        half_damage_from=new StringBuilder();
                        JSONArray half_damage_from_arr=damaginObj.getJSONArray("half_damage_from");
                        if (half_damage_from_arr.length()!=0){
                            for (int x=0;x<half_damage_from_arr.length();x++){
                                half_damage_from.append(half_damage_from_arr.getJSONObject(x).getString("name"));
                                if (x!=half_damage_from_arr.length()-1){
                                    half_damage_from.append(",");
                                }
                            }

                        }else{
                            half_damage_from.append("No One");
                        }



                        half_damage_to=new StringBuilder();
                        JSONArray half_damage_to_arr=damaginObj.getJSONArray("half_damage_to");
                        if (half_damage_to_arr.length()!=0) {
                            for (int x = 0; x < half_damage_to_arr.length(); x++) {
                                half_damage_to.append(half_damage_to_arr.getJSONObject(x).getString("name"));
                                if (x != half_damage_to_arr.length() - 1) {
                                    half_damage_to.append(",");
                                }
                            }
                        }else{
                            half_damage_to.append("No One");
                        }

                    }
                }


                System.out.println("Name: "+name);
                System.out.println("Id: "+id);
                System.out.println("Height: "+ height);
                System.out.println("Weight: "+weight);
                System.out.println("Type: "+type);
                System.out.println("No Damage From: "+no_damage_from);
                System.out.println("No Damage To: "+no_damage_to);
                System.out.println("Double Damage From: "+double_damage_from);
                System.out.println("Double Damage To: "+double_damage_to);
                System.out.println("Half Damage From: "+half_damage_from);
                System.out.println("Half Damage To: "+half_damage_to);


                System.out.println("\n\n");

            //Option 2
            }else if(number==2){
                System.out.print("Enter the name of Location: ");
                Scanner s=new Scanner(System.in);
                String location=(s.nextLine()).toLowerCase();
                String response=getinfo("https://pokeapi.co/api/v2/location/"+location);

                if(response==null){
                    continue;
                }
                JSONObject obj = new JSONObject(response);
                String region = obj.getJSONObject("region").getString("name");

                JSONArray arr=obj.getJSONArray("areas");
                StringBuilder area=new StringBuilder();

                for (int i = 0; i < arr.length(); i++) {
                    area.append(arr.getJSONObject(i).getString("name"));
                    if (i!=arr.length()-1){
                        area.append(",");
                    }
                }



                System.out.println("Name: "+location);
                System.out.println("Area: "+area);
                System.out.println("Region: "+region);
                System.out.println("\n\n");

            //Optoin 3
            }else if(number==3){
                break;
            }else{
                System.out.println("Not Avaliable");
                System.out.println("\n\n");

            }


        }

    }

    //returned value from API as String
    static String getinfo(String urli){
        String response="";
        try {

            URL url = new URL(urli);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if(responsecode == 404) {
                System.out.println("Not Found\n\n");
                return null;
            }
            else{

                Scanner sc = new Scanner(conn.getInputStream());
                while(sc.hasNext())
                {
                    response=sc.nextLine();
                }
                sc.close();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

          return response;
    }
}
