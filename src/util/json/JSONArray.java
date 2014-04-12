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
        String out = nSpaces(depth) + "[";
        for (JSONValue v : values)
        {
            out += v.toString(depth + 1) + "\n";
        }
        out += nSpaces(depth) + "]";
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
