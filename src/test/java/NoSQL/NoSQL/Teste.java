package NoSQL.NoSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class CommonJSONParser {

	public Map<String, Object> parse(String jsonStr) {

		Map<String, Object> result = null;

		if (null != jsonStr) {
			try {

				JSONObject jsonObject = new JSONObject(jsonStr);
				result = parseJSONObject(jsonObject);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // if (null != jsonStr)

		return result;
	}

	public Object parseValue(Object inputObject) throws JSONException {
		Object outputObject = null;

		if (null != inputObject) {

			if (inputObject instanceof JSONArray) {
				outputObject = parseJSONArray((JSONArray) inputObject);
			} else if (inputObject instanceof JSONObject) {
				outputObject = parseJSONObject((JSONObject) inputObject);
			} else if (inputObject instanceof String || inputObject instanceof Boolean
					|| inputObject instanceof Integer) {
				outputObject = inputObject;
			}

		}

		return outputObject;
	}

	public List<Object> parseJSONArray(JSONArray jsonArray) throws JSONException {

		List<Object> valueList = null;

		if (null != jsonArray) {
			valueList = new ArrayList<Object>();

			for (int i = 0; i < jsonArray.length(); i++) {
				Object itemObject = jsonArray.get(i);
				if (null != itemObject) {
					valueList.add(parseValue(itemObject));
				}
			} // for (int i = 0; i <jsonArray.length(); i++)
		} // if (null != valueStr)

		return valueList;
	}

	public Map<String, Object> parseJSONObject(JSONObject jsonObject) throws JSONException {

		Map<String, Object> valueObject = null;
		if (null != jsonObject) {
			valueObject = new HashMap<String, Object>();

			Iterator<String> keyIter = jsonObject.keys();
			while (keyIter.hasNext()) {
				String keyStr = keyIter.next();
				Object itemObject = jsonObject.opt(keyStr);
				if (null != itemObject) {
					valueObject.put(keyStr, parseValue(itemObject));
				} // if (null != itemValueStr)

			} // while (keyIter.hasNext())
		} // if (null != valueStr)

		return valueObject;
	}
}

public class Teste {

	@SuppressWarnings("unchecked")
	public static void parse(Map<String, Object> parse, String chave, int id) {
		
		for (Entry<String, Object> entry : parse.entrySet()) {
			
			if (entry.getValue() instanceof HashMap) {
				//System.out.println(entry.getKey());
				//chave = chave + "." + entry.getKey();
				System.out.println("Antes de chamar func: " + entry.getKey() + " id:" + id);
				int novo = id + 1;
				parse((Map<String, Object>)entry.getValue(), chave, novo);
				System.out.println("Depois: " + entry.getKey() + " id:" + id);
			} else {
				
				
				StringBuilder builder = new StringBuilder();
				
//				if (chave.trim().length() > 0) {
//					builder.append(chave);
//					builder.append(".");
//				}
				
				builder.append(entry.getKey().toString());
				
				System.out.println(builder.toString());
				//System.out.println(entry.getValue().getClass());
			}
			
		}
		
		
	}
	
	static boolean acabou = false;

	public static void main(String[] args) {

		String jsonStr = "{\r\n" + 
				"	\"nome\":\"Thiago\",\r\n" + 
				"	\"idade\": 25,\r\n" + 
				"	\"endereco\": {\r\n" + 
				"		\"rua\":\"av brasil\",\r\n" + 
				"		\"numero\":1375,\r\n" + 
				"		\"trabalho\": {\r\n" + 
				"			\"profissao\":\"programador\"\r\n" + 
				"		}\r\n" + 
				"	},\r\n" + 
				"\r\n" + 
				"	\"familia\": {\r\n" + 
				"		\"esposa\":\"Tatiane\",\r\n" + 
				"		\"Filha\": \"isabella\"\r\n" + 
				"	}\r\n" + 
				"}";

		// JSONObject jobject = new JSONObject(jsonStr);

		Map<String, Object> parse = new CommonJSONParser().parse(jsonStr);
		
		parse(parse, "", 0);

	}

}
