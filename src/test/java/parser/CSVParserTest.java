/*
package parser;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class CSVParserTest {

	private static CSVParser csvp = new CSVParser();

	private static String fileName;
	private static URL url_CSV;

	@BeforeClass
	public static void beforeClass() throws MalformedURLException {
		fileName = "src/dogs.csv";
		url_CSV = new URL("https://data.cityofnewyork.us/api/views/7yq2-hq9c/rows.csv");
		//http://winterolympicsmedals.com/medals.csv
	}

	@Test
	public void get_content_for_local_CSV() throws IOException {
		List<String> contentGet = csvp.readFileLocal(fileName);

		List<String> content = new ArrayList<>();
		content.add("Id,Prénom,Couleur,Age");
		content.add("1,Titi,Jaune,5");
		content.add("2,Médor,Noir,10");
		content.add("3,Pitié,Noir,5");
		content.add("4,Juju,Gris,5");
		content.add("5,Vanille,Blanc,7");
		content.add("6,Chocolat,Marron,12");
		content.add("7,Milou,Blanc,3");
		content.add("8,Idefix,Blanc,14");
		content.add("9,Pluto,Jaune,17");
		content.add("10,Dingo,Roux,1");

		int numberLines = 11;

		assertEquals(contentGet.size(), numberLines);
		assertEquals(contentGet, content);
	}

	@Test
	public void get_content_for_online_CSV() throws IOException {
		List contentGet = csvp.readFileURL(url_CSV);

		List<String> content = new ArrayList<>();
		content.add("ProviderType,DFTA ID,ProgramName,SponsorName,ProgramAddress,ProgramCity,ProgramState,Postcode,Borough,ProgramPhone,DFTA Funded,MonHourOpen,MonHourClose,TueHourOpen,TueHourClose,WedHourOpen,WedHourClose,ThuHourOpen,ThuHourClose,FriHourOpen,FriHourClose,SatHourOpen,SatHourClose,SunHourOpen,SunHourClose,Latitude,Longitude,Community Board,Council District,Census Tract,BIN,BBL,NTA,Location1");
		content.add("GERIATRIC MENTAL HEALTH SERVICES CONTRACTS,5G301,WEILL CORNELL MENTAL HEALTH,WEILL MEDICAL COLLEGE OF CORNELL UNIVERSITY,478 1st Street,NEW YORK,NY,10010,MANHATTAN,914-997-4333,Y,9 :00,5 :00,9 :00,5 :00,9 :00,5 :00,9 :00,5 :00,9 :00,5 :00,00:00,00:00,00:00,00:00,40.671232,-73.976579,306,39,155,3025180,3010760019,Park Slope-Gowanus,\"(40.671232, -73.976579)\"");
		content.add("GERIATRIC MENTAL HEALTH SERVICES CONTRACTS,3G101,SPOP MENTAL HEALTH,SERVICE PROGRAM FOR OLDER PEOPLE INC,302 WEST 91ST STREET,NEW YORK,NY,10024,MANHATTAN,212-787-7120,Y,09:00,05:00,09:00,05:00,09:00,05:00,09:00,05:00,09:00,05:00,00:00,00:00,00:00,00:00,40.792171,-73.975796,107,6,179,1034085,1012510022,Upper West Side,\"(40.792171, -73.975796)\"");
		content.add("GERIATRIC MENTAL HEALTH SERVICES CONTRACTS,4G801,SAMUEL FIELD MENTAL HEALTH,SAMUEL FIELD YM & YWHA INC,58-20 Little Neck Parkway,FLUSHING,NY,11362,QUEENS,718-225-6750,Y,8 :00,4 :00,8 :00,4 :00,8 :00,4 :00,8 :00,4 :00,8 :00,4 :00,00:00,00:00,00:00,00:00,40.761577,-73.723122,411,23,152902,4173029,4083520051,Douglas Manor-Douglaston-Little Neck,\"(40.761577, -73.723122)\"");
		content.add("GERIATRIC MENTAL HEALTH SERVICES CONTRACTS,1G701,JASA MENTAL HEALTH,THE JEWISH ASSOCIATION FOR SERVICES FOR THE AGED,1 Fordham Plaza,BRONX,NY,10458,BRONX,718-365-4044,Y,09:00,5 :00,09:00,5 :00,09:00,5 :00,09:00,5 :00,09:00,5 :00,00:00,00:00,00:00,00:00,40.860739,-73.889585,206,15,387,2088325,2030330053,Belmont,\"(40.860739, -73.889585)\"");

		int numberLines = 5;

		assertEquals(contentGet.size(), numberLines);
		assertEquals(contentGet, content);
	}

}
*/
