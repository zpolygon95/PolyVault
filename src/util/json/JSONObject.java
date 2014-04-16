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
        String out = "";
        for (JSONMember m : members)
        {
            out += m.toString(depth) + ",\n";
        }
        out = out.substring(0, out.length() - 2) + "\n";
        return out;
    }
}
