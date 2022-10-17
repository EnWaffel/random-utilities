package de.enwaffel.randomutils.http;

/**
 * A simpler version of the java HttpClient.
 */
public class HttpClient {

    /*
    private final java.net.http.HttpClient client;
    private Timer timer;

    protected HttpClient(java.net.http.HttpClient client) {
        this.client = client;
    }

    public void postAsync(String uri, PostResponse callback, Properties headers, String body) {
        _pb_a(uri, callback, headers, body);
    }

    public void postAsync(String uri, PostResponse callback, Properties headers, JSONObject body) {
        _pb_a(uri, callback, headers, body.toString());
    }

    public void getAsync(String uri) {
        _gb_a(uri);
    }

    private void _pb_a(String uri, PostResponse callback, Properties headers, String body) {
        HttpRequest request = _bbpr(uri, headers, body).build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        _wait();
        future.thenAccept(response -> {
            callback.call(HttpResponseCode.fromCode(response.statusCode()), response.body());
            _complete();
        });
    }

    private void _gb_a(String uri) {
        HttpRequest request = _bbgr(uri).build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        _wait();
        future.thenAccept(response -> {
            System.out.println(HttpResponseCode.fromCode(response.statusCode()));
            System.out.println(response.body());
            _complete();
        });
    }

    private void _wait() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Thread.onSpinWait();
            }
        }, 0, 1);
    }

    private void _complete() {
        timer.cancel();
        timer.purge();
    }

    private HttpRequest.Builder _bbpr(String uri, Properties headers, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(uri));
        for (Map.Entry<String, Property> set : headers.list().entrySet()) builder.header(set.getKey(), set.getValue().o().toString());
        builder.POST(HttpRequest.BodyPublishers.ofString(body));
        return builder;
    }

    private HttpRequest.Builder _bbgr(String uri) {
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(uri));
        builder.GET();
        return builder;
    }

    /**
     *
     * @return a new HttpClient
     */
    /*
    public static HttpClient get() {
        return new HttpClient(java.net.http.HttpClient.newHttpClient());
    }

    /**
     *
     * @param properties accepts: {{@code authenticator}: {@linkplain java.net.Authenticator}, {@code redirect_policy}: {@linkplain java.net.http.HttpClient.Redirect}, {@code connect_timeout}: {@linkplain java.time.Duration}, {@code cookie_handler}: {@linkplain java.net.CookieHandler}, {@code executor}: {@linkplain java.net.Authenticator}, {@code priority}: {@linkplain java.lang.Integer}, {@code proxy}: {@linkplain java.net.ProxySelector}, {@code ssl_context}: {@linkplain javax.net.ssl.SSLContext}, {@code ssl_parameters}: {@linkplain javax.net.ssl.SSLParameters}, {@code version}: {@linkplain java.net.http.HttpClient.Version}}
     * @return a new HttpClient
     */
    /*
    public static HttpClient get(Properties properties) {
        java.net.http.HttpClient.Builder builder = java.net.http.HttpClient.newBuilder();
        if (properties.has("authenticator")) builder.authenticator(properties.get("authenticator").any());
        if (properties.has("redirect_policy")) builder.followRedirects(properties.get("redirect_policy").any());
        if (properties.has("connect_timeout")) builder.connectTimeout(properties.get("connect_timeout").any());
        if (properties.has("cookie_handler")) builder.cookieHandler(properties.get("cookie_handler").any());
        if (properties.has("executor")) builder.executor(properties.get("executor").any());
        if (properties.has("priority")) builder.priority(properties.get("priority").any());
        if (properties.has("proxy")) builder.proxy(properties.get("proxy").any());
        if (properties.has("ssl_context")) builder.sslContext(properties.get("ssl_context").any());
        if (properties.has("ssl_parameters")) builder.sslParameters(properties.get("ssl_parameters").any());
        if (properties.has("version")) builder.version(properties.get("version").any());
        return new HttpClient(builder.build());
    }

     */
}
