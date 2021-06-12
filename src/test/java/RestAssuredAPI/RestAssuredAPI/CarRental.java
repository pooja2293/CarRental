package RestAssuredAPI.RestAssuredAPI;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;



public class CarRental extends Base {


	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void toGetAuthToken() throws InterruptedException {

		JSONObject request = new JSONObject();
		request.put(prop.getProperty("name"), prop.getProperty("number"));
		request.put(prop.getProperty("pwd"), prop.getProperty("value"));
		int status =given().header("Content-Type", "application/json").body(request.toJSONString()).when().post(prop.getProperty("LoginPost")).getStatusCode();
		String code =String.valueOf(status);
		assertEquals(code, "200");
	}


	@Test(priority=2)
	public void searchCar() {

		String body=given().header("Authorization","Bearer "+one()).when().get(prop.getProperty("CarGet")).getBody().asString();
		int res=given().header("Authorization","Bearer "+one()).when().get(prop.getProperty("CarGet")).getStatusCode();
		System.out.println(body);
		assertEquals(String.valueOf(res),"200");
	}


	@Test(priority=3)
	public void carPrice() {
		String price = given().header("Authorization","Bearer "+one()).when().get(prop.getProperty("PriceCar")).getBody().asString();
		System.out.println(price);
		int price1 = given().header("Authorization","Bearer "+one()).when().get(prop.getProperty("PriceCar")).statusCode();
		assertEquals(String.valueOf(price1),"200");
	}


	@SuppressWarnings("unchecked")
	@Test(priority=4)
	public void bookCar() {
		JSONObject request1 = new JSONObject();
		request1.put("car_id", prop.getProperty("id"));
		request1.put("car_license_number", prop.getProperty("license"));
		request1.put("total_rent_bill", prop.getProperty("bill"));
		request1.put("from_date_time", prop.getProperty("from"));
		request1.put("to_date_time", prop.getProperty("to"));
		String book =given().header("Content-Type", "application/json").header("Authorization","Bearer "+one()).body(request1.toJSONString()).when().post(prop.getProperty("CarBook")).getBody().asString();
		System.out.println(book);
		int book1 =given().header("Content-Type", "application/json").header("Authorization","Bearer "+one()).body(request1.toJSONString()).when().post(prop.getProperty("CarBook")).statusCode();
		assertEquals(String.valueOf(book1),"409");

	}


	@Test(priority=5)
	public void verifyBooking() {
		String booking=given().header("Content-Type", "application/json").header("Authorization","Bearer "+one()).when().get(prop.getProperty("Verify")).getBody().asPrettyString();
		System.out.println(booking);
		int booking1=given().header("Content-Type", "application/json").header("Authorization","Bearer "+one()).when().get(prop.getProperty("Verify")).getStatusCode();
		assertEquals(String.valueOf(booking1),"200");

	}

}
