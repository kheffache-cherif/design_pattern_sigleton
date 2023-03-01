package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// class generique
public class JsonSerializer<T> {
    public String toJson(T dataObject){
        ObjectMapper  objectMapper= new ObjectMapper();
        // exception surveillée obligatoire
        try {
          String json =  objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataObject);
          return json;
        } catch (JsonProcessingException e) { // je prends exception surveillée
            throw new RuntimeException(e);// je la remplace par une autre non surveillée
        }
    }
}
