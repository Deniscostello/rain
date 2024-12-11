package ie.dc.sensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepo extends MongoRepository<Sensor, String> {

    List<Sensor> findBySensorIdIn(List<String> sensorId);
}
