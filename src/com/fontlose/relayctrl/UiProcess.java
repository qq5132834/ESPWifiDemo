package com.fontlose.relayctrl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 

 




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
import android.widget.TextView;
import android.widget.Toast;

public class UiProcess {
 
   
   HandleMsg hUiMsg=null;
   Context mct=null;
   DataProcess dataProcess=null;
    
	private ImageButton bnConnect;
	private EditText etIp;
	private TextView tv1,tv2,tv3,tv4,tvn1,tvn2,tvn3,tvn4;
	private ImageView im1,im2,im3,im4;
	//QPopupWindow createConfigWindow;
	
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
 
		bnRealyOn=(Button)mainLayout.findViewById(R.id.bnOn1);
		bnRealyOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mct, "redLedOn", 0).show();
				
				snd.play(hitOkSfx, (float)0.5, (float)0.5, 0, 0, 1); 
				if(hUiMsg==null) return;
				
				Message msg=new Message();
				msg.what=DataProcess.redLedOn; //开始红色LED
				hUiMsg.sendMessage(msg);
				
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
				
				Message msg=new Message();
				msg.what=DataProcess.redLedOff; //开始红色LED
				hUiMsg.sendMessage(msg);
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
				
				Message msg=new Message();
				msg.what=DataProcess.GreenLedOn; //开始红色LED
				hUiMsg.sendMessage(msg);
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
				
				Message msg=new Message();
				msg.what=DataProcess.GreenLedOff; //开始红色LED
				hUiMsg.sendMessage(msg);
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
				
				Message msg=new Message();
				msg.what=DataProcess.BlueLedOn; //开始红色LED
				hUiMsg.sendMessage(msg);
				
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
				
				Message msg=new Message();
				msg.what=DataProcess.BlueLedOff; //开始红色LED
				hUiMsg.sendMessage(msg);
				
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
				 if(isEditEnable())
				 {
					 
					String sip    =etIp.getText().toString().trim();
					 
					 
					Toast.makeText(mct, sip+":"+333, 0).show();
					 
					Pattern pa=Pattern.compile("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");
					Matcher ma=pa.matcher(sip);
					if(ma.matches()==false)
					{  
						RelayCtrlActivity.showMessage(mct.getString(R.string.msg6));
						return;
					} 
					 
					int port=0;
					try {
							port=Integer.parseInt("333");
					} catch (Exception e) {
						RelayCtrlActivity.showMessage(mct.getString(R.string.msg7));
						return ;
					}
					 
					 
					 if(dataProcess.startConn(sip, 333)) //建立服务器连接
					 {
						 RelayCtrlActivity.showMessage(mct.getString(R.string.msg2));
						 bnConnect.setImageResource( R.drawable.true1);
						 hUiMsg.stateCheck(1);
						 setEditEnable(false);
					 }
					 else
					 {
						 //提示超时连接 
						 RelayCtrlActivity.showMessage(mct.getString(R.string.msg1));
					 }
				 }
				 else
				 { 
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
	
	
	 public QPopupWindow createConfigWindow(){/*澧炲姞杩炴帴鎸夐挳*/
		LayoutInflater factory=LayoutInflater.from(mct);
		LinearLayout lout=(LinearLayout)factory.inflate(R.layout.name,null);
 		QPopupWindow popupWindow = new QPopupWindow(lout,  LayoutParams.FILL_PARENT,  LayoutParams.WRAP_CONTENT,true);
 

		etRelay1=(EditText)lout.findViewById(R.id.etRelay1);
		etRelay2=(EditText)lout.findViewById(R.id.etRelay2);
		etRelay3=(EditText)lout.findViewById(R.id.etRelay3);
		etRelay4=(EditText)lout.findViewById(R.id.etRelay4);
		  	
		etRelay1.setText(nameRel1);
		etRelay2.setText(nameRel2);
		etRelay3.setText(nameRel3);
		etRelay4.setText(nameRel4);
		
		
		
  		Button bnClk=(Button)lout.findViewById(R.id.bnSet);
  		bnClk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundPlay();
				//createConfigWindow.dismiss();
				nameRel1=etRelay1.getText().toString();
				nameRel2=etRelay2.getText().toString();
				nameRel3=etRelay3.getText().toString();
				nameRel4=etRelay4.getText().toString();
				saveConfigure();
				updateName();
				RelayCtrlActivity.showMessage(mct.getString(R.string.setSuccess));
			}
		});
  		
  		
  		bnClk=(Button)lout.findViewById(R.id.bnCancel);
  		bnClk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundPlay();
				//createConfigWindow.dismiss();
			}
		});
  		
		
 		popupWindow.setOutsideTouchable(true);		
		popupWindow.setTouchable(true); 
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setAnimationStyle(R.style.PopupAnimation);
		popupWindow.update(); 
		
		return popupWindow; 
	}
	  
	
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
