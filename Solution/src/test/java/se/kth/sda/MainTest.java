package se.kth.sda;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MainTest {

    /**
     * Helper method to exchange the scanner in Main so that it reads from the string input of this method rather than
     * the console. This is so that we can simulate user input.
     *
     * @param input
     */
    private void setUpMainScanner(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Main.scanner = new Scanner(inputStream);
    }

    /**
     * This test tries out the method testRunOptionPokemonInfo. The method relies on user input so we have to simulate
     * it.
     */
    @Test
    public void testRunOptionPokemonInfo() {
        setUpMainScanner("pikachu");

        Main.runOptionPokemonInformation();
    }

    /**
     * This test tries out the method testRunOptionPokemonInfo with a pokémon name that shouldn't exist. The method
     * relies on user input so we have to simulate it.
     */
    @Test
    public void testRunOptionPokemonInfoBadInput() {
        String userInput = "wadwad" +
                "\n#";
        setUpMainScanner(userInput);

        Main.runOptionPokemonInformation();
    }

    /**
     * This test tries out the method testRunOptionLocationInfo with location name. Since we don't have any return
     * value from the method and it also don't change the state of any input variables we can't really make use of
     * an assertEquals statement or similar. But what we can test is that for the given input it should terminate
     * without error.
     */
    @Test
    public void testRunOptionLocationInfo() {
        String userInput = "canalave-city";
        setUpMainScanner(userInput);

        Main.runOptionLocationInformation();
    }

}