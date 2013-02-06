package com.homeworkhelper;

import com.homeworkhelper.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AgendaActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				switch (position) {
				case (0):
					Intent myIntent4 = new Intent(v.getContext(),
							EmailActivity.class);
				startActivity(myIntent4);
				    break;
				case (1):
					Intent myIntent = new Intent(v.getContext(),
							ClassActivity.class);
					startActivity(myIntent);
					break;
				case (2):
					Intent myIntent2 = new Intent(v.getContext(),
							GPAActivity.class);
					startActivity(myIntent2);

					break;
                case (3):
                    Intent myIntent3 = new Intent(v.getContext(),
                        HomeworkMenu.class);
                    startActivity(myIntent3);
                    break;
				}
			}
		});
	}
}