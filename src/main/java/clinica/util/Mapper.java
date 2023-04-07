package clinica.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.util.List;

public class Mapper {
  
  private final ObjectMapper objectMapper;
  
  public Mapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }
  
  public <S, T> T map( S element, Class<T> targetClass) throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(element);
    return objectMapper.readValue(json, targetClass);
  }
  
  public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(source);
    return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, targetClass));
  }
  
  public <T> String mapObjectToJson(T t) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.writeValueAsString(t);
  }
  
}