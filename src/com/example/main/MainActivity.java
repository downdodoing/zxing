package com.example.main;

import zblibrary.demo.activity_fragment.QRCodeActivity;
import zuo.biao.library.util.CommonUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.zxing.R;
import com.example.zxing.activity.CaptureActivity;
import com.example.zxing.camera.CameraManager;
import com.example.zxing.view.ViewfinderView;

public class MainActivity extends CaptureActivity implements Callback,
		OnClickListener {

	public static final String TAG = "ScanActivity";

	// 启动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**
	 * 启动这个Activity的Intent
	 * 
	 * @param context
	 * @param title
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, MainActivity.class);
	}

	// 启动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init(this, (SurfaceView) findViewById(R.id.svCameraScan),
				(ViewfinderView) findViewById(R.id.vfvCameraScan));

		// 功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initListener();
		// 功能归类分区方法，必须调用>>>>>>>>>>

	}

	@Override
	public void initView() {// 必须调用

	}

	@Override
	public void initData() {// 必须调用

	}

	@Override
	public void initListener() {// 必须调用

		findViewById(R.id.tvCameraScanReturn).setOnClickListener(this);
		findViewById(R.id.ivCameraScanReturn).setOnClickListener(this);
		findViewById(R.id.ivCameraScanLight).setOnClickListener(this);
		findViewById(R.id.ivCameraScanMyQRCode).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvCameraScanReturn:
		case R.id.ivCameraScanReturn:
			finish();
			break;
		case R.id.ivCameraScanLight:
			switchLight(!isOpen);
			break;
		case R.id.ivCameraScanMyQRCode:
			CommonUtil.toActivity(context,
					QRCodeActivity.createIntent(context, 1));
			break;
		default:
			break;
		}
	}

	private boolean isOpen = false;

	/**
	 * 打开或关闭闪关灯
	 * 
	 */
	private void switchLight(boolean open) {
		if (open == isOpen) {
			return;
		}
		isOpen = CameraManager.get().switchLight(open);
	}

}
