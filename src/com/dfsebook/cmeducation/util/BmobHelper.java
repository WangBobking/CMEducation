package com.dfsebook.cmeducation.util;

import java.io.File;

import android.os.Environment;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

public class BmobHelper {

	private DownLoadListener downLoadListener; 
		
	public void downloadFile(BmobFile file){
		if (downLoadListener == null) return;
	    File saveFile = new File(Environment.getExternalStorageDirectory() + "/bmob/", file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {

	        @Override
	        public void onStart() {
	        }

	        @Override
	        public void done(String savePath,BmobException e) {
	            if(e==null){
	            	downLoadListener.onDowLoad(true);
	            }else{
	                downLoadListener.onDowLoad(false);
	            }
	        }

	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	        }

	    });
	} 
	
	public void setDownLoadListener(DownLoadListener downLoadListener) {
		this.downLoadListener = downLoadListener;
	}

	public interface DownLoadListener {
		void onDowLoad(boolean completed);
	}
}
