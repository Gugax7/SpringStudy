package com.ggx.dev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {
    public static void main(String[] args) {
        //String jsonCar = "{\"the_model\":\"Civic\",\"year\":2025}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //Car car = objectMapper.readValue(jsonCar, Car.class);
            Car car = new Car();
            car.setModel("Civic");
            car.setYear(2025);
            car.setKey(1235);

            System.out.println(car.getModel() + " " + car.getYear());
            String carAsJson = objectMapper.writeValueAsString(car);
            System.out.println(carAsJson);

            ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(carAsJson);
            jsonNode.put("model", "City");
            jsonNode.put("isManual", false);

            String updatedCar = objectMapper.writeValueAsString(jsonNode);
            System.out.println(updatedCar);
        }catch (JsonProcessingException e ){
            e.printStackTrace();
        }
    }
}