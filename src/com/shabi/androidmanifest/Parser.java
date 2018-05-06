package com.shabi.androidmanifest;

import com.shabi.androidmanifest.data.ResourceIdChunk;
import com.shabi.androidmanifest.data.StringChunk;
import com.shabi.androidmanifest.data.XmlContentChunk;

public class Parser {

    int magicNumber;
    int fileSize;
    StringChunk mStringChunk;
    ResourceIdChunk mResourceIdChunk;
    XmlContentChunk mXmlContentChunk;

    Parser(byte[] data) {
        int offset = 0;
        magicNumber = Utils.bytes2Int(Utils.copy(data, 0, 4));
        fileSize = Utils.bytes2Int(Utils.copy(data, offset += 4, 4));
        mStringChunk = new StringChunk(Utils.copy(data, offset += 4, data.length - offset));
        mResourceIdChunk = new ResourceIdChunk(Utils.copy(data, offset += mStringChunk.chunkSize, data.length - offset));
        mXmlContentChunk = new XmlContentChunk(Utils.copy(data, offset += mResourceIdChunk.chunkSize, data.length - offset));
    }
}
