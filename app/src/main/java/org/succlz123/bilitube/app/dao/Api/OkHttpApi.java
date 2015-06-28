package org.succlz123.bilitube.app.dao.Api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by fashi on 2015/4/12.
 */
public class OkHttpApi {
	private static OkHttpApi instance;

	public synchronized static OkHttpApi getInstance() {
		if (instance == null) {
			instance = new OkHttpApi();
		}
		return instance;
	}

	private OkHttpApi() {

	}

	public String doGet(String adress) {
		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request.Builder().url(adress).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				String Json = response.body().string();
				return Json;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//图片下载
	public String doImageGet(String adress, String filePath) {
		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request.Builder().url(adress).build();
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				InputStream is = response.body().byteStream();
				OutputStream os = new FileOutputStream(filePath);
				byte[] bt = new byte[2048];
				int byteread;
				while ((byteread = is.read(bt)) != -1) {
					os.write(bt, 0, byteread);
				}
				os.flush();
				is.close();
				os.close();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
