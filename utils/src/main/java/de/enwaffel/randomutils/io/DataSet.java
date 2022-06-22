package de.enwaffel.randomutils.io;

public class DataSet<T> {

    private final String key;
    private T data;

    public DataSet(String key) {
        this.key = key;
    }

    public DataSet(String key, T data) {
        this.key = key;
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

}
