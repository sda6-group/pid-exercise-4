package se.kth.sda;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainTest {

    @Mock
    RequestHandler requestHandler;

    /**
     * Initializes the mock.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Helper method to exchange the scanner in Main so that it reads from the string input of this method rather than
     * the console. This is so that we can simulate user input.
     *
     * @param input
     */
    private void setUpInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Main.scanner = new Scanner(inputStream);
    }

    /**
     * Helper method to read contents of a file given by path.
     *
     * @param pathString
     */
    private String readFileContent(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        byte[] bytes = Files.readAllBytes(path);
        String content = new String(bytes, StandardCharsets.UTF_8);
        return content;
    }

    /**
     * This test tries out the method testRunOptionPokemonInfo. The method relies on user input so we have to simulate
     * it.
     */
    @Test
    public void testRunOptionPokemonInfo() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        setUpInput("pikachu");
        // Set up Main so that it uses our "fake" request handler instead of sending actual http requests.
        Main.requestHandler = requestHandler;
        String example_response = readFileContent("src/test/resources/example_response_pokemon_pikachu.json");
        Mockito.when(requestHandler.getResponseBodyFromUrl("https://pokeapi.co/api/v2/pokemon/pikachu")).thenReturn(example_response);

        Main.runOptionPokemonInformation();
        String output = outputStream.toString();

        assertThat(output, containsString("Name: pikachu"));
        assertThat(output, containsString("Id: 25"));
        assertThat(output, containsString("Height: 4"));
        assertThat(output, containsString("Weight: 60"));
    }

    /**
     * This test tries out the method testRunOptionPokemonInfo with a pokémon name that shouldn't exist. The method
     * relies on user input so we have to simulate it.
     */
    @Test
    public void testRunOptionPokemonInfoBadInput() {
        String userInput = "wadwad" +
                "\n#";
        setUpInput(userInput);

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
        setUpInput(userInput);

        Main.runOptionLocationInformation();
    }

}