package ie.dc.sensor;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class SensorController {
    private final SensorService sensorService;

    //Constructor Injection
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    //Coding challenge
    //Get sensor based on sensor id and date range
    @GetMapping("/query")
    public ResponseEntity<Map<String, List<SensorDTO>>> queryChallenge(
            @RequestParam List<String> sensorId,
            @RequestParam List<String> metricType,
            @RequestParam(required = false, defaultValue = "average") String stat,
            @RequestParam(required = false)  String startDate,
            @RequestParam(required = false)  String endDate
    ){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        //If no dates are included in query set date period to the past 24 hours (latest data)
        LocalDateTime start = (startDate !=null) ?
                LocalDateTime.parse(startDate, formatter)
                : LocalDateTime.now().minusDays(1);

        LocalDateTime end = (endDate !=null) ?
                LocalDateTime.parse(endDate, formatter)
                : LocalDateTime.now();

        List<SensorDTO> sensors = sensorService.findByUserQuery(sensorId, metricType, stat, start,end);
        Map<String, List<SensorDTO>> response = new HashMap<>();
        response.put("Result", sensors);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    //Save sensor to database
    @PostMapping("/addSensor")
    public Sensor createSensor(@RequestBody @Valid Sensor sensor) {
        sensorService.saveSensor(sensor);
        return sensor;
    }

    //return all sensors in db
    @GetMapping("/queryAllSensor")
    public ResponseEntity<Map<String, List<Sensor>>> queryAllSensor() {
        List<Sensor> sensorsList = sensorService.findAllSensors();
        Map<String, List<Sensor>> response = new HashMap<>();
        //Using map for a Key Value structure
        response.put("All sensors", sensorsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get sensors by sensorIds
    @GetMapping("/querySensorsById")
    public ResponseEntity<Map<String, List<Sensor>>> queryAllSensors(
            @RequestParam List<String> sensorId) {
        List<Sensor> sensorsFound = sensorService.getSensorBySensorID(sensorId);
        Map<String, List<Sensor>> response = new HashMap<>();
        response.put("Sensors found: ", sensorsFound);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get sensor based on time created
    @GetMapping("/getSensorBetweenDates")
    public ResponseEntity<Map<String, List<Sensor>>> getSensorBetweenDates(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        //Convert String dates from params to LocalDateTime dates for querying MongoDB
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        List<Sensor> sensorsList = sensorService.findSensorsBetweenDates(start, end);
        Map<String, List<Sensor>> response = new HashMap<>();
        response.put("Sensors", sensorsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //Get sensor based on sensor id and date range
    @GetMapping("/querySensorWithDates")
    public ResponseEntity<Map<String, List<Sensor>>> querySensorWithDates(
            @RequestParam List<String> sensorId,
            @RequestParam(required = false)  String startDate,
            @RequestParam(required = false)  String endDate
    ){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        //If no dates are included in query set date period to the past 24 hours (latest data)
        LocalDateTime start = (startDate !=null) ?
                LocalDateTime.parse(startDate, formatter)
                : LocalDateTime.now().minusDays(1);

        LocalDateTime end = (endDate !=null) ?
            LocalDateTime.parse(endDate, formatter)
                : LocalDateTime.now();

        List<Sensor> sensorsList = sensorService.getSensorStartAndEnd(sensorId, start,end);
        Map<String, List<Sensor>> response = new HashMap<>();
        response.put("Sensors", sensorsList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Exception handler for validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
