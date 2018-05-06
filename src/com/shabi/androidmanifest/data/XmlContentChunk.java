package com.shabi.androidmanifest.data;

import com.shabi.androidmanifest.Utils;

import java.util.ArrayList;
import java.util.List;

public class XmlContentChunk extends BaseData {

    public List<NameSpaceChunk> mNameSpaceChunnks;
    public List<TagChunk> mTagChunks;

    public XmlContentChunk(byte[] data) {
        super(data);
    }

    @Override
    void parse() {
        mTagChunks = new ArrayList<>();
        int offset = 0;
        mNameSpaceChunnks = new ArrayList<>();
        A:
        while (offset < mData.length) {
            byte[] data = Utils.copy(mData, offset, mData.length - offset);
            switch (Utils.getChunkType(data)) {
                case ChunkType.EndTagChunk:
                case ChunkType.StartTagChunk:
                    TagChunk tagChunk = new TagChunk(data);
                    mTagChunks.add(tagChunk);
                    offset += tagChunk.chunkSize;
                    break;
                case ChunkType.StartNameSpaceChunk:
                case ChunkType.EndNameSpaceChunk:
                    NameSpaceChunk nameSpaceChunk = new NameSpaceChunk(data);
                    offset += nameSpaceChunk.chunkSize;
                    mNameSpaceChunnks.add(nameSpaceChunk);
                    break;
                case ChunkType.TextChunk:
                    System.out.println("text chunk");
                    break;
                default:
                    break A;
            }
        }
    }
}
