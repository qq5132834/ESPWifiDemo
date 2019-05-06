package com.fontlose.relayctrl;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
	
public class RelayCtrlActivity extends Activity {
    
	private TextView showData;
	private Button LinkESP;
	
	//路由器连接
	private EditText APssid;
	private EditText APpwd;
	private Button APConnectButton;
	
	//连接P2P云服务
	private EditText P2Paddress;
	private EditText P2Pport;
	private Button P2PConnectButton;
	
	//
	private Button ST3;
	private Button ToP2P;
	
	
	private Button goButton;
	private Button leftButton;
	private Button rightButton;
	private Button backButton;
	
	
	private Socket socket;
	private static PrintWriter printWriter;
	
	public Handler mHandler= null;
	
	public static String P2P_IP = "192.168.0.115";
	public static String P2P_PORT = "8090";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.showData = (TextView) findViewById(R.id.showData);
        this.mHandler =new Handler(){  
	        public void handleMessage(Message msg) {  
	        	int what = msg.what;
	        	Bundle bundle = msg.getData();
	        	String val = (String) bundle.get("key");
	        	Toast.makeText(RelayCtrlActivity.this, val, Toast.LENGTH_LONG).show();
	        	showData.setText(val);
	        }     
	    }; 

        /**
         * 连接ap与消息显示
         * */
	    this.LinkESP = (Button) findViewById(R.id.LinkESP); //连接ESP8266服务
        this.LinkESP.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(RelayCtrlActivity.this, "连接AP热点", Toast.LENGTH_LONG).show();
				showData.setText("连接AP热点");
					
					new Thread(){
						@Override
						public void run() {
							try {
								Log.e("", "start connect.");
								socket = new Socket("192.168.4.1",80);
								Log.e("", "start connect success.");
								OutputStream out = socket.getOutputStream();
					            OutputStreamWriter osw  = new OutputStreamWriter(out,"UTF-8");
					            printWriter = new PrintWriter(osw,true);
				                
				                DataInputStream reader =  new DataInputStream( socket.getInputStream()); 
				                
				                InputStream in = socket.getInputStream();
				                InputStreamReader isr = new InputStreamReader(in,"UTF-8");
				                BufferedReader br  = new BufferedReader(isr);
				                 
				                String message = null;
				                while((message=br.readLine())!=null){
				                	Log.e("receive", message);
				                	sendMessage(message);
				                }
							} catch (Exception e) {
								e.printStackTrace();
								Log.e("Exception", e.toString());
							}
							
						}
					}.start();
				 
			}
		});
        
        
        /**
         * nodemcu连接路由器
         * */
        this.APssid = (EditText) findViewById(R.id.APssid);
        this.APpwd = (EditText) findViewById(R.id.APpwd);
        this.APConnectButton = (Button) findViewById(R.id.APConnectButton);
        this.APConnectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(printWriter!=null){
					String ssid = APssid.getText().toString();
					String pwd = APpwd.getText().toString();
					printWriter.println("AP="+ssid+"="+pwd+"=");
				}
				else{
					sendMessage("未建立连接");
				}
			}
		});
        
       /**
        * 将nodemcu连接上P2P云服务
        * */
       this.P2Paddress = (EditText) findViewById(R.id.P2Paddress);
       this.P2Pport = (EditText) findViewById(R.id.P2Pport);
       this.P2PConnectButton = (Button) findViewById(R.id.P2PConnectButton);
       this.P2PConnectButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			P2P_IP = P2Paddress.getText().toString();
		    P2P_PORT = P2Pport.getText().toString();
			if(printWriter!=null){
				printWriter.println("P2P=nodemcu="+P2P_IP+"="+P2P_PORT+"=");
			}
			else{
				sendMessage("未建立连接");
			}
		}
	});
     
 
       this.ST3 = (Button) findViewById(R.id.ST3);
       this.ST3.setOnClickListener(new OnClickListener() {
   		
   		@Override
   		public void onClick(View v) {
   			if(printWriter!=null){
   				printWriter.println("ST3=");
   			}
   			else{
   				sendMessage("未建立连接");
   			}
   		}
   	});
       this.ToP2P = (Button) findViewById(R.id.ToP2P);
       this.ToP2P.setOnClickListener(new OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {
      			P2P_IP = P2Paddress.getText().toString();
    		    P2P_PORT = P2Pport.getText().toString();
				Intent intent = new Intent();
	            intent.setClass(RelayCtrlActivity.this, P2PActivity.class); 
	            startActivity(intent);
      		}
      	});
        
        
     
        this.goButton = (Button) findViewById(R.id.goButton);
        this.goButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(printWriter!=null){
					Log.e("send", "ST0=222");
					printWriter.println("ST0=222");
				}else{
					Log.e("send", "null");
				}
				
			}
		});
        
        
        this.leftButton = (Button) findViewById(R.id.leftButton);
        this.leftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(printWriter!=null){
					Log.e("send", "ST0=444");
					printWriter.println("ST0=444");
				}else{
					Log.e("send", "null");
				}
				
			}
		});
        
        
        this.rightButton = (Button) findViewById(R.id.rightButton);
        this.rightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(printWriter!=null){
					Log.e("send", "ST0=666");
					printWriter.println("ST0=666");
				}else{
					Log.e("send", "null");
				}
				
			}
		});
        
        this.backButton = (Button) findViewById(R.id.backButton);
        this.backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(printWriter!=null){
					Log.e("send", "ST0=888");
					printWriter.println("ST0=888");
				}else{
					Log.e("send", "null");
				}
			}
		});
        
    }

    
    private void sendMessage(String message){
		Message msg = new Message();
		msg.what = 1;
		Bundle data = new Bundle();
		data.putString("key", message);
		msg.setData(data);
		mHandler.sendMessage(msg);
	}
	
     
}