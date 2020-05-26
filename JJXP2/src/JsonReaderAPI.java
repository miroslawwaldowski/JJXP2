import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class JsonReaderAPI {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "https://api.exchangeratesapi.io/latest";
	private static String[] currencyArray = {"CAD","HKD","ISK","PHP","DKK","HUF","CZK","AUD","RON","SEK","IDR","INR","BRL","RUB","HRK","JPY","THB","CHF","SGD","PLN","BGN","TRY","CNY","NOK","NZD","ZAR","USD","MXN","ILS","GBP","KRW","MYR"};


	public static void main(String[] args) throws IOException {
				
		String jsonString = GET();		
		JSONObject jo = new JSONObject(jsonString);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj kod waluty z wymienionych");
		System.out.println("CAD,HKD,ISK,PHP,DKK,HUF,CZK,AUD,RON,");
		System.out.println("SEK,IDR,INR,BRL,RUB,HRK,JPY,THB,CHF,");
		System.out.println("SGD,PLN,BGN,TRY,CNY,NOK,NZD,ZAR,USD,");
		System.out.println("MXN,ILS,GBP,KRW,MYR: ");
		
		String currency = scan.nextLine().toUpperCase();
		
		if(stringCheck(currency)) {
			double rate = jo.getJSONObject("rates").getDouble(currency);
			System.out.println("Dzisiejszy kurs EUR/"+ currency +" to " + rate);
			
		}else {
			System.out.println("nieprawid³owy kod waluty");
		}

	}

	private static String GET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		} else {
			return "GET request not worked";
		}
	}
	
	private static Boolean stringCheck(String str) {
		
		Boolean check = false;
		for(int i = 0; i < currencyArray.length; i++)
		{
		    if(currencyArray[i].equals(str))
		    {
		    	check = true;
		        break;
		    }
		}
				
	return check;
	}	

}