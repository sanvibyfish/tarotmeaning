package com.sanvi.tarotmeaning;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;





/**
 * @author Sanvi E-mail:sanvibyfish@gmail.com
 * @version 创建时间：2010-8-25 上午11:48:00
 */
public class ActivityUtils {

	
	private static final String TAG = "ActivityUtils";
	
	/**
	 * 窗体跳转
	 * @param old
	 * @param cls
	 */
	public static void jump(Context old, Class<?> cls, int requestCode,Bundle mBundle){
		jump(old, cls, requestCode,mBundle,false);
	}
	
	/**
	 * 窗体跳转
	 * @param old
	 * @param cls
	 */
	public static void jump(Context old, Class<?> cls, int requestCode,Bundle mBundle,boolean clearTop){
		   Intent intent = new Intent();  
           intent.setClass(old, cls);
           if(mBundle != null){
        	   intent.putExtras(mBundle);
           }
           
           Activity activity = (Activity) old;
           if(clearTop){
        	   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           }
           activity.startActivityForResult(intent, requestCode); 
	}
	
	
	
	public static void jump(Context old, Class<?> cls, int requestCode){
		jump(old, cls, requestCode,null);
	}
	
	
	public static void back(Context old, Intent intent){
		   Activity activity = (Activity) old;
		   activity.setResult(Activity.RESULT_OK, intent);
		   activity.finish();
	}
	
	/**
	 * 添加控件(会删除之前layout所有控件)
	 * @param layout
	 * @param view
	 */
	public static void addViewOnly(ViewGroup layout, View view){
		try {
			if(layout.getChildCount() > 0){
				layout.removeAllViews();
			}
			layout.addView(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void runInUIThread(Context context, final Toast toast){
		final Activity activity = (Activity)context;
	      activity.runOnUiThread(new Runnable() {
	           public void run() {
	        	   toast.show();
	           }
	       });
	}
	
	public static Display getWindowDisplay(Context context){
		return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay(); 
	}
	
	public static NotificationManager getNotificationManager(Context mContext){
		return (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public static Notification buildNotification(Context mContext,String title,String info,Class jumpClass) {
		Notification notification = new Notification(R.drawable.icon, title, System.currentTimeMillis());
		notification.flags |= Notification.FLAG_ONGOING_EVENT; //将此通知放到通知栏的"Ongoing"即"正在运行"组中
		notification.flags |= Notification.FLAG_NO_CLEAR; //表明在点击了通知栏中的"清除通知"后，此通知不清除，
		Intent intent = new Intent(mContext,jumpClass);
		intent.putExtra("info", info);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(mContext, title, info,
				contentIntent);
		return notification;
	}
	
	public static String getDeviceId(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}
	public static String getString(Context context,final int mStringId){
		return context.getResources().getString(mStringId);
	}
	
	public static int getColor(Context context,final int mColorId){
		return context.getResources().getColor(mColorId);
	}
	
	public static float getPX(Context context, int dipValue){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
	}
}
