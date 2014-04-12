package util.json;

/**
 *
 * @author polygon
 */
public class JSONObject
{
    public JSONMember[] members;
    
    public JSONObject(JSONMember... objectMembers)
    {
        members = objectMembers;
    }
    
    public JSONMember get(int i)
    {
        if (i < members.length && i > -1)
        {
            return members[i];
        }
        return null;
    }
    
    public JSONValue get(String name)
    {
        for (JSONMember m : members)
        {
            if (m.name.equals(name))
            {
                return m.value;
            }
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
        String out = nSpaces(depth) + "{";
        for (JSONMember m : members)
        {
            out += m.toString(depth + 1) + "\n";
        }
        out += nSpaces(depth) + "}";
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
