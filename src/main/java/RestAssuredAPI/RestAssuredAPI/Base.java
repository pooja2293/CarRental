package RestAssuredAPI.RestAssuredAPI;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;

public class Base {

	public static Properties prop;

	public Base()  {


		prop = new Properties();
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("user.dir: " + currentDirectory);

		FileInputStream file;
		try {
			file = new FileInputStream(currentDirectory+"\\src\\main\\java\\config\\File.properties");
			try {
				prop.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//	prop.load(getClass().class.getResourceAsStream("/File.properties"));

	}


	@SuppressWarnings("unchecked")
	public static String one() {
		JSONObject request = new JSONObject();
		request.put(prop.getProperty("name"), prop.getProperty("number"));
		request.put(prop.getProperty("pwd"), prop.getProperty("value"));
		String abc =given().header("Content-Type", "application/json").body(request.toJSONString()).when().post(prop.getProperty("LoginPost")).getBody().asString();
		String arr=abc.substring(69);
		int ar = arr.indexOf("}");
		int first=ar+69;
		final String token = abc.substring(69, first-1);
		return token;
	}

}
