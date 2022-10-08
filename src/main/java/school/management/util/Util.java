package school.management.util;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class Util {

    public static String convertToString(ObjectError error) {
        if (error == null) return "";

        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();

        return "Field " + fieldName + " " + errorMessage;
    }
}
