package de.enwaffel.randomutils.callback;

import de.enwaffel.randomutils.http.HttpResponseCode;

public interface PostResponse {
    void call(HttpResponseCode responseCode, String responseBody);
}
