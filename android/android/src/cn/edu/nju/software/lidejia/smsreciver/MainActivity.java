package cn.edu.nju.software.lidejia.smsreciver;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.SmsMessage;

public class MainActivity extends Activity {

	private BlockingQueue<Message> sendQueue = new LinkedBlockingQueue<Message>();
	private ExecutorService es = Executors.newSingleThreadExecutor();
	private SMSReciver reciver = new SMSReciver();

	private class SMSReciver extends BroadcastReceiver {

		private static final String strACT = "android.provider.Telephony.SMS_RECEIVED";

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(strACT)) {
				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					Object[] pdus = (Object[]) bundle.get("pdus");
					SmsMessage[] msg = new SmsMessage[pdus.length];
					for (int i = 0; i < pdus.length; i++) {
						msg[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
					}
					for (SmsMessage currMsg : msg) {
						try {
							String telephone = currMsg
									.getDisplayOriginatingAddress();
							String body = currMsg.getDisplayMessageBody();
							if (body.indexOf('@') != -1) {
								String[] tmp = body.split("@");
								String user = tmp[1];
								String content = tmp[0];
								sendQueue.put(new Message(telephone, user,
										content));
							} else {
								sendQueue
										.put(new Message(telephone, null, body));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
							break;
						}
					}
				}
			}

		}

	}

	private class SMSSender implements Runnable {

		public void run() {
			HttpClient client = new DefaultHttpClient();
			while (true) {
				try {
					Message message = sendQueue.take();
					List<NameValuePair> qparams = new ArrayList<NameValuePair>();
					qparams.add(new BasicNameValuePair("telephone", message
							.getTelephone()));
					qparams.add(new BasicNameValuePair("user", message
							.getUser()));
					qparams.add(new BasicNameValuePair("content", message
							.getContent()));
					URI uri = URIUtils.createURI("http", "192.168.1.101", 80,
							"/ssxf-weibo/send.php",
							URLEncodedUtils.format(qparams, "UTF-8"), null);
					HttpGet httpget = new HttpGet(uri);
					HttpResponse response = client.execute(httpget);
					try {
						System.out.println("*******************");
						System.out.println(message.getContent());
						System.out.println("*******************");
					} finally {
						httpget.abort();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		registerReceiver(reciver, intentFilter);
		//
		PowerManager.WakeLock wakeLock = ((PowerManager) getSystemService(POWER_SERVICE))
				.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
						| PowerManager.ON_AFTER_RELEASE, "MainActivity");
		wakeLock.acquire();
		//
		es.execute(new SMSSender());
	}
}