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
        String out = "\"" + name + "\":";
        out += (value.getType() == JSONValue.OBJECT || value.getType() == JSONValue.ARRAY) ? "\n" : "";
        out += value.toString();
        return out;
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
