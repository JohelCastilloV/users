package com.sermaluc.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void registerUserSuccessful() throws Exception {

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Juan Rodriguez",
                                  "email": "juan2@dominio.cl",
                                  "password": "hola211312",
                                  "phones": [
                                    {
                                      "number": "1234567",
                                      "cityCode": "1",
                                      "countryCode": "57"
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isActive", is(true)));

    }

    @Test
    void registerUserWhenEmailAlreadyExists() throws Exception {
        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Juan Rodriguez",
                                  "email": "juan@dominio.cl",
                                  "password": "hola211312",
                                  "phones": [
                                    {
                                      "number": "1234567",
                                      "cityCode": "1",
                                      "countryCode": "57"
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isCreated());

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Juan Rodriguez",
                                  "email": "juan@dominio.cl",
                                  "password": "hola211312",
                                  "phones": [
                                    {
                                      "number": "1234567",
                                      "cityCode": "1",
                                      "countryCode": "57"
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("El correo ya registrado.")));

    }
    @Test
    void registerUserWhenEmailIsNotValid() throws Exception {

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Juan Rodriguez",
                                  "email": "juan2@dominio.pe",
                                  "password": "hola211312",
                                  "phones": [
                                    {
                                      "number": "1234567",
                                      "cityCode": "1",
                                      "countryCode": "57"
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("correo de formato invalido")));

    }

    @Test
    void registerUserWhePasswordIsNotValid() throws Exception {
        // password valid 8-16 alphanumeric

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Juan Rodriguez",
                                  "email": "juan2@dominio.cl",
                                  "password": "hola21@",
                                  "phones": [
                                    {
                                      "number": "1234567",
                                      "cityCode": "1",
                                      "countryCode": "57"
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensaje", is("formato de password invalido")));

    }


}
