
import com.google.gson.GsonBuilder;
import com.usmanjamil.flightsite.model.Auth0SignIn;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by usmanjamil on 23/02/2017.
 */
public class SignInTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Auth0SignIn user;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignIn() throws Exception {
        Properties prop = new Properties();
        String filename = "auth0.properties";
        InputStream input = getClass().getClassLoader().getResourceAsStream(filename);

        prop.load(input);
        String url = prop.getProperty("auth0.signIn");

        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(user);

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
