package de.enwaffel.randomutils.callback;

import de.enwaffel.randomutils.http.HttpStatus;

public interface PostResponse {
    void call(HttpStatus responseCode, String responseBody);
}
