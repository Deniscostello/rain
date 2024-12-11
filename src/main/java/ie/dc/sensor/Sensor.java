package ie.dc.sensor;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("sensors")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Sensor {

    @Id
    private String id;

    private String sensorId;
    private String metricType;
    private double value;
    private LocalDateTime savedDate;


    public Sensor(String id, String sensorId, String metricType, double value, LocalDateTime savedDate) {
        setId(id);
        setSensorId(sensorId);
        setMetricType(metricType);
        setValue(value);
        setSavedDate(LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(LocalDateTime savedDate) {
        this.savedDate = savedDate;
    }
}
