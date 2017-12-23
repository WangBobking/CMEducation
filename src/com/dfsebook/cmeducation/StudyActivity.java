package com.dfsebook.cmeducation;

import java.util.List;

import com.dfsebook.cmeducation.adapter.StudyAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.bmob.v3.datatype.BmobFile;

public class StudyActivity extends Activity implements OnItemClickListener{

	private ListView listView;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study);
		listView = (ListView)findViewById(R.id.study_show);
		Intent intent = getIntent();
		List<BmobFile> bmobFiles = (List<BmobFile>)intent.getSerializableExtra("bmobFiles");
		StudyAdapter adapter = new StudyAdapter(this, bmobFiles);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		setTitles();
	}

	private void setTitles() {		
			
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}

}
