package se.kth.sda;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class MainTest {

    /**
     * This test tries out the method testRunOptionPokemonInfo. The method relies on user input so we have to simulate
     * it.
     */
    @Test
    public void testRunOptionPokemonInfo() {

        // Here we are simulating user input by making it so that java doesn't read from the console, but from our
        // own input stream, which is fed the string "pikachu".
        String data = "pikachu";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main.runOptionPokemonInformation();
    }

    /**
     * This test tries out the method testRunOptionPokemonInfo. The method relies on user input so we have to simulate
     * it.
     */
    @Test
    public void testRunOptionPokemonInfoBadInput() {

        // Here we are simulating user input by making it so that java doesn't read from the console, but from our
        // own input stream, which is fed the string "pikachu".
        String data = "awfeaf";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Main.runOptionPokemonInformation();
    }

}