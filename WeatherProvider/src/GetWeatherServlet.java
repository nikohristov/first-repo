

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/GetWeatherServlet")
public class GetWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String city = request.getParameter("cityName");
		city = city.replace(" ", "-");
		String myIpKey = "cddba3c9eb22e2ec544b8eb4eda3e5ab";
		HttpClient client = HttpClientBuilder.create().build();
		HttpUriRequest zaqvka = new HttpGet("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID="+myIpKey);
		HttpResponse otgovor = client.execute(zaqvka);
		String data = EntityUtils.toString(otgovor.getEntity());
		System.out.println(data);
		
		JsonObject obj = (JsonObject) new JsonParser().parse(data);
		
		String name = obj.get("name").getAsString();
		int id = obj.get("id").getAsInt();
		JsonObject objClouds = obj.get("clouds").getAsJsonObject();
		int brObl = objClouds.get("all").getAsInt();
		double tempr = obj.get("main").getAsJsonObject().get("temp").getAsInt();
		tempr = tempr - 273.15;
		
		JsonArray array = obj.get("weather").getAsJsonArray();
		String statusWeather = array.get(0).getAsJsonObject().get("description").getAsString();
		
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		request.setAttribute("desc", statusWeather);
		request.setAttribute("temp", tempr);
		request.setAttribute("cloud", brObl);
		request.getRequestDispatcher("show.jsp").forward(request, response);
		
		
	}
}
