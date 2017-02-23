
import com.google.gson.GsonBuilder;
import com.usmanjamil.flightsite.model.Auth0SignIn;
import com.usmanjamil.flightsite.services.UserService;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Properties;

import static org.mockito.Matchers.anyString;

/**
 * Created by usmanjamil on 23/02/2017.
 */
public class FirstTest {
    @Mock
    private UserService userService;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.userService = new UserService();
    }

    @Test
    public void signInTestFromApi() throws Exception {
        Properties prop = new Properties();
        InputStream input = null;

        String filename = "auth0.properties";
        input = getClass().getClassLoader().getResourceAsStream(filename);

        prop.load(input);
        String clientId = prop.getProperty("auth0.clientId");
        String url = prop.getProperty("auth0.signIn");
        String connection = prop.getProperty("auth0.connection");
        String scope = prop.getProperty("auth0.scope");
        String username = "usman.jamil@live.co.uk";
        String password = "hello1";

        Auth0SignIn user = new Auth0SignIn(clientId, username, password, connection, scope);

        String json = new GsonBuilder().create().toJson(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        Mockito.when(restTemplate
                .exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(aValidResponse());
    }

    private ResponseEntity<String> aValidResponse() {
        return new ResponseEntity("{}", HttpStatus.OK);
    }

}
