package ie.dc.sensor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    private final SensorRepo sensorRepo;


    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    //Save to mongodb database
    public void saveSensor(Sensor sensor) {
        sensorRepo.save(sensor);

    }

    //Find all sensors in the database
    public List<Sensor> findAllSensors() {
        return sensorRepo.findAll();
    }

    public List<Sensor> getSensorBySensorID(List<String> sensorId) {
        return sensorRepo.findBySensorIdIn(sensorId);
    }

    public List<Sensor> findSensorsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return sensorRepo.findBySavedDateBetween(start, end);
    }

    public List<Sensor> getSensorStartAndEnd(List<String> sensorId, LocalDateTime start, LocalDateTime end) {
        return sensorRepo.findBySensorIdInAndSavedDateBetween(sensorId,start,end);
    }

    //Filter through sensorIds that meet sensorId and date range
    public List<SensorDTO> findByUserQuery(List<String> sensorId, List<String> metricType, String stat, LocalDateTime start, LocalDateTime end) {
        List<Sensor> sensorsFound = getSensorStartAndEnd(sensorId, start, end);
        List<SensorDTO> response = new ArrayList<>();
        for (String type : metricType) {
            for (String id : sensorId) {
                // Filter the list of sensors based on the sensor IDs and metrics entered by the user
                List<Sensor> filteredSensors = filterSensors(sensorsFound, id, type);

                // Calculate stat values (e.g.,avg,   min, max, sum, etc.) for the filtered sensors
                DoubleSummaryStatistics stats = filteredSensors.stream()
                        .mapToDouble(Sensor::getValue)
                        .summaryStatistics();

                //  calculation based on the user's request
                Double calculationValue = calculate(stat, stats);

                // Create a new SensorDTO object with the sensor ID, type, requested stat, and calculated value
                SensorDTO result = new SensorDTO(id, type, stat, calculationValue);
                response.add(result);
            }
        }
        return response;
    }

    public Double calculate(String stat, DoubleSummaryStatistics stats) {
        return switch (stat.toLowerCase()) {
            case "min" -> stats.getMin();
            case "max" -> stats.getMax();
            case "sum" -> stats.getSum();
            default -> stats.getAverage();
        };
    }


    public List<Sensor> filterSensors(List<Sensor> sensorsFound, String id, String metricType) {
        // Stream through the list of sensors and filter based on the sensorId and metricType
        return sensorsFound.stream()
                .filter(sensor -> id.equals(sensor.getSensorId()) && metricType.equals(sensor.getMetricType()))
                .collect(Collectors.toList());
    }
}
