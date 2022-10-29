package common;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ApiUtils {

	HttpResponse<String> response;

	// Send GET request
	public HttpResponse<String> sendGetRequest(String url) {

		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET()
					.header("Content-Type", "application/json").build();
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Send get request fail");
		}
		return response;
	}

	// Send POST request
	public HttpResponse<String> sendPostRequest(String url, String jsonRequestBody) {

		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))// pass request body
					.build();

			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
			System.out.println("requestBodyString:" + jsonRequestBody);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Send get request fail");
		}
		return response;
	}

	// Send POST request w Nobody
	public HttpResponse<String> sendPostRequestNoBody(String url) {
		HttpResponse<String> response = null;
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).POST(HttpRequest.BodyPublishers.noBody())// No
																														// request
																														// body
					.build();
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Send get request fail");
		}
		return response;
	}

	// Send DELETE request
	public HttpResponse<String> sendDeleteRequest(String url) {
		HttpResponse<String> response = null;
		var request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").DELETE()
				.build();
		
		try {
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}
	
	// Send PUT request
	
	public HttpResponse<String> sendPUTRequest(String url, String jsonRequestBody) {
		HttpResponse<String> response = null;
		var request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
				.build();
		
		try {
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	
}
}
