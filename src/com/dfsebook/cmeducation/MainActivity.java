package com.dfsebook.cmeducation;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import com.dfsebook.cmeducation.bean.Cme;
import com.dfsebook.cmeducation.util.BmobHelper;
import com.dfsebook.cmeducation.util.BmobHelper.DownLoadListener;

public class MainActivity extends Activity implements DownLoadListener{

	private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/";

	private List<BmobFile> bmobFiles = new ArrayList<BmobFile>();
	
	private List<String> localFiles = new ArrayList<String>();
	
	private List<String> names = new ArrayList<String>();
	
	private BmobHelper bh ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		delAllFile(FILE_PATH);
		File readFile = new File(FILE_PATH);		
		File[] files = readFile.listFiles();
		for (File file: files) {
			localFiles.add(file.getName());
		}
    	bh = new BmobHelper();
    	bh.setDownLoadListener(MainActivity.this);
    	
		BmobQuery<Cme> bmobQuery = new BmobQuery<Cme>();
		bmobQuery.findObjects(new FindListener<Cme>() {
		    @Override
		    public void done(List<Cme> object,BmobException e) {
		        if(e == null){
		        	for (Cme cme : object) {
		        		String name = cme.getFile().getFilename();
		        		name = name.substring(0, name.indexOf("."));
		        		names.add(name);
		        		if (!localFiles.contains(cme.getFile().getFilename())) {
		        			bmobFiles.add(cme.getFile());
		        		}
		        	}
		        	if (bmobFiles.size() > 0)
		        		bh.downloadFile(bmobFiles.get(0));
		        	else {
		        		Intent intent = new Intent(MainActivity.this, CourseActivity.class);
		    			intent.putExtra("fileNames", (Serializable)names);
		    			startActivity(intent);
		        	}
		        }else{
 				   Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
		        }
		    }
		});
	}	

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
//				delFolder(path + "/" + tempList[i]);
				flag = true;
			}
		}
		return flag;
	}


	
	private int index = 0;
	
	@Override
	public void onDowLoad(boolean completed) {
		index ++;		
		if (index < bmobFiles.size()) {
			bh.downloadFile(bmobFiles.get(index));
		} else {
			Intent intent = new Intent(MainActivity.this, CourseActivity.class);
			intent.putExtra("fileNames", (Serializable)names);
			startActivity(intent);
		}
	}
}
