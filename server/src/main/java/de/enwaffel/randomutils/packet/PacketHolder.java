package de.enwaffel.randomutils.packet;

import java.util.HashMap;

public abstract class PacketHolder {

    private final HashMap<String, PacketType<?>> types = new HashMap<>();

    public void addType(String t, PacketType<?> type) {
        types.put(t, type);
    }

    public void removeType(String t) {
        types.remove(t);
    }

    public PacketType<?> getType(String t) {
        return types.containsKey(t) ? types.get(t) : types.values().toArray(new PacketType<?>[]{})[0];
    }

    public HashMap<String, PacketType<?>> getTypes() {
        return types;
    }

}
