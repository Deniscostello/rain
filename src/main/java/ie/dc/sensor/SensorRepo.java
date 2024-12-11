package ie.dc.sensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepo extends MongoRepository<Sensor, String> {

    List<Sensor> findBySensorIdIn(List<String> sensorId);

    List<Sensor> findBySavedDateBetween(LocalDateTime savedDateAfter, LocalDateTime savedDateBefore);
}
