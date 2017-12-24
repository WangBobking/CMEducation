package com.dfsebook.cmeducation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Environment;
import android.util.Log;

import com.dfsebook.cmeducation.bean.Subject;

public class GetSubjects {

	private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/bmob/";
	
	private List<Subject> subjects = new ArrayList<Subject>();
	
	private SubjectListener subjectListener;

	public void readFileToGetSubjects(String fileName) {
		if (subjectListener == null) return;
		File file = new File(FILE_PATH + fileName + ".txt"); 
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
			subjectListener.onGetSubject(subjects);
		} catch (Exception e) {
			e.printStackTrace();
			subjectListener.onGetSubject(null);
		}		
	}
	
	private Subject convertStringToSubject(String line) {
		Subject s = new Subject();
		s.setStem(line.split("@")[0]);
		s.setOptions(line.split("@")[1].split("#")[0].split("○"));
		Log.i("bmob", Arrays.toString(s.getOptions()));
		String ans = line.split("@")[1].split("#")[1];
		int[] answers = new int[5];
		if (ans.contains("A")) answers[0] = 1;
		if (ans.contains("B")) answers[1] = 1;
		if (ans.contains("C")) answers[2] = 1;
		if (ans.contains("D")) answers[3] = 1;
		if (ans.contains("E")) answers[4] = 1;
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
