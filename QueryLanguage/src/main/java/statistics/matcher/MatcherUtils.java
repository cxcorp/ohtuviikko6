package statistics.matcher;

import java.lang.reflect.Method;

class MatcherUtils {

    /**
     * Attempts to get the value of a field on the target object via its getter.
     * @throws ReflectiveOperationException if the getter is not found, its
     *                                      invocation fails or it is
     *                                      inaccessible.
     */
    public static Object getFieldValue(
        Object target,
        String fieldName
    ) throws ReflectiveOperationException {
        String getterName = resolveGetterName(fieldName);
        Method method = target.getClass().getMethod(getterName);
        return method.invoke(target);
    }

    private static String resolveGetterName(String fieldName) {
        return "get" + capitalize(fieldName);
    }

    private static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
