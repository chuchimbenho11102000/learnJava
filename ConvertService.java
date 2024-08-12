import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConvertService {
    public static <T> T mapData(Object current, Object input, Class<T> classMap) {
        try {
            Field[] fieldCurrents = current.getClass().getDeclaredFields();
            Field[] inputFields = input.getClass().getDeclaredFields();
            Map<String, Object> inputMap = new HashMap<>();
            Arrays.stream(inputFields).forEach(field -> {
                field.setAccessible(true);

                try {
                    if (field.get(input) != null) {
                        inputMap.put(field.getName(), field.get(input));
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            Map<String, Object> fieldMap = new HashMap<>();
            for (Field fieldCurrent : fieldCurrents) {
                fieldCurrent.setAccessible(true);
                if (inputMap.containsKey(fieldCurrent.getName())) {
                    fieldMap.put(fieldCurrent.getName(), inputMap.get(fieldCurrent.getName()));
                }else {
                    fieldMap.put(fieldCurrent.getName(), fieldCurrent.get(current));
                }

            }

            return buildModel(fieldMap,classMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static  <T> T buildModel(Map<String, Object> stringObjectMap, Class<T> classMap) throws InstantiationException, IllegalAccessException {
        Object object = classMap.newInstance();
        Field[] fieldObjects = object.getClass().getDeclaredFields();
        for (Field fieldObject : fieldObjects) {
            if (stringObjectMap.containsKey(fieldObject.getName())) {
                fieldObject.setAccessible(true);
                fieldObject.set(object, stringObjectMap.get(fieldObject.getName()));
            }
        }
        return (T) object;
    }
}
