package de.nazaruk.routes.controller;

import de.nazaruk.routes.service.RoutesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoutesControllerTest {

    @Autowired
    private RoutesService routesService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void direct() throws Exception {
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "3")
                .param("arr_sid", "6"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"dep_sid\":3,\"arr_sid\":6,\"direct_bus_route\":true}"))
                .andReturn();
    }

    @Test
    public void notDirect() throws Exception {
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "2")
                .param("arr_sid", "6"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"dep_sid\":2,\"arr_sid\":6,\"direct_bus_route\":false}"))
                .andReturn();
    }

}
