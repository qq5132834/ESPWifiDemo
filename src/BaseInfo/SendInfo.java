package BaseInfo;

import android.util.Log;

public class SendInfo {
	/**
	 * 光线强弱值（0-255）
	 * */
	public static int lightSize = 1;
	/**
	 * 灯光的颜色判断
	 * */
	public static String lightColor = "T";
	/**
	 * 人体传感器的开启与关闭
	 * */
	public static String sensorState = "O";
	/**
	 * 向服务器发送的数据
	 * */
	public static String getSendInfo(){
		String info = "";
		info = sensorState.trim()+lightColor.trim()+(char)lightSize;
		Log.e("基本信息", info+"--"+info.length());
		if(info.length()!=3){
			return null;
		}
		return info;
	}
	/**
	 * 测试灯光
	 * */
	public static String testLedInfo(){
		return "TTTTT";
	}
	
}
