

package com.example.accmanager;
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
public class testNumber0ForPCO8080Tests extends TestNGCitrusSpringSupport{
    @CitrusResource
    private TestContext context;
     private static ClientAndServer mockServer;
private static final Logger log = Logger.getLogger("autoGeneratedTests");

    
        @BeforeClass
        public static void setup() {
           mockServer = startClientAndServer(1080);















































               


               

               };


    @Test
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
                                .get("/acc/banque/comptes")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response1")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode"))
);        
                            variable("body",
                "citrus:message(Response1.body())");
                variable("status", "${statusCode}");
                String body = context.getVariable("body");
                String status = context.getVariable("status");
                assertEquals("200",status);
                verif();

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/acc/banque/comptes")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("[{\"id\":1,\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"}]")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/1")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/2")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,500)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("[{\"id\":1,\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"}]")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/acc/banque/comptes")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"premierCompte\",\"account\":200}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,500)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/acc/banque/comptes")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"deuxieme\",\"account\":200,\"prenom\":\"compte\",\"risk\":\"HIGH\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"deuxieme\",\"account\":200,\"prenom\":\"compte\",\"risk\":\"HIGH\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("[{\"id\":1,\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"},{\"id\":3,\"nom\":\"deuxieme\",\"account\":200,\"prenom\":\"compte\",\"risk\":\"HIGH\"}]")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/3")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"HIGH\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/8")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"HIGH\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/8")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,500)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("[{\"id\":1,\"nom\":\"premierCompte\",\"account\":200,\"prenom\":\"prem\",\"risk\":\"Low\"},{\"id\":3,\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"HIGH\"},{\"id\":4,\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"HIGH\"}]")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .delete("/acc/banque/comptes/4")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/1")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"premier\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"low\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/3")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"high\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/1")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"premier\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"low\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/1")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":1,\"nom\":\"premier\",\"account\":300,\"prenom\":\"compte\",\"risk\":\"low\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/1")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"premier\",\"account\":300,\"prenom\":\"compte\",\"risk\":\"low\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/1")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":1,\"nom\":\"premier\",\"account\":20300,\"prenom\":\"compte\",\"risk\":\"low\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/2")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,500)
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/3")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"high\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/acc/banque/comptes/3")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"deuxieme\",\"account\":100,\"prenom\":\"compte\",\"risk\":\"high\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/acc/banque/comptes/3")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":3,\"nom\":\"deuxieme\",\"account\":300,\"prenom\":\"compte\",\"risk\":\"high\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .extract(fromHeaders()
                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, "statusCode"))
);        

                variable("body",
                "citrus:message(Response.body())");
                variable("status", "${statusCode}");
                body = context.getVariable("body");
                status = context.getVariable("status");
                assertEquals("200",status);
                verif();
               }        

            public static void verif() {
           }

        @AfterClass 
        public void stopServer() { 
            mockServer.stop();
        }
    } 
