package com.dfsebook.cmeducation;

import java.util.List;

import com.dfsebook.cmeducation.adapter.StudyAdapter;
import com.dfsebook.cmeducation.bean.Subject;
import com.dfsebook.cmeducation.util.BmobHelper;
import com.dfsebook.cmeducation.util.GetSubjects;
import com.dfsebook.cmeducation.util.GetSubjects.SubjectListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.datatype.BmobFile;

public class StudyActivity extends Activity implements SubjectListener{

	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_show);
		Intent intent = getIntent();
		String fileName = intent.getStringExtra("fileName");
        setTitle(fileName);
		listView = (ListView)findViewById(R.id.showList);
		GetSubjects gs = new GetSubjects();
		gs.setSubjectListener(this);
		gs.readFileToGetSubjects(fileName);
	}

	@Override
	public void onGetSubject(List<Subject> subjects) {
		StudyAdapter adapter = new StudyAdapter(this, subjects);
		listView.setAdapter(adapter);
	}
}
