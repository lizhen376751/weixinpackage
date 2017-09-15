package service.thirdservice.aes;

import java.util.ArrayList;

/**
 *
 */
class ByteGroup {
    /**
     *
     */
    private ArrayList<Byte> byteContainer = new ArrayList<Byte>();

    /**
     * @return ds
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    /**
     * @param bytes ds
     * @return sdsa
     */
    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }

    /**
     * @return 数字
     */
    public int size() {
        return byteContainer.size();
    }
}
