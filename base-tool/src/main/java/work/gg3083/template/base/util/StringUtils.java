package work.gg3083.template.base.util;

import java.util.Objects;

public class StringUtils {


    public static boolean isEmpty(Object value) {
        return value == null || Objects.equals(value, "");
    }

}
