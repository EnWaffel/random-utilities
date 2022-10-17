package de.enwaffel.projectp.internal.api.impl;

import de.enwaffel.projectp.G;
import de.enwaffel.projectp.api.service.AssetProvider;
import de.enwaffel.randomutils.http.HttpStatus;
import de.enwaffel.randomutils.io.ByteBuffer;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class AssetProviderImpl implements AssetProvider {

    private static final MediaType JSON = MediaType.get("application/json");

    private final ServiceAPIImpl serviceAPI;

    protected AssetProviderImpl(ServiceAPIImpl serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @Override
    public ByteBuffer getAsset(String name) {
        try  {
            RequestBody body = RequestBody.create(new JSONObject().put("asset-name", name).toString(), JSON);
            Request request = new Request.Builder()
                    .url(G.apiServerURL)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("request-type", "get-asset")
                    .post(body)
                    .build();
            Response response = serviceAPI.getHttpClient().newCall(request).execute();
            if (response.body() == null) return ByteBuffer.from("Failed to get asset: " + name);
            if (response.code() == HttpStatus.NOT_FOUND.code()) return ByteBuffer.from("Failed to get asset: " + name);
            return new ByteBuffer(response.body().bytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ByteBuffer.from("Failed to get asset: " + name);
    }

    @Override
    public void setAsset(String location, ByteBuffer data) {

    }

}
