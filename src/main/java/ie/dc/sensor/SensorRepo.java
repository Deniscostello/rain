package ie.dc.sensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepo extends MongoRepository<Sensor, String> {

}
