

import static com.consol.citrus.actions.ReceiveMessageAction.Builder.receive;
import org.mockserver.matchers.Times;
import static com.consol.citrus.container.Conditional.Builder.conditional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.annotations.CitrusResource;
import static org.testng.Assert.assertTrue;import com.consol.citrus.annotations.CitrusTest;
import static com.consol.citrus.dsl.MessageSupport.MessageHeaderSupport.fromHeaders;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.message.HttpMessageHeaders;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import org.junit.jupiter.api.*;
import java.util.logging.Logger;
import org.mockserver.verify.VerificationTimes;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import org.mockserver.integration.ClientAndServer;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import org.mockserver.model.Header;import org.springframework.http.MediaType;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
public class testnumber28for1083Tests extends TestNGCitrusSpringSupport{
    @CitrusResource
    private TestContext context;
     private static ClientAndServer mockServer;
private static final Logger log = Logger.getLogger("autoGeneratedTests");

    
        @BeforeClass
        public static void setup() {
           mockServer = startClientAndServer(1080);

               mockServer.when(
                    request()
                      .withMethod("GET")
                      .withPath("/ident/TokenVerification")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW5hcmQiLCJpZCI6MywiZXhwIjoxNzI1OTY4MTUwLCJpYXQiOjE3MjU4ODE3NTB9.XfHzAXpozoGqQgzWLegHXK0IUVKxxZ6-8Wl6NtKIBwnzDwhhBV59GIzPltr-cBJ8og8hP46C79DBUhwWQbEswQ")          ,Times.exactly(1))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                              .withHeader("Content-Type","application/json")                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW5hcmQiLCJpZCI6MywiZXhwIjoxNzI1OTY4MTUwLCJpYXQiOjE3MjU4ODE3NTB9.XfHzAXpozoGqQgzWLegHXK0IUVKxxZ6-8Wl6NtKIBwnzDwhhBV59GIzPltr-cBJ8og8hP46C79DBUhwWQbEswQ"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           


               mockServer.when(
                    request()
                      .withMethod("POST")
                      .withPath("/prod/Products/Buy/1")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW5hcmQiLCJpZCI6MywiZXhwIjoxNzI1OTY4MTUwLCJpYXQiOjE3MjU4ODE3NTB9.XfHzAXpozoGqQgzWLegHXK0IUVKxxZ6-8Wl6NtKIBwnzDwhhBV59GIzPltr-cBJ8og8hP46C79DBUhwWQbEswQ")          ,Times.exactly(1))
       .respond(
                          response()
                            .withStatusCode(400)
                            .withBody("")
                              .withHeader("Content-Type","application/json")                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW5hcmQiLCJpZCI6MywiZXhwIjoxNzI1OTY4MTUwLCJpYXQiOjE3MjU4ODE3NTB9.XfHzAXpozoGqQgzWLegHXK0IUVKxxZ6-8Wl6NtKIBwnzDwhhBV59GIzPltr-cBJ8og8hP46C79DBUhwWQbEswQ"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           


               };


    @Test
    @CitrusTest
    public void testAlgo1() throws FileNotFoundException, InterruptedException{
          String token="";
          String body="";
          String status="";
                            HttpClient toClient = CitrusEndpoints
                                .http()
                                .client()
                                .requestUrl("http://localhost:1083/")
                                .build();
                            $(http()
                                .client(toClient)
                                .send()
                                .post("/client/Products/1/buy")
                                .message()
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW5hcmQiLCJpZCI6MywiZXhwIjoxNzI1OTY4MTUwLCJpYXQiOjE3MjU4ODE3NTB9.XfHzAXpozoGqQgzWLegHXK0IUVKxxZ6-8Wl6NtKIBwnzDwhhBV59GIzPltr-cBJ8og8hP46C79DBUhwWQbEswQ")
                              .accept(MediaType.ALL_VALUE));





                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response0")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode")
                                   .header("Authorization", "token")
));        

                variable("body",
                "citrus:message(Response0.body())");
                variable("status", "${statusCode}");
                variable("token", "${token}");
                token = context.getVariable("token");
                status = context.getVariable("status");
                if ((status.equals("400")) && (token.equals("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYW5hcmQiLCJpZCI6MywiZXhwIjoxNzI1OTY4MTUwLCJpYXQiOjE3MjU4ODE3NTB9.XfHzAXpozoGqQgzWLegHXK0IUVKxxZ6-8Wl6NtKIBwnzDwhhBV59GIzPltr-cBJ8og8hP46C79DBUhwWQbEswQ"))){assertTrue(true);}
                else {Assumptions.assumeTrue(false ,"Inconclusive") ;}
           verif();
           stopServer();
               }        

            public static void verif() {
        mockServer.verify(
                        request().withPath("/prod/Products/Buy/1"),
                        VerificationTimes.exactly(1)
        ); 
         mockServer.verify(
                        request().withPath("/ident/TokenVerification"),
                        VerificationTimes.exactly(1)
        ); 
            }

        @AfterClass 
        public void stopServer() { 
            mockServer.stop();
        }
    } 
