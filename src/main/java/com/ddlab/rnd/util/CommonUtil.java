package com.ddlab.rnd.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CommonUtil {
  public static String convertToJsonString(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    String toJson = null;
    try {
      toJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return toJson;
  }
}
