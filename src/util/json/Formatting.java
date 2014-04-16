package util.json;

/**
 *
 * @author polygon
 */
public class Formatting
{
    public static String nSpaces(int n)
    {
        String out = "";
        for (int i = 0; i < n; i++)
        {
            out += "\t";
        }
        return out;
    }
}
