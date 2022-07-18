package de.enwaffel.api;

public abstract class NativeAPI {

    protected final APIImpl api;

    public NativeAPI(APIImpl api) {
        this.api = api;
    }

    public APIImpl getAPI() {
        return api;
    }

}
