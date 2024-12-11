package ie.dc.sensor;

public class SensorDTO {
    private String sensorId;
    private String type;
    private String stat;
    private Double calculationValue;

    public SensorDTO(String sensorId, String type, String stat, Double calculationValue) {
        this.sensorId = sensorId;
        this.type = type;
        this.stat = stat;
        this.calculationValue = calculationValue;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Double getCalculationValue() {
        return calculationValue;
    }

    public void setCalculationValue(Double calculationValue) {
        this.calculationValue = calculationValue;
    }

}