package app;

import app.controller.CarController;
import app.controller.ControllerExceptionHandler;
import app.model.Car;
import app.model.SearchCarRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static app.service.CarServiceImpl.DEFAULT_CAR_BRAND;
import static app.service.CarServiceImpl.DEFAULT_CAR_MODEL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionHandlerTest {

    @Autowired
    private CarController carController;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void createDefaultCar() {
        ResponseEntity<Car> responseEntity = carController.create(true);
        Car car = responseEntity.getBody();
        assertNotNull(car);
        assertEquals(car.getBrand(), DEFAULT_CAR_BRAND);
        assertEquals(car.getModel(), DEFAULT_CAR_MODEL);
    }

    @Test
    public void createCar() throws Exception {
        mockMvc.perform(get("/cars/create/false"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findCar() throws Exception {
        SearchCarRequest searchCarRequest = SearchCarRequest.builder()
                .brand("toyota")
                .build();

        mockMvc.perform(put("/cars/find")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(searchCarRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}