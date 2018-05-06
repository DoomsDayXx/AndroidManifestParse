package com.shabi.androidmanifest.data;

import com.shabi.androidmanifest.Utils;

public abstract class BaseData {

    byte[] mData;
    int chunkType;
    public int chunkSize;
    int lineNumber;
    int unKnown;

    public BaseData(byte[] data) {
        this.mData = data;
        chunkType = Utils.bytes2Int(Utils.copy(mData, 0, 4));
        chunkSize = Utils.bytes2Int(Utils.copy(mData, 4, 4));
        if (chunkType != ChunkType.StartNameSpaceChunk) {
            mData = Utils.copy(mData, 0, chunkSize);
        }
        switch (chunkSize) {
            case ChunkType.ResourceIdChunk:
            case ChunkType.StringChunk:
                break;
            default:
                lineNumber = Utils.bytes2Int(Utils.copy(mData, 8, 4));
                unKnown = Utils.bytes2Int(Utils.copy(mData, 12, 4));
                break;
        }
        parse();
    }

    public static interface ChunkType {
        int StringChunk = 0x1c0001;
        int ResourceIdChunk = 0x80180;
        int StartNameSpaceChunk = 0x100100;
        int EndNameSpaceChunk = 0x100101;
        int StartTagChunk = 0x100102;
        int EndTagChunk = 0x100103;
        int TextChunk = 0x100104;
    }

    abstract void parse();
}
