package ie.dc.sensor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorService {

    private final SensorRepo sensorRepo;

    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public void saveSensor(Sensor sensor) {
        sensorRepo.save(sensor);

    }

    public List<Sensor> findAllSensors() {
        return sensorRepo.findAll();
    }

    public List<Sensor> getSensorBySensorID(List<String> sensorId) {
        return sensorRepo.findBySensorIdIn(sensorId);
    }

    public List<Sensor> findSensorsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return sensorRepo.findBySavedDateBetween(start, end);
    }
}
