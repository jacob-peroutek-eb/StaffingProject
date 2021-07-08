package com.jperoutek.staffingproject.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ApplicationUtil {
    private static final Logger log = LoggerFactory.getLogger(ApplicationUtil.class);

    public static String convertToJSON(Object obj){
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error while converting to JSON {} ", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void copyProperties(Object source, Object target){
        if(source==null){
            log.warn("The source object is null while copying the properties");
            //throw back
        }
        log.debug("The source object is while copying the properties {} ", source.toString());
        BeanUtils.copyProperties(source, target);
        log.debug("The target object is while copying the properties {} ", target.toString());
    }

    public static <T> T mapJsonToObject(String json, Class<T> desiredType) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, desiredType);
    }

    public static <T extends Comparable<T>> boolean haveDuplicates (T[] array ){
        Set<T> set = new HashSet<T>();
        for(T i : array){
            if(!set.add(i))
                return true;
        }
        return false;
    }
}
