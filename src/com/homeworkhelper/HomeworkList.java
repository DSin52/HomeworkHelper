package com.homeworkhelper;

import com.homeworkhelper.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class HomeworkList extends Activity {

	CheckBox hwTog1, hwTog2, hwTog3, hwTog4, hwTog5, hwTog6;

	TextView hw1, hw2, hw3, hw4, hw5, hw6, hwTitle;

	public static final String PREFS_NAME = "MyPrefsFile";

	private int classNum = 0;

	Button bHw;

	EditText hwEntry;

	SharedPreferences settings;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.homework);

		hwTog1 = (CheckBox) findViewById(R.id.hwTog1);
		hwTog2 = (CheckBox) findViewById(R.id.hwTog2);
		hwTog3 = (CheckBox) findViewById(R.id.hwTog3);
		hwTog4 = (CheckBox) findViewById(R.id.hwTog4);
		hwTog5 = (CheckBox) findViewById(R.id.hwTog5);
		hwTog6 = (CheckBox) findViewById(R.id.hwTog6);
	

		hw1 = (TextView) findViewById(R.id.hw1);
		hw2 = (TextView) findViewById(R.id.hw2);
		hw3 = (TextView) findViewById(R.id.hw3);
		hw4 = (TextView) findViewById(R.id.hw4);
		hw5 = (TextView) findViewById(R.id.hw5);
		hw6 = (TextView) findViewById(R.id.hw6);
		hwTitle = (TextView)  findViewById(R.id.hwTitle);


		settings = getSharedPreferences(PREFS_NAME, 0);

		editor = settings.edit();
		
		hwTitle.setText(settings.getString("Class", ""));
		
		TextView[] hwArray = { hw1, hw2, hw3, hw4, hw5, hw6 };

		CheckBox[] hwTogArray = { hwTog1, hwTog2, hwTog3, hwTog4, hwTog5,
				hwTog6 };
		classNum = settings.getInt("Position", 0);
		for (int i = 0; i < hwArray.length; i++) {
			String hwInfo = settings.getString("s" + classNum + i, "");
			if (!hwInfo.equals("") && !hwInfo.equals("Enter Assignment")) {
				hwArray[i].setText(hwInfo);
				hwTogArray[i].setChecked(settings.getBoolean("b" + classNum
						+ "i", false));

				hwArray[i].setVisibility(TextView.VISIBLE);
				hwTogArray[i].setVisibility(TextView.VISIBLE);

			} else {
				hwArray[i].setVisibility(TextView.INVISIBLE);
				hwTogArray[i].setVisibility(TextView.INVISIBLE);
			}
		}
		
		for (int i = 0; i < hwTogArray.length; i ++)
		{
			hwTogArray[i].setId(i);
			hwTogArray[i].setOnCheckedChangeListener(new CheckListener());
		}
		

		bHw = (Button) findViewById(R.id.bHw);

		hwEntry = (EditText) findViewById(R.id.hwEntry);

		bHw.setOnClickListener(new View.OnClickListener() {

			TextView[] hwArray = { hw1, hw2, hw3, hw4, hw5, hw6 };

			CheckBox[] hwTogArray = { hwTog1, hwTog2, hwTog3, hwTog4, hwTog5,
					hwTog6 };
			public void onClick(View v) {
				for (int i = 0; i < hwArray.length; i++) {
					TextView hw = hwArray[i];
					
					if (!hw.isShown())
					{
						hw.setText(hwEntry.getText().toString());
						hwEntry.setText("");
						hw.setVisibility(TextView.VISIBLE);
						hwTogArray[i].setVisibility(CheckBox.VISIBLE);
						
						editor.putString("s" + classNum + i, hw.getText().toString());
						
						editor.commit();
						break;
					}
				}
			}

		});


	}
		
	private class CheckListener implements OnCheckedChangeListener {

		public void onCheckedChanged(final CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked)
			{
				DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						switch(which) {
						case DialogInterface.BUTTON_NEGATIVE: 
							buttonView.setChecked(false);
							break;
						case DialogInterface.BUTTON_POSITIVE:
							for (int i = buttonView.getId(); i < 6; i ++)
							{
								editor.putString("s" + classNum + (i), settings.getString("s" + classNum + (i + 1), ""));
							}
							editor.commit();
							finish();
							startActivity(getIntent());
							break;
						}
					}
				};
				
				AlertDialog.Builder builder = new AlertDialog.Builder(buttonView.getContext());
				builder.setMessage("Are you sure you want to remove this task?")
				.setPositiveButton("Yes", dialog).setNegativeButton("No", dialog).show();
			}
		}
		
	}
}
