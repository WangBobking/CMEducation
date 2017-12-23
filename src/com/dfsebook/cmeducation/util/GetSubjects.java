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
import android.widget.Toast;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

import com.dfsebook.cmeducation.bean.Subject;

public class GetSubjects {

	private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/bmob/";
	
	private List<Subject> subjects = new ArrayList<Subject>();
	
	private SubjectListener subjectListener;
	
	private Context context;
	
	private StringBuilder sb = new StringBuilder();
	
//	public GetSubjects(Context context) {
//		this.context = context;
//	}

	public void get(BmobFile bmobFile) {
		sb.append("enter!!!!  ");
		File readFile = new File(FILE_PATH);
		File[] files = readFile.listFiles();
		for(File file : files) {
			if (file.getName().equals(bmobFile.getFilename())) {
				readFileToGetSubjects(file);
				return;
			}
		}
//		loadFileToGetSubjects(bmobFile);
	}

	public static void loadFileToGetSubjects(final Context context, BmobFile bmobFile) {
//		sb.append("load!!!!   ");
		final File saveFile = new File(FILE_PATH, bmobFile.getFilename());
	    bmobFile.download(saveFile, new DownloadFileListener() {

	        @Override
	        public void onStart() {
//	        	sb.append("start!!!   ");
	        }

	        @Override
	        public void done(String savePath,BmobException e) {
	            if(e==null){
//	            	sb.append("done!!!   ");
	            	BmobHelper.toast(context, "done");
//	            	readFileToGetSubjects(saveFile);
	            }else{
//	            	sb.append(e.getMessage());
	            	BmobHelper.toast(context, e.getMessage());
//	                if (subjectListener != null) {
//	                	subjectListener.onGetSubject(null);
//	                }
	            }
	        }

	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	            Log.i("bmob","下载进度："+value+","+newworkSpeed);
	        }

	    });
	}

	private void readFileToGetSubjects(File file) {
		sb.append("read!!!    ");
		BmobHelper.toast(context, sb.toString());
		try {
			InputStreamReader isr = new InputStreamReader(
					new FileInputStream(file),"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				Subject subject = convertStringToSubject(line);
				subjects.add(subject);
			}
			br.close();
			if (subjectListener != null) {
				subjectListener.onGetSubject(subjects);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
	
	private Subject convertStringToSubject(String line) {
		Subject s = new Subject();
		s.setStem(line.split("@")[0]);
		s.setOptions(line.split("@")[1].split("#")[0].split("○"));
		String[] ans = line.split("@")[1].split("#")[1].split(",");
		int[] answers = new int[5];
		for (String an : ans) {
			if (an.equals("A")) answers[0] = 1;
			if (an.equals("B")) answers[1] = 1;
			if (an.equals("C")) answers[2] = 1;
			if (an.equals("D")) answers[3] = 1;
			if (an.equals("E")) answers[4] = 1;
		}
		s.setAnswers(answers);
		return s;
	}
	
	public void setSubjectListener(SubjectListener subjectListener) {
		this.subjectListener = subjectListener;
	}

	public interface SubjectListener {
		void onGetSubject(List<Subject> subjects);
	}
}
