package com.dfsebook.cmeducation;

import java.io.Serializable;
import java.util.List;

import com.dfsebook.cmeducation.adapter.CourseAdapter;
import com.dfsebook.cmeducation.util.BmobHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.bmob.v3.datatype.BmobFile;

public class CourseActivity extends Activity implements OnItemClickListener{

	private ListView listView;
	
	private List<String> fileNames;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_show);
		listView = (ListView)findViewById(R.id.showList);
		Intent intent = getIntent();
		fileNames = (List<String>)intent.getSerializableExtra("fileNames");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, fileNames);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, StudyActivity.class);
		intent.putExtra("fileName", fileNames.get(position));
		startActivity(intent);
	}

}
