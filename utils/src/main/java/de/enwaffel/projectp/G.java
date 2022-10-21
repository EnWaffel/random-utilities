package de.enwaffel.projectp;

import java.util.UUID;

public class G {

    protected static final UUID globalKey = UUID.randomUUID();
    public static final String apiServerURL = "https://assets.bierfrust.de";
    protected static final String[] apis = new String[]{
            "service::de.enwaffel.projectp.api.service.ServiceAPI",
            "gc::de.enwaffel.projectp.api.guardiencraft.GCServiceAPI"
    };

}
