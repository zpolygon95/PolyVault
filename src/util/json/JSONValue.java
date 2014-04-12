package util.json;

/**
 *
 * @author polygon
 */
public class JSONValue
{
    private String rawValue;
    private int type;
    
    private String stringValue;
    private double numberValue;
    private JSONObject objectValue;
    private JSONArray arrayValue;
    private boolean boolValue;
    private boolean isNull;
    
    public static final int STRING = 0;
    public static final int NUMBER = 1;
    public static final int OBJECT = 2;
    public static final int ARRAY = 3;
    public static final int BOOLEAN = 4;
    public static final int NULL = -1;
    
    public JSONValue(String value)
    {
        rawValue = value.trim();
        if (rawValue.length() < 1)
        {
            type = NULL;
            isNull = true;
        }
        switch (rawValue.charAt(0))
        {
            case '\"':  //value is a string
                if (rawValue.charAt(rawValue.length() - 1) == '\"')
                {
                    stringValue = rawValue.substring(1, rawValue.length() - 1);
                    type = STRING;
                }
                else    //invalid ending
                {
                    type = NULL;
                    isNull = true;
                }
                break;
            case '{':   //value is an object
                if (rawValue.charAt(rawValue.length() - 1) == '}')
                {
                    objectValue = JSONParser.parseObject(rawValue.substring(1, rawValue.length() - 1));
                    type = OBJECT;
                }
                else    //invalid ending
                {
                    type = NULL;
                    isNull = true;
                }
                break;
            case '[':   //value is an array
                if (rawValue.charAt(rawValue.length() - 1) == ']')
                {
                    arrayValue = JSONParser.parseArray(rawValue.substring(1, rawValue.length() - 1));
                    type = ARRAY;
                }
                else    //invalid ending
                {
                    type = NULL;
                    isNull = true;
                }
                break;
            default:    //value is either a boolean, a number, or null
                switch (rawValue)
                {
                    case "true":    //is a true boolean
                        boolValue = true;
                    case "false":   //is a false boolean
                        if (!boolValue)
                        {
                            boolValue = false;
                        }
                        type = BOOLEAN;
                        break;
                    case "null":    //is null
                            type = NULL;
                            isNull = true;
                        break;
                    default:        //is probably a number
                        try
                        {
                            double d = Double.parseDouble(rawValue);
                            numberValue = d;
                            type = NUMBER;
                        }
                        catch (NumberFormatException ex) //is not a number
                        {
                            type = NULL;
                            isNull = true;
                        }
                        break;
                }
        }
    }
    
    public int getType()
    {
        return type;
    }
    
    public String getString()
    {
        if (type == STRING)
        {
            return stringValue;
        }
        return null;
    }
    
    public double getNumber()
    {
        if (type == NUMBER)
        {
            return numberValue;
        }
        return -1;
    }
    
    public JSONObject getObject()
    {
        if (type == OBJECT)
        {
            return objectValue;
        }
        return null;
    }
    
    public JSONArray getArray()
    {
        if (type == ARRAY)
        {
            return arrayValue;
        }
        return null;
    }
    
    public boolean getBool()
    {
        if (type == BOOLEAN)
        {
            return boolValue;
        }
        return false;
    }
    
    public boolean isNull()
    {
        return isNull;
    }
    
    public void set(String newValue)
    {
        type = STRING;
        stringValue = newValue;
    }
    
    public void set(double newValue)
    {
        type = NUMBER;
        numberValue = newValue;
    }
    
    public void set(JSONObject newValue)
    {
        type = OBJECT;
        objectValue = newValue;
    }
    
    public void set(JSONArray newValue)
    {
        type = ARRAY;
        arrayValue = newValue;
    }
    
    public void set(boolean newValue)
    {
        type = BOOLEAN;
        boolValue = newValue;
    }
    
    public void set()
    {
        type = NULL;
    }
    
    @Override
    public String toString()
    {
        return toString(0);
    }
    
    public String toString(int depth)
    {
        switch (type)
        {
            case STRING:
                return nSpaces(depth) + getString();
            case NUMBER:
                return nSpaces(depth) + Double.toString(getNumber());
            case OBJECT:
                return getObject().toString(depth + 1);
            case ARRAY:
                return getArray().toString(depth + 1);
            case BOOLEAN:
                return nSpaces(depth) + (getBool() ? "true" : "false");
            case NULL:
                return nSpaces(depth) + "null";
        }
        return rawValue;
    }
    
    public String nSpaces(int n)
    {
        String out = "";
        for (int i = 0; i < n; i++)
        {
            out += " ";
        }
        return out;
    }
}
