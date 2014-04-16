package util.json;

/**
 *
 * @author polygon
 */
public class JSONMember
{
    public String name;
    public JSONValue value;
    
    public JSONMember(String memberName, JSONValue memberValue)
    {
        name = memberName;
        value = memberValue;
    }
    
    @Override
    public String toString()
    {
        return toString(0);
    }
    
    public String toString(int depth)
    {
        int type = value.getType();
        String out = Formatting.nSpaces(depth) + "\"" + name + "\":";
        out += (type == JSONValue.ARRAY || type == JSONValue.OBJECT) ? "\n" + value.toString(depth) : value.toString();
        return out;
    }
}
