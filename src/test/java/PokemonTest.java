import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class PokemonTest extends pokemonBaseUrl {

    @Test
    public void getHighestPower() {

        theStrongestPokemon(50,52);


    }

    public static void theStrongestPokemon(int beginId,int endId){

        List<Integer> powerList=new ArrayList<>();
        List<String> nameList=new ArrayList<>();
        List<String> urls=new ArrayList<>();


        for (int i = beginId; i <= endId; i = i + 1) {

            //set url send get request
            spec.pathParams("first", "pokemon", "second", i);
            Response response = given().when().spec(spec).contentType(ContentType.JSON).get("{first}/{second}");
            List<String> moves = response.jsonPath().getList("moves.move.name");
            String url=response.jsonPath().getString("forms.url");
            String name=response.jsonPath().getString("forms.name");
            System.out.println("moves of "+name +" = " + moves);

            int powerSum = 0;
            for (int j=0; j<moves.size();j++) {
                //set url and send get request
                spec.pathParams("first", "move", "second", moves.get(j));
                Response response1 = given().when().spec(spec).contentType(ContentType.JSON).get("{first}/{second}");
                JsonPath jsonPath = response1.jsonPath();

                try {
                    int power = (jsonPath.get("power"));
                    //System.out.println("power = " + power);
                    powerSum = power + powerSum;
                    //System.out.println("powerSum = " + powerSum);
                } catch (Exception e) {
                }


            }
            powerList.add(powerSum);
            nameList.add(name);
            urls.add(url);

        }
        int idx=0;
        int max = Integer.MIN_VALUE;

        for (int k = 0; k < powerList.size(); k++) {
            int currentNumber = powerList.get(k);
            if (currentNumber > max) {
                max = currentNumber;
                idx=k;
            }
        }
        List<String> urlList= List.of(urls.get(idx).split("/"));
        String pokemonId=urlList.get(6);

        System.out.println("Biggest value: " + max);
        System.out.println("Pokemon Name = " + nameList.get(idx) );
        System.out.println("Pokemon id = " + pokemonId);

    }
}


