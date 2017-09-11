package com.kg.konggang_guide.other.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kg.konggang_guide.R;


/**
 * 吐司提示类
 * @author jack.wuxiazhi
 *
 */
public class ToastUtil {
	private Handler mHandler = new Handler();
	private Context context;
	private Toast toast;
	private static ToastUtil instance;
	private ToastUtil(Context context){
		this.context=context;
		this.toast=Toast.makeText(this.context, "", Toast.LENGTH_SHORT);
	}
	public static ToastUtil getInstance(Context context){
		if(instance==null){
			if (instance == null) {
				synchronized (ToastUtil.class) {
					if (instance == null) {
						instance = new ToastUtil(context);
					}
				}
			}
		}
		return instance;
	}
	/**
	 * 短提示
	 * @param text
	 */
	public void shortToast(String text){
		toast.setText(text);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}
	/**
	 * 短提示
	 * @param text
	 */
	public void longToast(String text){
		toast.setText(text);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}
	/**
	 * 长提示
	 * @param id
	 */
	public void shortToast(int id){
		shortToast(context.getString(id));
	}
	/**
	 * 长提示
	 * @param id
	 */
	public void longToast(int id){
		longToast(context.getString(id));
	}
	/**
	 * 警告提示
	 * @param string
	 */
	public void WarmingToast(String string){

	}

	/**
	 * 自定义toast
	 */
	public void curToast(String string){
		View view= LayoutInflater.from(context).inflate(R.layout.toast_background,null);
		toast.setView(view);
		TextView textView= (TextView) view.findViewById(R.id.tv_content);
		textView.setText(string);
		toast.setGravity(Gravity.CENTER,0,0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}

}
