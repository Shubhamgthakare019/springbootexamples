package com.java.bootcsv.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Response;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

//@SpringBootTest(classes = CSVControllerTest.class)
//@ExtendWith(MockitoExtension.class)
public class CSVControllerTest {

   // @Mock
   // private RestTemplate restTemplate;

   // @InjectMocks
   // private CSVController controller = new CSVController();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8084);

    String url = "http://localhost:8084/customer/getCustomer/1";


    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static WireMockServer server = new WireMockServer(PORT);

    @BeforeClass
    private void initializeServer() {
        System.out.println("Init");

        server.start();
        WireMock.configureFor(HOST, PORT);

        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200);
        mockResponse.withBody("{\\n\" +\n" +
                "                \"    \\\"customerId\\\": \\\"2222\\\",\\n\" +\n" +
                "                \"    \\\"customerName\\\": \\\"Ronaldo\\\"\\n\" +\n" +
                "                \"}");

        //https://github.com/sheetalrepo/MockPractice/blob/master/src/test/java/com/wiremock/B_StartServerFromCode.java
        // Mock-Request - both below format will work
       // WireMock.stubFor(WireMock.get("/customer/getCustomer/1").willReturn(mockResponse));
        //WireMock.givenThat(WireMock.get("/customer/getCustomer/1").willReturn(mockResponse))
    }

    @Test
    public void test() {
        String testApi = "http://localhost:" + PORT + "/customer/getCustomer/1";

        System.out.println("Service to be hit " + testApi);

        Response response = (Response) RestAssured
                .given()
                .get("http://localhost:8080/customer/getCustomer/1")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println();
        Assert.assertEquals(response.getBody().toString(), "{\n" +
                "    \"customerId\": \"2222\",\n" +
                "    \"customerName\": \"Ronaldo\"\n" +
                "}");
    }


    @AfterClass
    public void closeServer() {
        if (server.isRunning() && null != server) {
            System.out.println("Shutdown");
            server.shutdown();
        }
    }


  /*  @Test
    public void testUploadFile() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String path = "C:\\Users\\SHUTHAKA\\intellij-workspace\\boot-csv\\src\\test\\resources\\cutomerdata.csv";
        Customer customer = new Customer("1", "Kartik");

        /*Mockito.when(restTemplate.getForObject(
                "http://localhost:8082/customer/getCustomer/1", Customer.class))
                .thenReturn(customer);

        StubMapping stubbing = stubFor(get(urlEqualTo(url)).withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200)));


        FileInputStream inputStream = new FileInputStream(path);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "customerdata.csv",
                "multipart/form-data", inputStream);

        ResponseEntity<String> cust = controller.uploadFile(multipartFile);
        Assertions.assertEquals(customer, cust);
    } */

}
