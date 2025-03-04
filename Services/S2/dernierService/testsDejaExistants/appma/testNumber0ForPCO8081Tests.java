

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
public class testNumber0ForPCO8081Tests extends TestNGCitrusSpringSupport{
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
                                .requestUrl("http://localhost:8081/")
                                .build();
                            $(http()
                                .client(toClient)
                                .send()
                                .get("/app/Approval/approvals")
                                .message()
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
                                .get("/app/Approval/approvals")
                                .message()
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
                                .post("/app/Approval/approvals")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":1,\"nom\":\"name\",\"reponse\":\"accepted\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"name\",\"reponse\":\"accepted\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/app/Approval/approvals")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"refused\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":2,\"nom\":\"name\",\"reponse\":\"refused\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/app/Approval/approvals")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"refused\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"refused\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/app/Approval/approvals/1")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"name\",\"reponse\":\"accepted\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/app/Approval/approvals")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":1,\"nom\":\"premier\",\"reponse\":\"accepted\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"premier\",\"reponse\":\"accepted\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/app/Approval/approvals/1")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":1,\"nom\":\"premier\",\"reponse\":\"accepted\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/app/Approval/approvals/3")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"refused\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/app/Approval/approvals")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":3,\"nom\":\"deuxieme\",\"reponse\":\"refused\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"deuxieme\",\"reponse\":\"refused\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .get("/app/Approval/approvals/3")
                                .message()
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"deuxieme\",\"reponse\":\"refused\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .post("/app/Approval/approvals")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":3,\"nom\":\"deuxieme\",\"reponse\":\"refused\"}")
                              .accept(MediaType.ALL_VALUE));

                            $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"deuxieme\",\"reponse\":\"refused\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .put("/app/Approval/approvals/3")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"accepted\"}")
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
                                .get("/app/Approval/approvals/3")
                                .message()
                              .accept(MediaType.ALL_VALUE));
               variable("conditionnal", "${statusCode}");
               String conditionnal = context.getVariable("conditionnal");
                            if(conditionnal.equals(200)){
                                           $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"accepted\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .delete("/app/Approval/approvals/3")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("")
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
                String body = context.getVariable("body");
                String status = context.getVariable("status");
                assertEquals("200",status);
               }                            if(conditionnal.equals(200)){
                                           $(receive(toClient)
                                .message()
                                .type(MessageType.PLAINTEXT)
                                .name("Response")
                                .header(HttpMessageHeaders.HTTP_STATUS_CODE,200)
                .body("{\"id\":3,\"nom\":\"name\",\"reponse\":\"accepted\"}")
);        

                            $(http()
                                .client(toClient)
                                .send()
                                .delete("/app/Approval/approvals/3")
                                .message()
                                   .header("Content-Type", "application/json")
                                .body("")
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
                String body = context.getVariable("body");
                String status = context.getVariable("status");
                assertEquals("200",status);
               }           verif();
               }        

            public static void verif() {
           }

        @AfterClass 
        public void stopServer() { 
            mockServer.stop();
        }
    } 
