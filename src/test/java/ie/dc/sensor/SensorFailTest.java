package ie.dc.sensor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SensorFailTest {
    @BeforeEach
    void setUp() {}

    @Test
    void testSensorIdMinFailure() {
        Exception exMessage = assertThrows(IllegalArgumentException.class, () -> new Sensor("1","s1", "temperature", 19.20, LocalDateTime.now()));
        assertEquals("Sensor ID must be between 3 and 10 characters", exMessage.getMessage());
    }

    @Test
    void testSensorIdMaxFailure() {
        Exception exMessage = assertThrows(IllegalArgumentException.class, () -> new Sensor("1","sensor123456", "temperature", 19.20, LocalDateTime.now()));
        assertEquals("Sensor ID must be between 3 and 10 characters", exMessage.getMessage());
    }





}
