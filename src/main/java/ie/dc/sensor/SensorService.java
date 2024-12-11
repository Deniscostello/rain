package ie.dc.sensor;

import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private SensorRepo sensorRepo;

    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public void saveSensor(Sensor sensor) {
        sensorRepo.save(sensor);

    }
}
