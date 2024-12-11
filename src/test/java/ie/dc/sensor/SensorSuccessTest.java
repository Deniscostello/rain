package ie.dc.sensor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SensorSuccessTest {
    @BeforeEach
    void setUp() {}

    //Unit Test on sensorId Success
    @Test
    void testSensorIdSuccess(){
        Sensor sensor = new Sensor("1", "sensor1", "temperature", 15.00, LocalDateTime.now());
    }


    @AfterEach
    void tearDown() {}
}
