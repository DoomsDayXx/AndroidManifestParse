package com.shabi.androidmanifest.data;

import com.shabi.androidmanifest.Utils;

import java.util.ArrayList;

public class TagChunk extends BaseData {

    public ArrayList<TagAttribute> mTagAttributes;
    public int uri;
    public int name;
    public int flag;
    public int attributeCount;
    public int classAttribute;

    public TagChunk(byte[] data) {
        super(data);
    }

    @Override
    void parse() {
        int offset = 12;
        uri = Utils.bytes2Int(Utils.copy(mData, offset += 4, 4));
        name = Utils.bytes2Int(Utils.copy(mData, offset += 4, 4));

        if (chunkType == ChunkType.EndTagChunk) {
            return;
        }

        //结束标签还是开始标签
        flag = Utils.bytes2Int(Utils.copy(mData, offset += 4, 4));
        attributeCount = Utils.bytes2Int(Utils.copy(mData, offset += 4, 4));
        //标签包含的类属性
        classAttribute = Utils.bytes2Int(Utils.copy(mData, offset += 4, 4));
        offset += 4;
        mTagAttributes = new ArrayList<>();
        for (int index = 0; index < attributeCount; index++) {
            byte[] data = Utils.copy(mData, offset + index * 20, 20);
            TagAttribute tagAttribute = new TagAttribute();
            mTagAttributes.add(tagAttribute);
            tagAttribute.nameSpaceUri = Utils.bytes2Int(Utils.copy(data, 0, 4));
            tagAttribute.name = Utils.bytes2Int(Utils.copy(data, 4, 4));
            tagAttribute.stringValue = Utils.bytes2Int(Utils.copy(data, 8, 4));
            tagAttribute.type = Utils.bytes2Int(Utils.copy(data, 12, 4)) >> 24;
            tagAttribute.data = Utils.bytes2Int(Utils.copy(data, 16, 4));
        }
    }
}
