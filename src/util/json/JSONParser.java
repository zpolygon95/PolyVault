package util.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author polygon
 */
public class JSONParser
{

    public static String cleanString(String in)
    {
        int commentBeginIndex, commentEndIndex;
        String out = in;
        
        while ((commentBeginIndex = out.indexOf("/*")) > -1)
        {
            if ((commentEndIndex = out.indexOf("*/", commentBeginIndex)) > -1)
            {
                out = out.substring(0, commentBeginIndex) + out.substring(commentEndIndex + 2);
            }
            else
            {
                out = out.substring(0, commentBeginIndex);
            }
        }
        
        while ((commentBeginIndex = out.indexOf("//")) > -1)
        {
            if ((commentEndIndex = out.indexOf("\n", commentBeginIndex)) > -1)
            {
                out = out.substring(0, commentBeginIndex) + out.substring(commentEndIndex);
            }
            else
            {
                out = out.substring(0, commentBeginIndex);
            }
        }
        
        return out;
    }
    
    public static String[] appendArray(String[] first, String last)
    {
        String[] out = new String[first.length + 1];
        System.arraycopy(first, 0, out, 0, first.length);
        out[first.length] = last;
        return out;
    }
    
    public static String[] splitCollection(String in)
    {
        int numObjects = 0;
        int numArrays = 0;
        int beginIndex = 0;
        boolean inString = false;
        String[] out = new String[0];
        for (int i = 0; i < in.length(); i++)
        {
            switch (in.charAt(i))
            {
                case '{':
                    if (!inString)
                    {
                        numObjects++;
                    }
                    break;
                case '}':
                    if (!inString)
                    {
                        if (numObjects > 0)
                        {
                            numObjects--;
                        }
                        else
                        {
                            throw new IllegalArgumentException("orphan } at " + i);
                        }
                    }
                    break;
                case '[':
                    if (!inString)
                    {
                        numArrays++;
                    }
                    break;
                case ']':
                    if (!inString)
                    {
                        if (numArrays > 0)
                        {
                            numArrays--;
                        }
                        else
                        {
                            throw new IllegalArgumentException("orphan ] at " + i);
                        }
                    }
                    break;
                case '\"':
                    if (inString)
                    {
                        if (in.charAt(i-1) != '\\')
                        {
                            inString = false;
                        }
                    }
                    else
                    {
                        inString = true;
                    }
                    break;
                case ',':
                    if (!inString && numObjects == 0 && numArrays == 0)
                    {
                        out = appendArray(out, in.substring(beginIndex, i));
                        beginIndex = i + 1;
                    }
                    break;
            }
        }
        out = appendArray(out, in.substring(beginIndex));
        for (String s : out)
        {
            s = s.trim();
        }
        return out;
    }
    
    public static JSONMember parseMember(String in)
    {
        int seperator = in.indexOf(":");
        if (in.length() > 3 && seperator > 0 && seperator < in.length() - 1)
        {
            String name = in.substring(0, seperator).trim();
            String value = in.substring(seperator + 1, in.length()).trim();
            int endNameChar = name.length() - 1;
            if ((name.charAt(0) == '\"') && (name.charAt(endNameChar) == '\"'))
            {   //ensure validity of name
                String parsedName = name.substring(1, name.length() - 1);
                JSONValue parsedValue = new JSONValue(value);
                return new JSONMember(parsedName, parsedValue);
            }
        }   //every member must contain one seperator, a name, and a value
        return null;
    }
    
    public static JSONObject parseObject(String in)
    {
        String[] members = splitCollection(in);
        for (String s : members)
        {
            s = s.trim();
        }
        JSONMember[] parsedMembers = new JSONMember[members.length];
        for (int i = 0; i < members.length; i++)
        {
            parsedMembers[i] = parseMember(members[i]);
        }
        return new JSONObject(parsedMembers);
    }

    public static JSONArray parseArray(String in)
    {
        String[] values = splitCollection(in);
        for (String s : values)
        {
            s = s.trim();
        }
        JSONValue[] parsedValues = new JSONValue[values.length];
        for (int i = 0; i < values.length; i++)
        {
            parsedValues[i] = new JSONValue(values[i]);
        }
        return new JSONArray(parsedValues);
    }
    
    public static JSONObject parseFile(File f) throws FileNotFoundException, IOException
    {
        BufferedReader r = new BufferedReader(new FileReader(f));
        String text = "";
        while (r.ready())
        {
            text += r.readLine();
        }
        return parseObject(text);
    }
    
    public static boolean writeFile(JSONObject contents, File location)
    {
        return false;
    }
}
