import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

public class pokemonBaseUrl {


        public static RequestSpecification spec;


    @Before
    public void setUp() throws Exception {
        spec = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("https://pokeapi.co/api/v2").build();
    }
}
