package de.enwaffel.randomutils.sql;

public class SQLManager {

    public SQL connect(Class<? extends SQL> t, Object... parameters) {
        try {
            Class<?>[] classParameters = new Class<?>[parameters.length];
            for (int i = 0;i < parameters.length;i++) {
                classParameters[i] = parameters[i].getClass();
            }
            return t.getDeclaredConstructor(classParameters).newInstance(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
