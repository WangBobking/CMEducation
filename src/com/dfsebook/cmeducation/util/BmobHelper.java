package com.dfsebook.cmeducation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import com.dfsebook.cmeducation.adapter.StudyAdapter;
import com.dfsebook.cmeducation.bean.Cme;

public class BmobHelper {
	
	private List<BmobFile> bmobFiles = new ArrayList<BmobFile>();
	 
	private List<BmobFile> dloads = new ArrayList<BmobFile>();
	
	private List<String> existFiles = new ArrayList<String>();
	
	public void getBmobFiles(final Context context, final ListView listView) {
		BmobQuery<Cme> bmobQuery = new BmobQuery<Cme>();
		bmobQuery.findObjects(new FindListener<Cme>() {
		    @Override
		    public void done(List<Cme> object,BmobException e) {
		        if(e==null){
		            for (Cme cme : object) {
		                BmobFile file = cme.getFile();
		               if(file!= null){
		                    bmobFiles.add(file);
		               }		        	
		            }
		            StudyAdapter adapter = new StudyAdapter(context,bmobFiles);
		            listView.setAdapter(adapter);
		        }else{
		            toast(context, "查询失败："+e.getMessage());
		        }
		    }
		});
	}
	
	public void getExistFilles() {
		File readFile = new File(Environment.getExternalStorageDirectory() + "/bmob/");
		File[] files = readFile.listFiles();
		for (File f : files) {
			existFiles.add(f.getName());
		}
	}

	public void downLoad(Context context) {
		for (BmobFile bf : dloads) {
			downloadFile(context, bf);
		}
	}
	
	public void getNeedDownloadFiles() {
		for (BmobFile bf : bmobFiles) {
			if (!existFiles.contains(bf.getFilename())) {
				dloads.add(bf);
			}
		}
	}
	
	public void readFile(Context context, String fileName) {		
		File readFile = new File(Environment.getExternalStorageDirectory() + "/bmob/" + fileName);
		try {
			InputStreamReader isr = new InputStreamReader(
					new FileInputStream(readFile),"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			int size = 0;
			while (br.readLine() != null) {
				size ++;
			}
			toast(context, size + "-----size");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void downloadFile(final Context context, BmobFile file){
	    File saveFile = new File(Environment.getExternalStorageDirectory() + "/bmob/", file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {

	        @Override
	        public void onStart() {
	            toast(context, "开始下载...");
	        }

	        @Override
	        public void done(String savePath,BmobException e) {
	            if(e==null){
	                toast(context, "下载成功,保存路径:"+savePath);
	            }else{
	                toast(context, "下载失败："+e.getErrorCode()+","+e.getMessage());
	            }
	        }

	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	            Log.i("bmob","下载进度："+value+","+newworkSpeed);
	        }

	    });
//		toast(context, "enter");
	} 
	
	public static void toast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
