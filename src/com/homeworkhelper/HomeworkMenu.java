package com.homeworkhelper;

import java.util.Arrays;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeworkMenu extends ListActivity {

	public static final String PREFS_NAME = "MyPrefsFile";
	private SharedPreferences settings;
	private String[] classPos = new String[0];
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		SharedPreferences.Editor editor = settings.edit();
		String options = classPos[position];
		editor.putInt("Position", position);
		editor.putString("Class", options);
		editor.commit();
		Intent hwIntent = new Intent(v.getContext(), HomeworkList.class);
		startActivity(hwIntent);

	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		settings = getSharedPreferences(PREFS_NAME, 0);
		for (int i = 0; i < 6; i ++)
		{
			String classString = settings.getString("Class " + i, "");
			if (!classString.equals(""))
			{
				String[] newClasses = new String[i + 1];
				newClasses = Arrays.copyOf(classPos, i + 1);
				newClasses[i] = classString;
				classPos = newClasses;
			}
		}
		
		setListAdapter(new ArrayAdapter<String>(HomeworkMenu.this, android.R.layout.simple_list_item_1, classPos));
	}
	
}
	
	