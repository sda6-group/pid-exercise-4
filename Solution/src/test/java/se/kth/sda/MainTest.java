package se.kth.sda;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MainTest {

    @Test
    public void getinfo() {


        String res[]=new String[20];
        res[1]="{" +"\"areas\":["+"{" +
                "\"name\":\"canalave-city-area\"," +
                "\"url\":\"https://pokeapi.co/api/v2/location-area/1/\"" +
                "}" +
                "]," +
                "\"game_indices\":[" +
                "{" +
                "\"game_index\":7," +
                "\"generation\":{" +
                "\"name\":\"generation-iv\"," +
                "\"url\":\"https://pokeapi.co/api/v2/generation/4/\"" +
                "}" +
                "}" +
                "]," +
                "\"id\":1," +
                "\"name\":\"canalave-city\"," +
                "\"names\":[" +
                "{" +
                "\"language\":{" +
                "\"name\":\"en\"," +
                "\"url\":\"https://pokeapi.co/api/v2/language/9/\"" +
                "}," +
                "\"name\":\"Canalave City\"" +
                "}," +
                "{" +
                "\"language\":{" +
                "\"name\":\"fr\"," +
                "\"url\":\"https://pokeapi.co/api/v2/language/5/\"" +
                "}," +
                "\"name\":\"Joliberges\"" +
                "}" +
                "]," +
                "\"region\":{" +
                "\"name\":\"sinnoh\"," +
                "\"url\":\"https://pokeapi.co/api/v2/region/4/\"" +
                "}" +
                "}";
               for (int i=1;i<=1;i++){
                   assertEquals(Main.getinfo("https://pokeapi.co/api/v2/location/"+i),res[i]);
               }
    }

}