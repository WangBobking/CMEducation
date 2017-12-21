package com.dfsebook.cmeducation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.dfsebook.cmeducation.util.FloatingActionButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FloatingActionButton rab = new  FloatingActionButton.Builder(this)
        .withDrawable(getResources().getDrawable(R.drawable.play))
        .withButtonColor(Color.CYAN)
        .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
        .withMargins(0, 0, 16, 16)
        .create();
		rab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				openOptionsMenu();				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.cme_h7n9) {
			Intent intent = new Intent(this,StudyActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
