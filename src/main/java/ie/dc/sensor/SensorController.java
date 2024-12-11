package ie.dc.sensor;

import jakarta.validation.Valid;

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

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @PostMapping("/addSensor")
    public Sensor createSensor(@RequestBody @Valid Sensor sensor) {
        System.out.println(sensor);
        sensorService.saveSensor(sensor);
        return sensor;
    }

    //return all sensors in db
    @GetMapping("/queryAllSensor")
    public ResponseEntity<Map<String, List<Sensor>>> queryAllSensor() {
        List<Sensor> sensorsList = sensorService.findAllSensors();
        Map<String, List<Sensor>> response = new HashMap<>();
        response.put("All sensors", sensorsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/querySensorsById")
    public ResponseEntity<Map<String, List<Sensor>>> queryAllSensors(
            @RequestParam List<String> sensorId) {
        List<Sensor> sensorsFound = sensorService.getSensorBySensorID(sensorId);
        Map<String, List<Sensor>> response = new HashMap<>();
        response.put("Sensors found: ", sensorsFound);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


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
