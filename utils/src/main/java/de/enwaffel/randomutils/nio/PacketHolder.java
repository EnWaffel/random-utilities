package de.enwaffel.randomutils.nio;

import de.enwaffel.randomutils.io.ByteBuffer;

import java.util.ArrayList;
import java.util.List;

public class PacketHolder {

    protected final List<PacketReader<Packet<?>>> readers = new ArrayList<>();

    public void addReader(PacketReader<Packet<?>> reader) {
        readers.add(reader);
    }

    protected void readPacket(ByteBuffer buffer) {
        String str = new String(buffer.getBuffer());
        int[] data = _getData(str); if (data[0] == -1) throw new RuntimeException(new IllegalArgumentException("Invalid or unregistered channel: " + data[0]));
        String str1 = str.substring(data[1]);

        for (int i = 0;i < readers.size();i++) {
            PacketReader<Packet<?>> reader = readers.get(i);
            if (reader != null) {
                if (reader.getType().getClass().getSimpleName().equals("")) {

                }
            }
        }
    }

    private int[] _getData(String str) {
        StringBuilder _str = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c ==  '-') {
                return new int[]{Integer.parseInt(_str.toString()), _str.length()};
            } else {
                _str.append(c);
            }
        }
        return new int[]{-1, 0};
    }

}
