package com.shabi.androidmanifest.data;

import com.shabi.androidmanifest.Utils;

public class NameSpaceChunk extends BaseData {

    public int prefix;//命名空间前缀
    public int uri;

    public NameSpaceChunk(byte[] data) {
        super(data);
    }

    @Override
    void parse() {
        prefix = Utils.bytes2Int(Utils.copy(mData, 16, 4));
        uri = Utils.bytes2Int(Utils.copy(mData, 20, 4));
    }
}
