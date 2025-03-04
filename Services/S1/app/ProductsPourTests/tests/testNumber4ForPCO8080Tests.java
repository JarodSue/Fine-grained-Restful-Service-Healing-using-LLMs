

package com.example;
import static com.consol.citrus.actions.ReceiveMessageAction.Builder.receive;
import static com.consol.citrus.container.Conditional.Builder.conditional;import static org.junit.jupiter.api.Assertions.assertEquals;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.mockserver.integration.ClientAndServer;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import org.mockserver.model.Header;import org.springframework.http.MediaType;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class testNumber4ForPCO8080Tests extends TestNGCitrusSpringSupport{
    @CitrusResource
    private TestContext context;
     private static ClientAndServer mockServer;
private static final Logger log = Logger.getLogger("autoGeneratedTests");

    
        @BeforeTest
        public static void setup() {
           mockServer = startClientAndServer(1080);

               mockServer.when(
                    request()
                      .withMethod("GET")
                      .withPath("/ident/TokenVerification")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           




               mockServer.when(
                    request()
                      .withMethod("GET")
                      .withPath("/ident/TokenVerification")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           




               mockServer.when(
                    request()
                      .withMethod("GET")
                      .withPath("/ident/TokenVerification")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           




               mockServer.when(
                    request()
                      .withMethod("GET")
                      .withPath("/ident/TokenVerification")
                      .withHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
       .respond(
                          response()
                            .withStatusCode(200)
                            .withBody("")
                                   .withHeaders(
                      new Header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ"))
                            .withDelay(TimeUnit.SECONDS,1)
                        );
           


               };


     @Test(groups = "a")
    @CitrusTest
    public void testAlgo1() throws FileNotFoundException{
                            HttpClient toClient = CitrusEndpoints
                                .http()
                                .client()
                                .requestUrl("http://localhost:8080/")
                                .build();
                            $(http()
                                .client(toClient)
                                .send()
                                .post("prod/Admin/Products/ajout")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"name\":\"objetPut\",\"cost\":200,\"buy\":200,\"stock\":80}")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
                              .accept(MediaType.ALL_VALUE));



                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
));        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("prod/Admin/Products/ajout")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"name\":\"objetPut\",\"cost\":200,\"buy\":200,\"stock\":80}")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
                              .accept(MediaType.ALL_VALUE));



                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, 400)
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("prod/Products")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"name\":\"objetPut\",\"cost\":300,\"buy\":200,\"stock\":80}")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
                              .accept(MediaType.ALL_VALUE));



                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, 200)
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .delete("/prod/Products/1")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
                              .accept(MediaType.ALL_VALUE));



                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode")
                                   .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ")
));        

                variable("body",
                "citrus:message(Response.body())");
                variable("status", "${statusCode}");
                String body = context.getVariable("body");
                String status = context.getVariable("status");
                assertEquals("200",status);
           verif();
               }        

    
    
    @Test(groups = "b",dependsOnGroups = "a")
    public static void Xreste() throws InterruptedException{
        try{
                System.out.println("la");
                URL url = new URL("http://localhost:8080/prod/Admin/Products/ajout");
                URLConnection con = url.openConnection();
                HttpURLConnection http = (HttpURLConnection)con;
                http.setDoOutput(true);
                http.setRequestMethod("POST"); // PUT is another valid option
                http.setRequestProperty("Content-Type", "application/json");
                http.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNrIiwiaWQiOjEsImV4cCI6MTY3ODQ1MTU3OCwiaWF0IjoxNjc4MzY1MTc4fQ.iNqFeICxMUFqyssFLHGRc4x-CDr2qpM7n1lHURCERKa6t882HRh3RVhkx6XH7zSzwcivc9TQytnkgT0eno_nXQ");
                byte[] out = "{\"name\":\"ObjectToDrop\",\"cost\":300,\"buy\":200,\"stock\":80}" .getBytes(StandardCharsets.UTF_8);
                try(OutputStream os = http.getOutputStream()) {
                    os.write(out);
                }
                catch(Exception e){
                    System.out.println(e);
                }
                int responseCode = http.getResponseCode();
                http.disconnect();
                assertEquals(200,responseCode);
                }
                catch(Exception e){
                    System.out.println(e);
                }
        
    }
    @Test(dependsOnGroups = "b")
    public static void Yreste() throws InterruptedException{
        try{
            URL url2 = new URL("http://localhost:8080/prod/Drop");
                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                con2.setRequestMethod("GET");   
                int responseCode = con2.getResponseCode();
                con2.disconnect();
                 
                  }
            catch(Exception e){
                System.out.println(e);
            }
        
        
        
    }
    
    
            public static void verif() {
        mockServer.verify(
                        request().withPath("/ident/TokenVerification"),
                        VerificationTimes.exactly(4)
        ); 
            }

        @AfterTest  
        public void stopServer() { 
            mockServer.stop();
        }
    } 
