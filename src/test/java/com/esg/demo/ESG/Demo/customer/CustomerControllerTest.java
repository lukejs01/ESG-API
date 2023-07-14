package com.esg.demo.ESG.Demo.customer;

import com.esg.demo.ESG.Demo.exception.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
public class CustomerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    /**
     * @verifies save records from csv
     * @see CustomerController#saveCustomer(Customer)
     */
    @Test
    public void saveCustomer_shouldSaveRecordsFromCsv() {
        // given
        Customer customer = new Customer();
        customer.setCustomerRef(1L);

        // when
        WebTestClient.ResponseSpec saveCustomer = webTestClient
                .post().uri("/api/Customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange();

        // then
        Customer result = repository.getReferenceById(1L);

        saveCustomer.expectStatus().is2xxSuccessful();

        Assertions.assertEquals(1L, result.getCustomerRef());

    }

    /**
     * @verifies retrieve customer by ref
     * @see CustomerController#getCustomer(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)
     */
    @Test
    public void getCustomer_shouldRetrieveCustomerByRef() {
        // given
        Customer customer = new Customer();
        customer.setCustomerRef(1L);

        // when
        WebTestClient.ResponseSpec saveCustomer = webTestClient
                .post().uri("/api/Customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange();

        // then
        saveCustomer.expectStatus().is2xxSuccessful();

        WebTestClient.ResponseSpec retrieveCustomer = webTestClient
                .get().uri("/api/Customer/byRef").header("customerRef", String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        retrieveCustomer.expectStatus().is2xxSuccessful();

        Customer result = retrieveCustomer
                .expectBody(Customer.class)
                .returnResult()
                .getResponseBody();

        assert result != null;
        Assertions.assertEquals(1L, result.getCustomerRef());
    }

    /**
     * @verifies return 404 if customer not found
     */
    @Test
    public void getCustomer_shouldReturn404IfCustomerNotFound() {
        // given

        // when
        WebTestClient.ResponseSpec retrieveCustomer = webTestClient
                .get().uri("/api/Customer/byRef")
                .header("customerRef", String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        // then
        retrieveCustomer.expectStatus().isNotFound();

    }

    /**
     * @verifies return response with error message
     * @see CustomerController#getCustomer(jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse)
     */
    @Test
    public void getCustomer_shouldReturnResponseWithErrorMessage() throws Exception {
        // given

        // when
        WebTestClient.ResponseSpec retrieveCustomer = webTestClient
                .get().uri("/api/Customer/byRef")
                .header("customerRef", String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        // then
        ErrorResponse response = retrieveCustomer
                .expectBody(ErrorResponse.class)
                .returnResult().getResponseBody();

        assert response != null;
        Assertions.assertEquals("No record in db found for ref: 1", response.getMessage());
    }
}
