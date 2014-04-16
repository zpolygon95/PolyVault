package test;

import util.json.JSONArray;
import util.json.JSONMember;
import util.json.JSONObject;
import util.json.JSONValue;

/**
 *
 * @author polygon
 */
public class test
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JSONValue[] vals = new JSONValue[10];
        for (int i = 0; i < 10; i++)
        {
            vals[i] = new JSONValue(i);
        }
        JSONArray array = new JSONArray(vals);
        JSONValue value = new JSONValue(array);
        JSONMember member = new JSONMember("array", value);
        JSONMember member1 = new JSONMember("other array", value);
        JSONObject object = new JSONObject(member, member1);
        JSONValue finalValue = new JSONValue(object);
        System.out.println(finalValue.toString());
    }
}
