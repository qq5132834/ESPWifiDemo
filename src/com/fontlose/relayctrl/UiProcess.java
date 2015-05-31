package com.fontlose.relayctrl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 

 












import BaseInfo.SendInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class UiProcess {
 
	/**
	 * 消息机制
	 * */
	HandleMsg myHand = new HandleMsg(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(dataProcess==null) return;
			
			Log.e("消息类型", msg.what+"");
			
			//连接服务器
			if(msg.what==DataProcess.ConnectServerOK){//显示连接成功
				String ip = (String) msg.obj;
				etIp.setText(ip);
				Toast.makeText(mct, "深圳大学 黄聊连接成功"+ip, 0).show();
				bnConnect.setImageResource( R.drawable.true1);
				myHand.stateCheck(1);
				setEditEnable(false);
			}else if(msg.what==DataProcess.ConnectServerFailed){//显示连接失败
				String ip = (String) msg.obj;
				etIp.setText(ip);
			}
			
			//灯光控制
			if(msg.what==DataProcess.LigthController){
				String str = SendInfo.getSendInfo();
				if(str!=null){
					Log.e("发送的数据:", str);
					dataProcess.sendrelayCmd(msg.arg1,msg.arg2,str);
				}else{
					Toast.makeText(mct, "系统错误", 0).show();
				}
			}
			
			//加载进度条
			if(msg.what==DataProcess.startLoading){
				dialog = ProgressDialog.show(mct, null, "正在匹配连接装置，请稍候...", true, false);  
			}else if(msg.what==DataProcess.endLoading){
				if(dialog!=null){
					dialog.dismiss();  
				}
			}
		}
	 
	};
	
	private ProgressDialog dialog;  
	
	HandleMsg hUiMsg=null;
   Context mct=null;
   DataProcess dataProcess=null;
   
	private ImageButton bnConnect;
	private EditText etIp;
	private TextView tv1,tv2,tv3,tv4,tvn1,tvn2,tvn3,tvn4;
	private ImageView im1,im2,im3,im4;
	private SeekBar seekBar = null;
	
	public String nameRel1,nameRel2,nameRel3,nameRel4;
	
	SoundPool snd = new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
	int hitOkSfx;
	
	
	ProgressDialog pdialog ; 
	
	public void soundPlay()
	{
		snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
	 
	}
	
	
	
	
	public UiProcess(LinearLayout mainLayout,Context context,HandleMsg hMsg,DataProcess dProcess) {
		super();
		Button bnRealyOn,bnRelayOff;
		hUiMsg=hMsg;
		mct=context;
		dataProcess=dProcess;
		
		hitOkSfx = snd.load(mct, R.raw.ping_short, 0);
		
		/**
		 * seekBar滑动条变化
		 * */
		this.seekBar = (SeekBar) mainLayout.findViewById(R.id.seekBar);
		this.seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.e("滑动条的值", progress+"");
				SendInfo.lightSize=progress;
				
				/*Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);*/
				
				
			}
		});
 
		bnRealyOn=(Button)mainLayout.findViewById(R.id.bnOn1);
		bnRealyOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "redLedOn", 0).show();
				
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				SendInfo.lightColor="R";
				
				Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);
				
				
				
			}
		});
		
		bnRelayOff=(Button)mainLayout.findViewById(R.id.bnOff1);
		bnRelayOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "redLedOff", 0).show();
				
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				SendInfo.lightColor="r";
				
				Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);
				
				
				
			}
		});
		 
		bnRealyOn=(Button)mainLayout.findViewById(R.id.bnOn2);
		bnRealyOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "GreenLedOn", 0).show();
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				SendInfo.lightColor="G";
				
				Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);
				
				
				
			}
		});
		bnRelayOff=(Button)mainLayout.findViewById(R.id.bnOff2);
		bnRelayOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "GreenLedOff", 0).show();
				
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				SendInfo.lightColor="g";
				
				Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);
				
				
				
			}
		});
		 
		bnRealyOn=(Button)mainLayout.findViewById(R.id.bnOn3);
		bnRealyOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "bnOn3", 0).show();
				
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				
				SendInfo.lightColor="B";
				
				Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);
				
				
				
			}
		});
		bnRelayOff=(Button)mainLayout.findViewById(R.id.bnOff3);
		bnRelayOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "bnOff3", 0).show();
				
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				SendInfo.lightColor="b";
				
				Message msg=new Message();
				msg.what=DataProcess.LigthController; //开始红色LED
				myHand.sendMessage(msg);
				
				
				
			}
		});
	 
		bnRealyOn=(Button)mainLayout.findViewById(R.id.bnOn4);
		bnRealyOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "bnOn4", 0).show();
			}
		});
 
		bnRelayOff=(Button)mainLayout.findViewById(R.id.bnOff4);
		bnRelayOff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "bnOff4", 0).show();
			}
		});
	 
		
		
		
		 tv1=(TextView)mainLayout.findViewById(R.id.tv1);
		 tv2=(TextView)mainLayout.findViewById(R.id.tv2);
		 tv3=(TextView)mainLayout.findViewById(R.id.tv3);
		 tv4=(TextView)mainLayout.findViewById(R.id.tv4);
		 
		 tvn1=(TextView)mainLayout.findViewById(R.id.tvname1);
		 tvn2=(TextView)mainLayout.findViewById(R.id.tvname2);
		 tvn3=(TextView)mainLayout.findViewById(R.id.tvname3);
		 tvn4=(TextView)mainLayout.findViewById(R.id.tvname4);
		 
		 im1=(ImageView)mainLayout.findViewById(R.id.im1);
		 im2=(ImageView)mainLayout.findViewById(R.id.im2);
		 im3=(ImageView)mainLayout.findViewById(R.id.im3);
		 im4=(ImageView)mainLayout.findViewById(R.id.im4);
		  
		
		 loadConfigure();
		 updateName();
		
		 setRelayState(0);
		
		 etIp  =(EditText)mainLayout.findViewById(R.id.etIp);
		 
		 loadIpPort();
		
		
		 bnConnect=(ImageButton)mainLayout.findViewById(R.id.bnConnect);
		 bnConnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				// TODO Auto-generated method stub
				 if(isEditEnable()){
				
					 
					 String myIP=etIp.getText().toString().trim();
					 if(dataProcess.startConn(myIP, 333)){
						 
						 Log.e("默认IP地址成功连接：", myIP);
						 Message msg=new Message();
						 msg.what=DataProcess.ConnectServerOK; //成功连接
						 msg.obj = myIP;
						 myHand.sendMessage(msg);
						 
					 }else{
						 
						 /**
						  * 开启一个线程来探测服务器IP地址
						  * */
						 new Thread(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								super.run();
								 
								Message msg0=new Message();
								msg0.what=DataProcess.startLoading; //开启进度条
								myHand.sendMessage(msg0);
								 
								boolean st2 = false;
								for(int i=0;i<3;i++){
									 for(int j=100;j<111;j++){
										 String sip = "192.168."+i+"."+j;
										 Log.e("尝试IP地址：", sip);
										 boolean st = dataProcess.startConn(sip, 333);
										 if(st){
											 Log.e("装置IP地址为：", sip);
											 st2 = true;
											 Message msg=new Message();
											 msg.what=DataProcess.ConnectServerOK; //成功连接
											 msg.obj = sip;
											 myHand.sendMessage(msg);

											 Message msg1=new Message();
											 msg1.what=DataProcess.endLoading; //开启进度条
											 myHand.sendMessage(msg1);
											 
											 break;
										 }else{
											 Message msg=new Message();
											 msg.what=DataProcess.ConnectServerFailed; //失败连接
											 msg.obj = sip;
											 myHand.sendMessage(msg);
										 }
									 }
									 if(st2){
										 break;
									 }
									 Message msg1=new Message();
									 msg1.what=DataProcess.endLoading; //开启进度条
									 myHand.sendMessage(msg1);
								 }
							}
							 
						 }.start();
						 
						 
						 
					 }
					 
					
				 }else{ 
					 stopConn();
				 }
			}
		});
   }	
	
	
	
	public void stopConn( )
	{
		 dataProcess.stopConn();
		 hUiMsg.stateCheck(0);
		 RelayCtrlActivity.showMessage(mct.getString(R.string.msg3));
		 bnConnect.setImageResource(R.drawable.false0);
		 setEditEnable(true);
		 setRelayState(0);
	} 
	
	
	public void setEditEnable(boolean en)
	{
		etIp.setEnabled(en); 		
	 
		if(en==false) 
		{
			etIp.setInputType(InputType.TYPE_NULL);
			 
		}
		else   
		{
			etIp.setInputType(InputType.TYPE_CLASS_TEXT);
			 
		}
	}
	
	public boolean isEditEnable()
	{
		return etIp.isEnabled(); 
	}	
	
 
	
	
	private byte[] state_l=new byte[]{(byte)0xFF,(byte)0xff,(byte)0xFF,(byte)0xff}; 
	
	public void setRelayState(int state)
	{//缁х數鍣�1
		byte sat=(byte)(state&0xff);
 		if(state_l[0]!=sat)
 		{  
 			state_l[0]=sat;
 			setRelayOnoff(tv1,im1,sat); 
 		}
		
		
		
		state=state>>8;		
		sat=(byte)(state&0xff);
 		if(state_l[1]!=sat)
 		{  
 			state_l[1]=sat;
 			setRelayOnoff(tv2,im2,sat); 
 		}
 		
 		
		state=state>>8;
		sat=(byte)(state&0xff);
 		if(state_l[2]!=sat)
 		{  
 			state_l[2]=sat;
 			setRelayOnoff(tv3,im3,sat); 
 		}
	
		state=state>>8;
		sat=(byte)(state&0xff);
 		if(state_l[3]!=sat)
 		{  
 			state_l[3]=sat;
 			setRelayOnoff(tv4,im4,sat); 
 		}
 
	}
	
	
	public void setRelayOnoff(TextView tv,ImageView im,byte state)
	{//缁х數鍣�1		 
		if(state==0x1)
		{
			tv.setText(R.string.state0);
			im.setImageResource(R.drawable.off);
		}
		else if(state==0x2)
		{
			tv.setText(R.string.state1);
			im.setImageResource(R.drawable.on);
		}
		else
		{
			tv.setText(R.string.state2);
			im.setImageResource(R.drawable.off);
		}
	}
	
	private EditText etRelay1,etRelay2,etRelay3,etRelay4;
	
	
 
	  
	
	    public void loadConfigure()
	     {
	  	    SharedPreferences uiState   = mct.getSharedPreferences("system", mct.MODE_PRIVATE);
	  	    nameRel1  =uiState.getString("relay1", mct.getString(R.string.relay1));
	  	    nameRel2  =uiState.getString("relay2", mct.getString(R.string.relay2));
	  	    nameRel3  =uiState.getString("relay3", mct.getString(R.string.relay3));
	  	    nameRel4  =uiState.getString("relay4", mct.getString(R.string.relay4)); 
	     }
	      
	     public void saveConfigure()
	     {
	  	    SharedPreferences uiState   = mct.getSharedPreferences("system", mct.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("relay1",nameRel1);
	  		et.putString("relay2",nameRel2);
	  		et.putString("relay3",nameRel3);
	  		et.putString("relay4",nameRel4); 
	  		et.commit();
	     } 
	
	     public void updateName()
	     {
	    	 tvn1.setText(nameRel1); 
	    	 tvn2.setText(nameRel2); 
	    	 tvn3.setText(nameRel3); 
	    	 tvn4.setText(nameRel4); 
	     } 
	
	
	     public void saveIpPort()
	     {
	  	    SharedPreferences uiState   = mct.getSharedPreferences("system", mct.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		et.putString("ip",etIp.getText().toString());
	  		 
	  		et.commit();
	     } 
	 
	     public void loadIpPort()
	     {
	  	    SharedPreferences uiState   = mct.getSharedPreferences("system", mct.MODE_PRIVATE);
	  	    etIp.setText(uiState.getString("ip","192.168.4.1" ));
	  	   
	     }
	
	
}
