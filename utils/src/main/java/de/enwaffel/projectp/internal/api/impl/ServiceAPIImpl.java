package de.enwaffel.projectp.internal.api.impl;

import de.enwaffel.projectp.api.service.AssetProvider;
import de.enwaffel.projectp.api.service.ServiceAPI;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.Validate;

public class ServiceAPIImpl implements ServiceAPI {

    private static boolean initialized;


    private final AssetProvider assetProvider;
    private final OkHttpClient httpClient;

    public ServiceAPIImpl() {
        Validate.isTrue(!initialized, "already initialized!");

        assetProvider = new AssetProviderImpl(this);
        httpClient = new OkHttpClient();

        initialized = true;
    }

    @Override
    public AssetProvider getAssetProvider() {
        return assetProvider;
    }

    protected OkHttpClient getHttpClient() {
        return httpClient;
    }

}
