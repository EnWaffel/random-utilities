package de.enwaffel.randomutils;

public class Property {

    private Object value;

    public Property(Object value) {
        this.value = value;
    }

    public String s() {
        return (String) value;
    }

    public double d() {
        return (double) value;
    }

    public int i() {
        return (int) value;
    }

    public boolean b() {
        return (boolean) value;
    }

    public Object o() {
        return value;
    }

    public <T> T any() {
        return (T) value;
    }

    public boolean isNull() {
        return value == null;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
