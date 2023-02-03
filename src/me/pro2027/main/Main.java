package me.pro2027.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

	public static String[] stringToPlateArray(String str) {
		String arr[] = new String[6];
		for (int i = 0; i < 6; i++) {
			arr[i] = "";
		}
		for (int i = 0; i < str.length(); i++) {
			if (i < 6) {
				arr[i] = str.charAt(i) + "";
			}
		}

		return arr;
	}

	public static boolean sendPlateRequest(String plate[]) {
		try {

			URL url = new URL("https://sa.dor.mo.gov/mv/plates4u/available");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("authority", "sa.dor.mo.gov");
			http.setRequestProperty("sec-ch-ua",
					"\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"");
			http.setRequestProperty("sec-ch-ua-mobile", "?0");
			http.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
			http.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			http.setRequestProperty("cache-control", "no-cache");
			http.setRequestProperty("x-requested-with", "XMLHttpRequest");
			http.setRequestProperty("x-microsoftajax", "Delta=true");
			http.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
			http.setRequestProperty("accept", "*/*");
			http.setRequestProperty("origin", "https://sa.dor.mo.gov");
			http.setRequestProperty("sec-fetch-site", "same-origin");
			http.setRequestProperty("sec-fetch-mode", "cors");
			http.setRequestProperty("sec-fetch-dest", "empty");
			http.setRequestProperty("referer", "https://sa.dor.mo.gov/mv/plates4u/available");
			http.setRequestProperty("accept-language", "en-US,en;q=0.9");
			http.setRequestProperty("cookie",
					"visid_incap_1276241=Mlw3YQlGSl6NXr9oHizaMko+rGEAAAAAQUIPAAAAAAATF2+OtV+lvPngUIujMhaG; incap_ses_168_1276241=qLxQU3ntniUaAFMiVNtUAks+rGEAAAAAPLP87s/+eyJdZCVWVNty1Q==; _ga=GA1.2.1584866839.1638678092; _gid=GA1.2.1534856688.1638678092; BIGipServersdpb-asfp008.mo.gov_443_SSL=255653130.47873.0000; visid_incap_1300137=boH0plHxRAyNYdYstH2iN3U+rGEAAAAAQUIPAAAAAABI+veY0CENZG9O7/ge2AAo; incap_ses_1329_1300137=Y1nnUxkC1S27odhNtI5xEnY+rGEAAAAAH0A8EurMVMWSWv9hZG3TYg==; ASP.NET_SessionId=sgf0sdx2wmrjwcvs1czjfhvg; __AntiXsrfToken=94f420f204bb43e584566f4e460d3ad6; TS01dfb84d=01e5551b5ae49cda0c68a6dab65df4b7e841c3bc193b13032c73683353c18b054eccb8f0ab7a59103a0812794bae4c4790484331a5");

			String data = "ctl00%24ctl09=ctl00%24MainContent%24UpdatePanel1%7Cctl00%24MainContent%24btnAvailable&__LASTFOCUS=&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=WEITCTcq5pygY2VSgU9acxMOyYARZN%2Fr2VwqedCvBsHY26eQiRlJ%2FNrenaVOGmTWLIB3RiC9y8hEkJZLasnUj4fDAvCR2jW8JxSh5Mp%2BOcu8BdnaqJM1vDMi5ZO%2BAOlmazIgtAwstwFoP8jGDasXKvqyKNreVSzfta3DbbM9AdHjMqQ71svEyPClRajdtlbPH3ROvPstK2G9C7HTQbI7CNq5eqxy3whquvawtrbzPg6BHSsh2rtZMEnQT55XudtfU7nM0b5SLcMlNyB2vdYODloRaH%2FJH7LdR%2BKX%2FOIlhS00OgVvapol%2BGOYbYbMzQZxfVE3MC2LTkWWbF4EiLZATZGkGeKNy6sCRm%2B2nMh6QOQnsQIuf94%2B1S0PD8MPriZjbGO1GCbQ06Xz9t2BLTxhkwCGp8Oro5miueA%2BS3kk7PMPEyQIQNLEecKG7ELPrjiX7M0ktexrutRsSbG%2FBEAf%2FGr3ZmGEJjLa7BRQrSNrtM9eQLW%2BlJRUfbXkx4W1BSoxDW3pjt%2F%2Fo%2BXSZnrli00DyoWyePCo1QdwaIdQxLrNRaFdIaxmLPVzFYH3iTBlnRGOmgXfeagu8xrE%2Fzfpr2HyEHaG7gT1kvPCiAyN54P83gVSZrl8HlZl288t3uLdpnvDUkLwb0UhcDoAkEzpeHnLAK8%2FCO31IHOgFLxw9vL1xEP8JlqXpPqFFkc1O0fXZ83tbA8DAScYWxoo%2F%2BPM4%2BJmgTDAUAK0w%2B%2FqDHgYDZWh%2BAyzbsFoM2Ji735r%2BSw9MIkzA9nNZfsBfwPaouHbZqSn3rnggvyD88ahnzcVjTtkcIc8k1brNmz%2Fm5SCQRhAUqi%2FdZsRLAwzsYEgL7E3kncfiizegKucJmRAaNt1UdDeujDTIgyJ9DP2Xe5A9MwptKy%2F5tqIZjUNuRXVAXDDeNvEHNi4cbdVJ9nIMTS6jRExQoiwjz0yoXWpzyvSqs9doWZnYJ7VEczE2UYDO1enutLF6UbGNPxV%2FoceKvPd2VH%2B4ZXRtuhcCxGqi8bwg%2B0QhIEvUzxYSorzI63EBLLO7myR2HKvIexVh2FfSH1GxM9eneqQtViFeEx16xoCY%2FEfItqk%2BqVUZGcajYMpieNq21iT1NO06sRoYu%2FLEeY5YYe6z8VVkTTSAM8t7a%2FDDV9Ete9jyyAjmXtSjmIrb21E46AmD5XZ412se7IhkaCeALQXd5iguLRLJmex%2B4LzIYg9ERNfASLqGir3LaZ%2BmEePmjwKzlde1avgTcA7hfonbtxLBHLx%2BPw7aTc5SjPD3EYG8xDnQbpxu8VQLPIndqRsplOAwdAhhvRKQUvurv1uU%2BsjInyLXDpvek06yBzQAWBz34t78LJyuuebr5MdCuTeVvD39IZLecyxBjPwGq%2BNhENxmUFAX%2FXOcMB%2BWs7FObeTC%2F6pZi8VYLqZmz2N5aSJGwWoQjYqIVzN1hxuy9SbluFTpfXSia2IqC60XOEnpP2xgcVAm%2F355C33l34u73hHU9jHRDDBmeeVy3GHjz8V0dMfR%2FlAfH%2BzqqLVFP6CBsa1CZMeGwN2UfbUIzEWB7x6CB4fyRDak8IuwnuUAYj5jb16%2FhGow5o2fOv2IMJU0DdqCExMlBPGPF0xdkWwZJqWRBiHuIRwy6shWQ6STwXrqayImh41%2BEclP8ra0hkDFTZSlFXOLY6NRSfne4SAMqapLQRnZ%2F9verLT%2B%2BMn9KtrIeJs0c8yvbkYVRKU%2F%2B8MbioUzcKSNtt83f14FOCVEhiTHpgRkkHuhhFkWVwIZ159Vns9LTPlSX%2Bqb735KfJ0r%2FseLT0wDwyJFI0Fun9x%2BQHLG9We64KsDcbXFze5B%2BiKFtq2GhoLV2m3%2F94MyY2ki1tsnfxx8niTSnrEYpNiUdiiI0M7YA2KNiD2NZkXfm6zCfIR2MBYxo7GEL9vpvCuOG3czRFS1UK%2Fn3OJ3zJjY1%2B1aRhiMSE43mN%2By%2BY4BYcjDEmnjtnJiTLL%2FivVT%2BiqclTYfAHa0sXlNuusnGma2OzFbjUzKNQlyvsDO2VGy4jNLaQKTjHZWnmPVSLKPKPVixEvkU2OvLS19RgqTfPo3mmN%2BsovlNgrojpzuLsKH8mAD35HaEWxlOYn3Ug%2F%2BqSQEp%2Be6%2FzZTvKe%2Fy3A7Glhv83MbW9EBoBCZs%2BiItU1q%2BeHuekpbLLgvgUoy69C721qKx9U4tbS3YbjnZeIMsURHhrPb0YNlABNhnV8Lv69QkFjgFCXwHOVnwXu%2BzJaTKqVqEUQf4rhv0AkLo1CBN0fyfcOa14etsbXv9m9eIsqvrfJXK9iuBo67IqhkN3GGY6CLCr5iwRwMMHMBqQ4zQK6qEECxmTnmxioGuvywHrDzYYOWFYlV9BU0qYYSl%2BIKmM%2BzVUXmvlu1mVuF6k8MPZXTJETbAZiMtJGgUCcEkFVgID6%2BlFNCZpPOUMKoIvUVrOQvV4gR4XpAE9GRDf0tksRm%2Fsf69lJvFPlIB6%2BqFB%2FuhYTC8WG%2BafixauFH%2ByeXw8ycNBLmK8Pc0xl9eBwwKr9oJKdf0sutxk7rDIZBy4p6N44miPR9jAfA0yInyx9Qri%2FY6LHiSGsTZSiwhxIwtIYTxszTUgEapb6kPyWvW32lWYi0uL0ODI8Q%2B%2F3N8bhobMOWZWcjQQtfKUGDzty%2B4Akspi8rwZNd1KoSCnJ94HZE%2BhYSezyhk1Gmt%2FACGKI%2BvscB8qrfULZomGCe7qBV94Eb0s0t%2FtnstAli%2ByuE6Rh5pw6icpknSKZM4w09NgmXCME0ZZ7sTyAdflhfat196%2Fx1Ybrug7e3nA8%2BNCMi91S5%2FIQqrCQwmXSAEQgfk2Vuk1njfCIGXmlnaChd5RpaNVYVsaPizKKBYkNh4ZO981UkYAtHpjKbJEvpARMYNSuv7gc2P9s6W3J9Z21g8DJPJy8Zi7tr7EmNuXNVyaxXj7T0ns%2FMHHYCjs4Z0sW%2BnjLyml8CP34%2BRxasxUAfHPLunnACqTuhHdO0Y0SKWawu614raWlpfEE%2FGHpJB78E5Emrt41sKFJQhqH5OOkeLFiRq98N0hhvDwk7hH1HAoRThTrvZakFGb3Iz5cio7fCVobnJBAZfD8J7uB1U27%2BYE0gWgPYEN8qLS%2F2gOSRQR4tj1AdHhyGeC7IXlyajfsgDPSgTddMbyiYUEAp32sPVmePdV5Bz2g91OXq%2B7vylTx%2FJ2tuFuMbVrAoSvKif9QwsXqGyP2qRF7vU1Nr7XSU8%2BJCZjZyxZyYvREr%2FUp50S4MpP%2BLFbBm262gRspvWT%2FxHKTySBaRFjcR2zjrThsXK1SL6Hg%2BDICzmpOM15G25aYufQr4sDngfpc3IW0uHPNSt4R%2FV2bCw70M0FSXjhAB7LHxrBSb0rvHlOu1YAiuqmEcXf792hbSqqV8AzNv92a&__VIEWSTATEGENERATOR=885B43E6&__EVENTVALIDATION=Mf4UmLcQjIe26YiLLbGWpZGw3HGtKcwCEg6LzSgv4EMTOXwQeHwu8H0PxP0vXPWyfVnsPqTptBGSe0c4BfL2PMF8%2F6ELSHEEyV6X7i15%2BZvdOPnldnWdWO6logwREWqmf35f22L7zTgQ0leKjIhLehHZzD%2FNaq8qcE1Z9EOlr0M4yhh9MCky43qQx18Fj6vSRCrH%2B3GZtPd5EpTyCT72FxCAag7vjNhR7RmcbxVUOOamiDL4e2o94MIdDUOhZj6ga5LBSfYIZN7odkQ8ehUSVHHrBvKtmAQGu9lj3iG9pq38ZDihj45d3vq%2Bw9QsxAIgU94nVi9jXNNaOJfRBE5pQx3uZQMAW5T2%2Fl7hir4OtTARcU3pP5O4pcxFmeVzJuISY3L9RfvprzDseVcBwULSzzaDrOR5Mb%2F868zEVEXJKYZgZvKS9AxAylb8VbSKpx0OLo%2BMvK0%2BaIAgoa89S6Yzm2mmO1SDA0%2BqX%2Bf%2FvuFhbhf15o9E%2BEbIkM6HHKcu6nW1gBJGbcLp3krzVDcaLevQoVZMtGOSYY8NiUmi2BJ5Hm%2Fn4%2BXXomdq850dpX6xWzF7Tin5ntBOKqfgsoAqyPTiRay3B0ajr%2BdGJt4wJ2SUfaDUPpAyo3PKk095zYsnM5%2FDmigZtaV7iek50kNC%2BRndad1JU77KBfvf6lSz%2BpEMYjjloPrhsR2sYtjlspOU0NoEE3%2B2LgnOBPXJsvM0bMGK929P88WJnHCXeQS4nvHwQrPzhMDW7lcJCWEt4HybiuS0iEo7jucYoJQ9bSiOXbQrDODYRdZU8h%2BsX5dlv9pqm%2FIaBzbuGncfYdp7VEB%2B%2BB%2BJSQYrtyB1L2JI2nwDsjFdB%2FbhlQXzyYSbf35pF9f6Nelyt7Hc&ctl00%24MainContent%24ddlSelectPlateType=2&ctl00%24MainContent%24ddlSelectVehicleType=12&ctl00%24MainContent%24Let1="
					+ plate[0] + "&ctl00%24MainContent%24Let2=" + plate[1] + "&ctl00%24MainContent%24Let3=" + plate[2]
					+ "&ctl00%24MainContent%24Let4=" + plate[3] + "&ctl00%24MainContent%24let5=" + plate[4]
					+ "&ctl00%24MainContent%24Let6=" + plate[5]
					+ "&ctl00%24MainContent%24txtExplain=&__ASYNCPOST=true&ctl00%24MainContent%24btnAvailable=Check%20Availability";

			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));

			
			String output;
			while ((output = br.readLine()) != null) {
				
				// System.out.println(output);
				if (output.contains("MainContent_lblAvailable")) {
					http.disconnect();
					return true;
				}
				if (output.contains("MainContent_lblNotAvailable")) {
					http.disconnect();
					return false;
				}
			}

			System.out.println("ERROR RESPONSE!");
			// System.out.println(http.getResponseCode() + " " + sb.toString());
			http.disconnect();

			// return http.getResponseMessage();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isValid(String plate) {
		String arr[] = stringToPlateArray(plate);
		return sendPlateRequest(arr);

	}

	public static BufferedWriter bw = null;
	
	public static void outputPlateResponse(String plate) throws IOException {
		boolean valid = isValid(plate);
		if (valid == true) {
			bw.write(plate);
			bw.newLine();
			System.out.println(plate + ": " + valid);
		}
	}

	public static void main(String[] args) {

		
		String plate = "raven";
		System.out.println(plate + ": " + isValid(plate));
		
		/*
		try {
			FileOutputStream outputScanner = new FileOutputStream(new File("C:/Users/Tyler/Documents/plateoutput.txt"));
			Scanner s = new Scanner(new File("C:/Users/Tyler/Documents/google-10000-english.txt"));

			bw = new BufferedWriter(new OutputStreamWriter(outputScanner));
			
			
			while (s.hasNextLine()) {
				
				String str = s.nextLine();
				outputPlateResponse(str);
			}
			
			bw.close();
			
			outputScanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
