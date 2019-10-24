package se.kth.sda;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    private static final String POKEMON_API_BASE_URL = "https://pokeapi.co/api/v2/";
    private static final String POKEMON_API_POKEMON_URL = POKEMON_API_BASE_URL + "pokemon/";
    static Scanner scanner = new Scanner(System.in);
    static RequestHandler requestHandler = new RequestHandler();

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
            response = requestHandler.getResponseBodyFromUrl(POKEMON_API_POKEMON_URL + inputPokemonName);
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
            response = requestHandler.getResponseBodyFromUrl("https://pokeapi.co/api/v2/location/" + inputLocation);
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

}
