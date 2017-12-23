package com.dfsebook.cmeducation;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import cn.bmob.v3.datatype.BmobFile;

import com.dfsebook.cmeducation.util.FloatingActionButton;
import com.dfsebook.cmeducation.util.GetBmobFiles;
import com.dfsebook.cmeducation.util.GetBmobFiles.BmobFileListener;

public class MainActivity extends Activity implements BmobFileListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GetBmobFiles gf = new GetBmobFiles();
		gf.setBmobFileListener(this);
		gf.getFiles();
	}


	@Override
	public void onGetFile(List<BmobFile> bmobFiles) {
		Intent intent = new Intent(this, StudyActivity.class);
		intent.putExtra("bmobFiles", (Serializable)bmobFiles);
		startActivity(intent);
	}
}
