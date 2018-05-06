package com.shabi.androidmanifest.data;

import com.shabi.androidmanifest.Utils;

import java.util.ArrayList;
import java.util.List;

public class StringChunk extends BaseData {

    public List<String> mStrings;
    int mStringCount;
    private int mStyleCoutn;
    private int mStringPoolOffsets;
    private int mStylePoolOffsets;

    public StringChunk(byte[] data) {
        super(data);
    }

    @Override
    void parse() {
        mStrings = new ArrayList<>();
        mStringCount = Utils.bytes2Int(Utils.copy(mData, 8, 4));
        mStyleCoutn = Utils.bytes2Int(Utils.copy(mData, 12, 4));
        unKnown = Utils.bytes2Int(Utils.copy(mData, 16, 4));
        //字符池相对整个文件的偏移
        mStringPoolOffsets = Utils.bytes2Int(Utils.copy(mData, 20, 4));
        mStylePoolOffsets = Utils.bytes2Int(Utils.copy(mData, 24, 4));
        int start = 28;
        for (int index = 0; index < mStringCount; index++) {
            int strStart = mStringPoolOffsets + Utils.bytes2Int(Utils.copy(mData, start + index * 4, 4));
            int len = 0;
            if (unKnown == 0x100) {
                len = Utils.copy(mData, strStart, 1)[0];
            } else if (unKnown == 0) {
                len = Utils.bytes2Int(Utils.copy(mData, strStart, 2)) * 2;
            }
            byte[] copy = Utils.copy(mData, strStart + 2, len);
            mStrings.add(Utils.filtterString(copy));
        }
    }
}
