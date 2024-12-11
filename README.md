# Coding Challenge

Sensor Data REST API

## Primary Endpoints
1. POST /api/addSensor 
    <br> Add/Save Sensor data to MongoDB <br>
    Request Body:
   <br>{<br> 
   "sensorId": "sensor2", <br>
   "metricType": "temperature",<br>
    "value": 11.00<br>
   }
2. GET /api/query
    <br> Coding challenge endpoint
    <br> Query params
   1. sensorId (required): Single or multiple sensor Ids, i.e. sensor1, sensor5
   2. metricType (required): Single or multiple metric types, e.g. temperature, humidity
   3. stat (optional): Stat provided for result, i.e. (average, sum,min,max). Default is average
   4. startDate (optional): The start of date in range. Defaults past 24 hours.
   5. endDate (optional): The end of date in range. Default is when the request is sent.
<br> <br> Example query: http://localhost:8080/api/query?sensorId=sensor1&metricType=temperature&stat=average&startDate=2024-12-04T00:00:00&endDate=2024-12-11T23:59:59
   <br> Response: Result:
   <br>[
      {<br>"sensorId": "sensor1", 
   <br> "type": "temperature", 
   <br> "stat": "average", 
   <br>"calculationValue": 24.0<br>
   }]

### Other Endpoints

1. GET /api/queryAllSensor
<br> get all sensors in database
2. GET /api/querySensorsById
<br> Get sensors by their sensor ids
3. GET /api/querySensorWithDates
<br> Get sensors based on date range
