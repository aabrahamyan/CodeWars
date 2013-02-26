package com.skybot.connection.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;

import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.skybot.util.CookieStorage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * @author aabraham
 * 
 */
public class HttpConnection implements Runnable {

	public static final int DID_START = 0;
	public static final int DID_ERROR = 1;
	public static final int DID_SUCCEED = 2;

	private static final int GET = 0;
	private static final int POST = 1;
	private static final int PUT = 2;
	private static final int DELETE = 3;
	private static final int BITMAP = 4;

	private String cookieString = "";
	private String url;
	private int method;
	private Handler handler;
	private String data;
	private List<NameValuePair> nameValuePairs;

	private HttpClient httpClient;

	public HttpConnection() {
		this(new Handler());
	}

	public HttpConnection(Handler _handler) {
		handler = _handler;
	}

	public void create(int method, String url, String data,
			final List<NameValuePair> nameValuePairs) {
		this.method = method;
		this.url = url;
		this.data = data;
		this.nameValuePairs = nameValuePairs;
		ConnectionManager.getInstance().push(this);
	}

	public void get(String url, String data) {
		create(GET, url, data, null);
	}

	public void post(String url, final List<NameValuePair> nameValuePairs) {
		create(POST, url, data, nameValuePairs);
	}

	public void put(String url, String data) {
		create(PUT, url, data, null);
	}

	public void delete(String url) {
		create(DELETE, url, null, null);
	}

	public void bitmap(String url) {
		create(BITMAP, url, null, null);
	}

	@Override
	public void run() {
		handler.sendMessage(Message.obtain(handler, HttpConnection.DID_START));

		httpClient = new DefaultHttpClient();
		CookieStore cookieStore = new BasicCookieStore();

		if (CookieStorage.getInstance().getArrayList() != null
				&& !CookieStorage.getInstance().getArrayList().isEmpty()) {
			this.cookieString = (String) CookieStorage.getInstance()
					.getArrayList().get(0);
		}

		try {
			HttpResponse response = null;
			switch (method) {
			case GET:
				HttpGet httpGet = new HttpGet(url);
				if (cookieString != null) {
					httpGet.setHeader("Cookie", cookieString);
				}
				response = httpClient.execute(httpGet);				
				break;
			case POST:
				httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
						CookiePolicy.RFC_2109);

				// Create local HTTP context
				HttpContext localContext = new BasicHttpContext();
				// Bind custom cookie store to the local context
				localContext.setAttribute(ClientContext.COOKIE_STORE,
						cookieStore);

				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(this.nameValuePairs));

				if (cookieString != null) {
					httpPost.setHeader("Cookie", cookieString);
				}
				httpPost.setHeader(
						"User-Agent",
						"Mozilla/5.0 (X11; U; Linux "
								+ "i686; en-US; rv:1.8.1.6) Gecko/20061201 Firefox/2.0.0.6 (Ubuntu-feisty)");
				httpPost.setHeader(
						"Accept",
						"text/html,application/xml,"
								+ "application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
				httpPost.setHeader("Content-Type",
						"application/x-www-form-urlencoded");

				// httpPost.addHeader("Cookie",
				// "_SchEnt2_session=740c8dc090d32826155e8eb8b8e63f37");

				response = httpClient.execute(httpPost, localContext);
				break;
			case PUT:
				HttpPut httpPut = new HttpPut(url);
				httpPut.setEntity(new StringEntity(data));
				response = httpClient.execute(httpPut);
				break;
			case DELETE:
				response = httpClient.execute(new HttpDelete(url));
				break;
			case BITMAP:
				response = httpClient.execute(new HttpGet(url));
				processBitmapEntity(response.getEntity());
				break;
			}
			if (method < BITMAP)
				processEntity(response, cookieStore);
		} catch (Exception e) {
			handler.sendMessage(Message.obtain(handler,
					HttpConnection.DID_ERROR, e));
		}
		ConnectionManager.getInstance().didComplete(this);
	}

	private void processEntity(HttpResponse response, CookieStore cookieStore)
			throws IllegalStateException, IOException {

		// -------------------- Analyze Headers ------------------------//

		Header[] headers = response.getAllHeaders();
		List<Cookie> cookies = cookieStore.getCookies();

		for (Cookie s : cookies) {
			if (s.getName().equals("user_credentials")) {
				cookieString += s.getName() + "=" + s.getValue() + "; ";
			}

			if (s.getName().equals("_SchEnt2_session")) {
				cookieString += s.getName() + "=" + s.getValue();
			}
		}

		if (CookieStorage.getInstance().getArrayList().isEmpty()
				&& cookieString != null) {
			CookieStorage.getInstance().getArrayList().add(cookieString);
		}

		// -------------------- Analyze Content ------------------------//
		HttpEntity entity = response.getEntity();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent(), "UTF-8"), 8);
		String line, result = "";
		while ((line = br.readLine()) != null)
			result += line;
		Message message = Message.obtain(handler, DID_SUCCEED, result);
		handler.sendMessage(message);
	}

	private void processBitmapEntity(HttpEntity entity) throws IOException {
		BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
		Bitmap bm = BitmapFactory.decodeStream(bufHttpEntity.getContent());
		handler.sendMessage(Message.obtain(handler, DID_SUCCEED, bm));
	}

}
