package ie.dc.sensor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Document(collection = "Sensors")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Sensor {

    @Id
    private String id;

    @Size(min=3, max =10, message = "Sensor ID must be between 3 and 10 characters")
    private String sensorId;

    @NotBlank(message="Metric cannot be blank")
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
        if("temperature".equalsIgnoreCase(metricType) || "humidity".equalsIgnoreCase(metricType) || "wind_speed".equalsIgnoreCase(metricType)) {
            this.metricType = metricType;
        }
        else{
            throw new IllegalArgumentException("Please enter 'Temperature', 'Humidity', or 'Wind_Speed'");
        }
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
