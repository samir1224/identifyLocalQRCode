package com.example.com.samir1224.test.identifylocalqrcode;

import java.io.IOException;
import java.io.InputStream;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			BinaryBitmap bbm = loadImage("", this);
			
			QRCodeReader qrReader = new QRCodeReader();
			
			Result result = qrReader.decode(bbm);
		
			System.err.println(result.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private BinaryBitmap loadImage(String fileName, Context context) {
    	try {
    		
    		InputStream is = null;
			try {
				is = this.getAssets().open("qr_code_image.jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bitmap bm = BitmapFactory.decodeStream(is);
			
	        return new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(bm)));
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
