package com.shabi.androidmanifest.data;

import com.shabi.androidmanifest.Utils;

import java.util.ArrayList;

public class ResourceIdChunk extends BaseData {
    public ArrayList<Integer> mResourceIds;
    public ResourceIdChunk(byte[] data) {
        super(data);
    }

    @Override
    void parse() {
         mResourceIds = new ArrayList<>();
        int offset = 8;
        int size = chunkSize / 4 - 2;
        for (int index = 0; index < size; index++) {
            int id = Utils.bytes2Int(Utils.copy(mData, offset + index * 4, 4));
            mResourceIds.add(id);
        }
    }
}
