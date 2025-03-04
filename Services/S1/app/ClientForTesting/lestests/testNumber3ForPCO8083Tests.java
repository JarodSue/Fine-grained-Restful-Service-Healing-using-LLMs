

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
public class testNumber3ForPCO8083Tests extends TestNGCitrusSpringSupport{
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
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTcwNjI3NzgxMCwiaWF0IjoxNzA2MTkxNDEwfQ.qRWyB9AUecw4HfQhQmlO9SEu6XjOj7-8poLTcqc02EJ03SAZpB8lB19OvUvY37xbMSlX6GmnOU-U6VMpMA0V9g"),Times.exactly(1))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                              .withHeader("Content-Type","application/json")                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTcwNjI3NzgxMCwiaWF0IjoxNzA2MTkxNDEwfQ.qRWyB9AUecw4HfQhQmlO9SEu6XjOj7-8poLTcqc02EJ03SAZpB8lB19OvUvY37xbMSlX6GmnOU-U6VMpMA0V9g"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           


               mockServer.when(
                    request()
                      .withMethod("POST")
                      .withPath("/prod/Products/Buy/1")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTcwNjI3NzgxMCwiaWF0IjoxNzA2MTkxNDEwfQ.qRWyB9AUecw4HfQhQmlO9SEu6XjOj7-8poLTcqc02EJ03SAZpB8lB19OvUvY37xbMSlX6GmnOU-U6VMpMA0V9g"),Times.exactly(1))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                              .withHeader("Content-Type","application/json")                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTcwNjI3NzgxMCwiaWF0IjoxNzA2MTkxNDEwfQ.qRWyB9AUecw4HfQhQmlO9SEu6XjOj7-8poLTcqc02EJ03SAZpB8lB19OvUvY37xbMSlX6GmnOU-U6VMpMA0V9g"))
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
                                .requestUrl("http://localhost:8083/")
                                .build();
                            $(http()
                                .client(toClient)
                                .send()
                                .post("/client/Products/1/buy")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("1")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTcwNjI3NzgxMCwiaWF0IjoxNzA2MTkxNDEwfQ.qRWyB9AUecw4HfQhQmlO9SEu6XjOj7-8poLTcqc02EJ03SAZpB8lB19OvUvY37xbMSlX6GmnOU-U6VMpMA0V9g")
                              .accept(MediaType.ALL_VALUE));





                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response3")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode")
                                   .header("Authorization", "token")
));        

                variable("body",
                "citrus:message(Response3.body())");
                variable("status", "${statusCode}");
                variable("token", "${token}");
                token = context.getVariable("token");
                status = context.getVariable("status");
                if ((status.equals("200")) && (token.equals("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTcwNjI3NzgxMCwiaWF0IjoxNzA2MTkxNDEwfQ.qRWyB9AUecw4HfQhQmlO9SEu6XjOj7-8poLTcqc02EJ03SAZpB8lB19OvUvY37xbMSlX6GmnOU-U6VMpMA0V9g"))){assertTrue(true);}
                else {Assumptions.assumeTrue(false ,"Inconclusive") ;}

                            $(http()
                                .client(toClient)
                                .send()
                                .delete("/client/Products/1")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response4")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode")
));        

                variable("body",
                "citrus:message(Response4.body())");
                variable("status", "${statusCode}");
                status = context.getVariable("status");
                if ((status.equals("401"))){assertTrue(true);}
                else {Assumptions.assumeTrue(false ,"Inconclusive") ;}
           verif();
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
