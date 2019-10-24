package se.kth.sda;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    private static StringBuilder no_damage_from, no_damage_to, double_damage_from, double_damage_to, half_damage_from, half_damage_to;

    private static final String POKEMON_API_BASE_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_API_POKEMON_URL = POKEMON_API_BASE_URL + "pokemon/";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;


        while (!exit) {
            System.out.println("1- Information about a Pokemon");
            System.out.println("2- Information about an Area");
            System.out.println("3- Exit");

            System.out.println("Enter a number: ");
            String input = scanner.nextLine().strip();

            switch (input) {
                case "1":
                    runOptionPokemonInformation();
                    break;
                case "2":
                    runOptionLocationInformation();
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.out.println("Not Avaliable\n");
                    break;
            }
        }
    }

    /**
     * This method asks the user for a pokemon name and presents (prints) information about it (name, weight, height
     * and id.)
     */
    static void runOptionPokemonInformation() {
        String response = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.println("Enter the name of the Pokemon or write '#' to go back: ");
            String inputPokemonName = (scanner.nextLine()).toLowerCase().strip();
            if (inputPokemonName.equals("#")) {
                return;
            }
            response = getResponseBodyFromUrl(POKEMON_API_POKEMON_URL + inputPokemonName);
            if (response != null) {
                inputValid = true;
            } else {
                System.out.println("Sorry, could not find the pokémon you were looking for :(");
            }
        }

        JSONObject pokemonInfo = new JSONObject(response);
        String name = pokemonInfo.getString("name");
        int height = pokemonInfo.getInt("height");
        int weight = pokemonInfo.getInt("weight");
        int id = pokemonInfo.getInt("id");

        System.out.println("Name: " + name);
        System.out.println("Id: " + id);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("\n");
    }

    /**
     * This method asks the user for a location name and presents (prints) information about it (the area, the region it
     * is in and the name.
     */
    static void runOptionLocationInformation() {
        String response = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.println("Enter the name of Location: ");
            String inputLocation = (scanner.nextLine()).toLowerCase();
            response = getResponseBodyFromUrl("https://pokeapi.co/api/v2/location/" + inputLocation);
            if (response != null) {
                inputValid = true;
            }
        }

        JSONObject locationInfo = new JSONObject(response);
        JSONArray nameInfos = locationInfo.getJSONArray("names");
        Stream<JSONObject> namesStream = StreamSupport.stream(nameInfos.spliterator(), false).map(o -> (JSONObject) o);

        // Here we have some wonderful stream magic :) Can you figure out how it works?
        String names = namesStream.map(
                j -> "(" + j.getJSONObject("language").getString("name") + ") "
                        + j.getString("name"))
                .collect(Collectors.joining(" / "));
        String region = locationInfo.getJSONObject("region").getString("name");


        System.out.println("Name(s): " + names);
        System.out.println("Region: " + region);
        System.out.println("\n");
    }

    /**
     * Takes in an url and makes a get request to the given url and returns the body of the response as a string.
     * Returns null if response code is not 200 or some unexpected error occurs with the connection.
     * @param urlString
     * @return
     */
    public static String getResponseBodyFromUrl(String urlString) {
        String response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

            // Code 200 is the standard response for get requests in the HTTP protocol.
            if (responseCode != 200) {
                System.out.println("Something went wrong with fetching the resource!\n" +
                        "Code: " + responseCode);
                return null;
            } else {
                Scanner responseScanner = new Scanner(connection.getInputStream());
                while (responseScanner.hasNext()) {
                    response = responseScanner.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
