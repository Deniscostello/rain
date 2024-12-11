package ie.dc.sensor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SensorService sensorService;

    @Test
    void allSensors() throws Exception {
        List<Sensor> testSensors = List.of(
                new Sensor("123","sensor3", "temperature", 14.5, LocalDateTime.now()),
                new Sensor("321","sensor11", "temperature", 15.5, LocalDateTime.now())
        );
        when(sensorService.getSensorBySensorID(anyList())).thenReturn(testSensors);

        mockMvc.perform(get("/api/querySensorsById")
                .param("sensorId", "sensor3","sensor11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Sensors found: ']").isArray())
                .andExpect(jsonPath("$.['Sensors found: '].length()").value(2))
                .andExpect(jsonPath("$.['Sensors found: '][0].sensorId").value("sensor3"))
                .andExpect(jsonPath("$.['Sensors found: '][1].sensorId").value("sensor11"));

        verify(sensorService, times(1)).getSensorBySensorID(anyList());
    }
}
