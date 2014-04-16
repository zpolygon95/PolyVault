package util.json;

/**
 *
 * @author polygon
 */
public class JSONArray
{
    public JSONValue[] values;
    
    public JSONArray(JSONValue... vals)
    {
        values = vals;
    }
    
    public JSONValue get(int i)
    {
        if (i < values.length && i >= 0)
        {
            return values[i];
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        return toString(0);
    }
    
    public String toString(int depth)
    {
        String out = "";
        for (JSONValue v : values)
        {
            out += v.toString(depth) + ",\n";
        }
        out = out.substring(0, out.length() - 2) + "\n";
        return out;
    }
}
