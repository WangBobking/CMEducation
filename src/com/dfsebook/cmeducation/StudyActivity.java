package com.dfsebook.cmeducation;

import com.dfsebook.cmeducation.util.BmobHelper;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StudyActivity extends Activity implements TextWatcher{

	private ListView listView;
	
	private EditText search;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study);
		listView = (ListView)findViewById(R.id.study_show);
		TextView hind = (TextView)findViewById(R.id.hind);
		hind.requestFocus();
		search = (EditText)findViewById(R.id.search);
		search.addTextChangedListener(this);
		setTitles();
		
		BmobHelper bh = new BmobHelper();
		bh.getBmobFiles(this, listView);
	}

	private void setTitles() {		
			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.study, menu);
		return true;
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

}
