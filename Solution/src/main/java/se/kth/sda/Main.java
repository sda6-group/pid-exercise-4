package se.kth.sda;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static StringBuilder no_damage_from, no_damage_to, double_damage_from, double_damage_to, half_damage_from, half_damage_to;

    private static final String POKEMON_API_BASE_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_API_POKEMON_URL = POKEMON_API_BASE_URL + "pokemon/";

    public static void main(String[] args) {
        run();
    }

    /**
     * This method asks the user for a pokemon name and presents (prints) information about it (name, weight, height
     * and id.)
     */
    static void runOptionPokemonInformation() {
        String response = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.println("Enter the name of the Pokemon: ");
            Scanner scanner = new Scanner(System.in);
            String inputName = (scanner.nextLine()).toLowerCase().strip();
            response = getInfo(POKEMON_API_POKEMON_URL + inputName);
            if (response != null) {
                inputValid = true;
            }

        }

        JSONObject pokemonInfo = new JSONObject(response);
        String name = pokemonInfo.getString("name");
        int height = pokemonInfo.getInt("height");
        int weight = pokemonInfo.getInt("weight");
        int id = pokemonInfo.getInt("id");




//        JSONObject obj2 = new JSONObject(getInfo("https://pokeapi.co/api/v2/type/"));
//        JSONArray results = obj2.getJSONArray("results");
//
//
//        for (int i = 0; i < results.length(); i++) {
//            if ((results.getJSONObject(i).getString("name")).equals(type)) {
//                JSONObject damaginObj = new JSONObject(getinfo(results.getJSONObject(i).getString("url"))).getJSONObject("damage_relations");
//
//
//                no_damage_from = new StringBuilder();
//                JSONArray no_damage_from_arr = damaginObj.getJSONArray("no_damage_from");
//                if (no_damage_from_arr.length() != 0) {
//                    for (int x = 0; x < no_damage_from_arr.length(); x++) {
//                        no_damage_from.append(no_damage_from_arr.getJSONObject(x).getString("name"));
//                        if (x != no_damage_from_arr.length() - 1) {
//                            no_damage_from.append(",");
//                        }
//                    }
//                } else {
//                    no_damage_to.append("No One");
//                }
//
//                no_damage_to = new StringBuilder();
//                JSONArray no_damage_to_arr = damaginObj.getJSONArray("no_damage_to");
//                if (no_damage_to_arr.length() != 0) {
//                    for (int x = 0; x < no_damage_to_arr.length(); x++) {
//                        no_damage_to.append(no_damage_to_arr.getJSONObject(x).getString("name"));
//                        if (x != no_damage_to_arr.length() - 1) {
//                            no_damage_to.append(",");
//                        }
//                    }
//                } else {
//                    no_damage_to.append("No One");
//                }
//
//
//                double_damage_from = new StringBuilder();
//                JSONArray double_damage_from_arr = damaginObj.getJSONArray("double_damage_from");
//                if (double_damage_from_arr.length() != 0) {
//                    for (int x = 0; x < double_damage_from_arr.length(); x++) {
//                        double_damage_from.append(double_damage_from_arr.getJSONObject(x).getString("name"));
//                        if (x != double_damage_from_arr.length() - 1) {
//                            double_damage_from.append(",");
//                        }
//                    }
//                } else {
//                    half_damage_from.append("No One");
//                }
//
//
//                double_damage_to = new StringBuilder();
//                JSONArray double_damage_to_arr = damaginObj.getJSONArray("double_damage_to");
//                if (double_damage_to_arr.length() != 0) {
//                    for (int x = 0; x < double_damage_to_arr.length(); x++) {
//                        double_damage_to.append(double_damage_to_arr.getJSONObject(x).getString("name"));
//                        if (x != double_damage_to_arr.length() - 1) {
//                            double_damage_to.append(",");
//                        }
//                    }
//                } else {
//                    double_damage_to.append("No One");
//                }
//
//
//                half_damage_from = new StringBuilder();
//                JSONArray half_damage_from_arr = damaginObj.getJSONArray("half_damage_from");
//                if (half_damage_from_arr.length() != 0) {
//                    for (int x = 0; x < half_damage_from_arr.length(); x++) {
//                        half_damage_from.append(half_damage_from_arr.getJSONObject(x).getString("name"));
//                        if (x != half_damage_from_arr.length() - 1) {
//                            half_damage_from.append(",");
//                        }
//                    }
//
//                } else {
//                    half_damage_from.append("No One");
//                }
//
//
//                half_damage_to = new StringBuilder();
//                JSONArray half_damage_to_arr = damaginObj.getJSONArray("half_damage_to");
//                if (half_damage_to_arr.length() != 0) {
//                    for (int x = 0; x < half_damage_to_arr.length(); x++) {
//                        half_damage_to.append(half_damage_to_arr.getJSONObject(x).getString("name"));
//                        if (x != half_damage_to_arr.length() - 1) {
//                            half_damage_to.append(",");
//                        }
//                    }
//                } else {
//                    half_damage_to.append("No One");
//                }
//
//            }
//        }
//
//
        System.out.println("Name: " + name);
        System.out.println("Id: " + id);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
//
//
//        System.out.println("\n\n");
//
//        //Option 2


    }

    static void run() {

        while (true) {

            System.out.println("1- Information about a Pokemon");
            System.out.println("2- Information about an Area");
            System.out.println("3- Exit");

            Scanner input = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int number = input.nextInt();

            //Option 1
            if (number == 1) {
                runOptionPokemonInformation();
            } else if (number == 2) {
                System.out.print("Enter the name of Location: ");
                Scanner s = new Scanner(System.in);
                String location = (s.nextLine()).toLowerCase();
                String response = getInfo("https://pokeapi.co/api/v2/location/" + location);

                if (response == null) {
                    continue;
                }
                JSONObject obj = new JSONObject(response);
                String region = obj.getJSONObject("region").getString("name");

                JSONArray arr = obj.getJSONArray("areas");
                StringBuilder area = new StringBuilder();

                for (int i = 0; i < arr.length(); i++) {
                    area.append(arr.getJSONObject(i).getString("name"));
                    if (i != arr.length() - 1) {
                        area.append(",");
                    }
                }


                System.out.println("Name: " + location);
                System.out.println("Area: " + area);
                System.out.println("Region: " + region);
                System.out.println("\n\n");

                //Optoin 3
            } else if (number == 3) {
                break;
            } else {
                System.out.println("Not Avaliable");
                System.out.println("\n\n");

            }


        }

    }

    //returned value from API as String
    public static String getInfo(String urli) {
        String response = null;
        try {

            URL url = new URL(urli);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();

            // Code 200 is the standard response for get requests in the HTTP protocol.
            if (responseCode != 200) {
                System.out.println("Something went wrong with fetching the resource!\n" +
                        "Code: " + responseCode);
                return null;
            } else {
                Scanner sc = new Scanner(conn.getInputStream());
                while (sc.hasNext()) {
                    response = sc.nextLine();
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
