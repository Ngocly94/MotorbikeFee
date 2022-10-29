package common;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonUtils {

	public boolean checkErrorMessage(String responseBody, String expectedMessage) {
		String[] splittedResponseBody = responseBody.split("\"");
		boolean isChecked = false;
		for (int i = 0; i < splittedResponseBody.length; i++) {
			if (splittedResponseBody[i].equals(expectedMessage)) {
				isChecked = true;
			}
		}
		return isChecked;
	}

	// Get value from Json body by key
	public String getValueByKey(String responseBody, String key) {
		JSONParser parser = new JSONParser();
		String value = "";
		try {
			JSONObject responseBodyObj = (JSONObject) parser.parse(responseBody);
			value = responseBodyObj.get(key).toString();
		} catch (Exception e) {
			System.out.println("Response body is null.");
			e.printStackTrace();
		}
		return value;
	}

	public void copyJsonFile(File sourceFile, File destinationFile) {
		if (destinationFile.exists()) {
			destinationFile.delete();
		}
		try {
			Files.copy(sourceFile.toPath(), destinationFile.toPath());
			System.out.println("Copy successfully");
		} catch (Exception e) {
			System.out.println("Json request body is not found");
		}

	}

	// Pass value by SinglefieldName
	public String changeValueBySingleFieldName(File file, String fieldName, String value) {
		String resultFile = null;
		String regex = "/^[a-zA-Z0-9- ]*$/";
		String filePath = file.getAbsolutePath();// Lấy đường dẫn đến file
		try {
			String originalFile = new String(Files.readAllBytes(Paths.get(filePath)));// Đọc file từ đường dẫn

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(originalFile);// Chuyển file sang dạng Json Object
			if (value.equals("missing")) { // Nếu value miss -> remove fieldname khỏi json object)
				jsonObject.remove(fieldName); // Đọc value và fieldname ở feature
			} else if (value.equals("null")) {
				jsonObject.put(fieldName, null);
			} else if (value.equals("true")) {
				jsonObject.put(fieldName, true);
			} else if (value.equals("\"\"")) { // String
				jsonObject.put(fieldName, "");
			} else if (value.matches("["+ regex + "]+")) { // String
				jsonObject.put(fieldName, "!$%");
			} else {
				jsonObject.put(fieldName, value);
			}
			resultFile = jsonObject.toJSONString(); // chuyển về String

		} catch (Exception e) {
			System.out.println("File not found");
		}

		return resultFile;

	}

	// ChangeValueTwoFields
	public String changeValueOfMultiField(HashMap<String, String> fieldNameValues, File jsonFile) {
		String resultFile = null;
		String filePath = jsonFile.getAbsolutePath();// Lấy đường dẫn đến file
		try {
			String originalFile = new String(Files.readAllBytes(Paths.get(filePath)));// Đọc file từ đường dẫn
            
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(originalFile);
			
			for (String i : fieldNameValues.keySet()) {
				if (fieldNameValues.get(i).equals("missing")) { // Nếu value miss -> remove fieldname khỏi json object)
					jsonObject.remove(i); // Đọc value và fieldname ở feature
				} else if (fieldNameValues.get(i).equals("null")) {
					jsonObject.put(i, null);
				} else if (fieldNameValues.get(i).equals("true")) {
					jsonObject.put(i, true);
				} else if (fieldNameValues.get(i).equals("\"\"")) { // String
					jsonObject.put(i, "");
				} else {
					jsonObject.put(i, fieldNameValues.get(i));
				}

			}
			resultFile = jsonObject.toString();
		} catch (Exception e) {
			System.out.println("Set value by key fail");
		}

		return resultFile;

	}

}
