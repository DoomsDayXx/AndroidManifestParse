package com.shabi.androidmanifest.data;

import java.util.List;

public class AttributeType {
    public final static int ATTR_NULL = 0;
    public final static int ATTR_REFERENCE = 1;
    public final static int ATTR_ATTRIBUTE = 2;
    public final static int ATTR_STRING = 3;
    public final static int ATTR_FLOAT = 4;
    public final static int ATTR_DIMENSION = 5;
    public final static int ATTR_FRACTION = 6;
    public final static int ATTR_FIRSTINT = 16;
    public final static int ATTR_HEX = 17;
    public final static int ATTR_BOOLEAN = 18;
    public final static int ATTR_FIRSTCOLOR = 28;
    public final static int ATTR_RGB8 = 29;
    public final static int ATTR_ARGB4 = 30;
    public final static int ATTR_RGB4 = 31;

    public static String getAttributeData(List<String> strs, TagAttribute tagAttribute) {
        switch (tagAttribute.type) {
            case ATTR_NULL:
                return null;
            case ATTR_REFERENCE:
                return "@ref/0x" + Integer.toHexString(tagAttribute.data);
            case ATTR_ATTRIBUTE:
                return null;
            case ATTR_STRING:
                String s = strs.get(tagAttribute.data);
                return s;
            case ATTR_FLOAT:
                return Float.parseFloat(tagAttribute.data + "") + "";
            case ATTR_DIMENSION:
                return null;
            case ATTR_FRACTION:
                return null;
            case ATTR_FIRSTINT:
                return tagAttribute.data + "";
            case ATTR_HEX:
                return "0x" + Integer.toHexString(tagAttribute.data);
            case ATTR_BOOLEAN:
                return (tagAttribute.data == -1) + "";
            case ATTR_FIRSTCOLOR:
                return String.format("#%s", Integer.toHexString(tagAttribute.data));
            case ATTR_RGB8:
                return "#" + Integer.toHexString(tagAttribute.data);
            case ATTR_ARGB4:
                return null;
            case ATTR_RGB4:
                return null;
        }
        return tagAttribute.data + "";
    }
}
