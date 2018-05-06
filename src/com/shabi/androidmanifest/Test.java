package com.shabi.androidmanifest;

import com.shabi.androidmanifest.data.AttributeType;
import com.shabi.androidmanifest.data.NameSpaceChunk;
import com.shabi.androidmanifest.data.TagAttribute;
import com.shabi.androidmanifest.data.TagChunk;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String... args) throws IOException {
        byte[] data = Utils.getBytye(new File("AndroidManifest.xml"));
        Parser parser = new Parser(data);

        List<String> mStrings = parser.mStringChunk.mStrings;
        List<NameSpaceChunk> nameSpaceChunnks = parser.mXmlContentChunk.mNameSpaceChunnks;
        List<TagChunk> tagChunks = parser.mXmlContentChunk.mTagChunks;
        int size = tagChunks.size();
        int len = 0;
        for (int index = 0; index < size; index++) {
            String align = getAlign(len);
            TagChunk mTagChunk = tagChunks.get(index);
            switch (mTagChunk.flag) {
                case 0://结束标签
                    System.out.println(String.format("%s<%s />", getAlign(--len), mStrings.get(mTagChunk.name)));
                    break;
                case 1310740://开始标签
                    len++;
                    StringBuilder sb = new StringBuilder();
                    for (TagAttribute ta : mTagChunk.mTagAttributes) {
                        sb.append("\n")
                                .append(getAlign(1))
                                .append(align);
                        if (ta.nameSpaceUri != -1) {
                            String uri = mStrings.get(ta.nameSpaceUri);
                            for (NameSpaceChunk nameSpaceChunnk : nameSpaceChunnks) {
                                if (ta.nameSpaceUri == nameSpaceChunnk.uri) {
                                    sb.append(mStrings.get(nameSpaceChunnk.prefix))
                                            .append(":");
                                    break;
                                }
                            }
                        }
                        sb.append(mStrings.get(ta.name))
                                .append("=\"")
                                .append(ta.stringValue == -1 ? AttributeType.getAttributeData(mStrings, ta) : mStrings.get(ta.stringValue))
                                .append("\"");
                    }

                    if (index + 1 < size) {
                        TagChunk tagChunk = tagChunks.get(index + 1);
                        if (tagChunk.flag == 0 && tagChunk.name == mTagChunk.name) {
                            sb.append(" />");
                            index++;
                            len--;
                        } else {
                            sb.append(" >");
                        }
                    }
                    System.out.println(String.format("%s<%s%s", align, mStrings.get(mTagChunk.name), sb.toString()));
                    break;
            }
        }
    }

    private static String getAlign(int len) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < len; index++) {
            sb.append("\t");
        }
        return sb.toString();
    }
}