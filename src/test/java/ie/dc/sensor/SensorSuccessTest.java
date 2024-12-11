package ie.dc.sensor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class SensorSuccessTest {
    @BeforeEach
    void setUp() {}

//    //Unit Test on sensorId Success
//    @Test
//    void testSensorIdSuccess(){
//        Sensor sensor = new Sensor("1", "sensor1", "temperature", 15.00, LocalDateTime.now());
//        assertEquals("sensor1", sensor.getSensorId());
//    }

    @Test
    void testSensorMetricTempSuccess(){
        Sensor sensor = new Sensor("1", "sensor1", "temperature", 15.00, LocalDateTime.now());
        assertEquals("temperature", sensor.getMetricType());
    }

    @Test
    void testSensorMetricHumSuccess(){
        Sensor sensor = new Sensor("1", "sensor1", "humidity", 15.00, LocalDateTime.now());
        assertEquals("humidity", sensor.getMetricType());
    }

    @Test
    void testSensorMetricWSSuccess(){
        Sensor sensor = new Sensor("1", "sensor1", "wind_speed", 15.00, LocalDateTime.now());
        assertEquals("wind_speed", sensor.getMetricType());
    }




    @AfterEach
    void tearDown() {}
}
