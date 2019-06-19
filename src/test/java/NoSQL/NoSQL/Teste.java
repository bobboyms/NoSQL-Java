package NoSQL.NoSQL;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.wnameless.json.flattener.JsonFlattener;

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
	
	static StringBuilder builder = new StringBuilder();

	@SuppressWarnings("unchecked")
	public static int parse(Map<String, Object> parse) {
				
		for (Entry<String, Object> entry : parse.entrySet()) {
			
			if (entry.getValue() instanceof HashMap) {
				//System.out.println(entry.getKey());
//				chave = chave + "." + entry.getKey();
				System.out.println(entry.getKey());
				parse((Map<String, Object>)entry.getValue());

			} else {
				
				
//				StringBuilder builder = new StringBuilder();
//				
//				if (chave.trim().length() > 0) {
//					builder.append(chave);
//					builder.append(".");
//				}
				System.out.println(entry.getKey());

				
//				System.out.println(builder.toString());
				//System.out.println(entry.getValue().getClass());
			}
			
		}
		
		return 0;
		
	}
	
	static boolean acabou = false;
	// https://github.com/wnameless/json-flattener
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

		Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(jsonStr);
		
		for (Entry<String, Object> entry : flattenJson.entrySet()) {
			System.out.println(entry.getKey());
		}
		
		System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
		
		//System.out.println(flattenJson);
		
		

	}

}
