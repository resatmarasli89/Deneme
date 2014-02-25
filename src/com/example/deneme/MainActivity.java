package com.example.deneme;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.adapters.TabsPagerAdapter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Top Rated", "Games", "Movies" };

	private static final String METHOD_NAME = "HelloWorld";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String SOAP_ACTION = "http://tempuri.org/IService1/HelloWorld";

	// 10.0.2.2 is the ip address for the device simulator.
	private static final String URL = "http://10.0.2.2:64186/Service1.svc";

	// SOAP must be the same version as the webservice.
	private static final int SOAP_VERSION = SoapEnvelope.VER11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();

		// try {
		// HttpClient httpClient = new DefaultHttpClient();
		// HttpContext localContext = new BasicHttpContext();
		// HttpGet httpGet = new HttpGet(
		// "http://webservices.gama-system.com/files/GetCurrentExchangeRatesXML.xml");
		// HttpResponse response = httpClient.execute(httpGet, localContext);
		// Log.i("response", response.toString());
		// } catch (ClientProtocolException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
		helloWorld();
	}

	public void initUI() {

		Countly.sharedInstance().init(MainActivity.this,
				"https://cloud.count.ly", "53033767172bb08c52038fb6");

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Countly.sharedInstance().onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Countly.sharedInstance().onStop();
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(arg0.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	private void helloWorld() {
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SOAP_VERSION);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

			androidHttpTransport.call(SOAP_ACTION, envelope);
			Object result = envelope.getResponse();

			String resultData = result.toString();
			Log.i("result", resultData);
		} catch (IOException e) {
			Log.i("err", e.toString());
		} catch (XmlPullParserException e) {
			Log.i("err", e.toString());
		}
	}
}
