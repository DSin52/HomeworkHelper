package com.homeworkhelper;

import com.homeworkhelper.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClassActivity extends Activity implements OnClickListener {

	public static final String PREFS_NAME = "MyPrefsFile";

	EditText etclass1, etclass2, etclass3, etclass4, etclass5, etclass6;

	EditText etgpa1, etgpa2, etgpa3, etgpa4, etgpa5, etgpa6;

	TextView numCredits;
	ClassModel classModel;
	Button bSave;

	SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classes);

		//classModel = new ClassModel();

		etclass1 = (EditText) findViewById(R.id.etclass1);
		etclass2 = (EditText) findViewById(R.id.etclass2);
		etclass3 = (EditText) findViewById(R.id.etclass3);
		etclass4 = (EditText) findViewById(R.id.etclass4);
		etclass5 = (EditText) findViewById(R.id.etclass5);
		etclass6 = (EditText) findViewById(R.id.etclass6);

		numCredits = (TextView) findViewById(R.id.numCredits);

		EditText[] classArray = { etclass1, etclass2, etclass3, etclass4,
				etclass5, etclass6 };


		etgpa1 = (EditText) findViewById(R.id.etgpa1);
		etgpa2 = (EditText) findViewById(R.id.etgpa2);
		etgpa3 = (EditText) findViewById(R.id.etgpa3);
		etgpa4 = (EditText) findViewById(R.id.etgpa4);
		etgpa5 = (EditText) findViewById(R.id.etgpa5);
		etgpa6 = (EditText) findViewById(R.id.etgpa6);
		
		EditText[] creditArray = { etgpa1, etgpa2, etgpa3, etgpa4, etgpa5,
				etgpa6 };

		bSave = (Button) findViewById(R.id.bSave);
		settings = getSharedPreferences(PREFS_NAME, 0);

		if (settings.contains("Class 0")) {
			for (int i = 0; i < classArray.length; i++) {
				classArray[i].setText(settings.getString("Class " + i, ""));
				if (!settings.getString("Credit " + i, "").equals("")) {
					creditArray[i].setText("" + Integer.parseInt(settings.getString("Credit " + i, "")));
				}
			}
		}
		
		numCredits.setText("" + settings.getInt("Total", 0));

		bSave.setOnClickListener(this);

	}

	public void onClick(View v) {
		SharedPreferences.Editor editor = settings.edit();

		EditText[] classArray = { etclass1, etclass2, etclass3, etclass4,
				etclass5, etclass6 };
		EditText[] creditArray = { etgpa1, etgpa2, etgpa3, etgpa4, etgpa5,
				etgpa6 };

		int totalCredits = 0;

		for (int i = 0; i < classArray.length; i++) {
			Editable classes = classArray[i].getText();
			Editable credits = creditArray[i].getText();
			if (classes != null && credits != null) {
				String classesStr = classes.toString();
				String creditsStr = credits.toString();

				if (!classesStr.equals("") && !creditsStr.equals("")) {
					editor.putString("Class 0", classArray[0].getText().toString());
					editor.putString("Class 1", classArray[1].getText().toString());
					editor.putString("Class 2", classArray[2].getText().toString());
					editor.putString("Class 3", classArray[3].getText().toString());
					editor.putString("Class 4", classArray[4].getText().toString());
					editor.putString("Class 5", classArray[5].getText().toString());
					
					editor.putString("Credit 0", creditArray[0].getText().toString());
					editor.putString("Credit 1", creditArray[1].getText().toString());
					editor.putString("Credit 2", creditArray[2].getText().toString());
					editor.putString("Credit 3", creditArray[3].getText().toString());
					editor.putString("Credit 4", creditArray[4].getText().toString());
					editor.putString("Credit 5", creditArray[5].getText().toString());
					
					totalCredits += Integer.parseInt(creditsStr);

				}
			}

		}
		editor.putInt("Total", totalCredits);
		editor.commit();
		numCredits.setText("" + settings.getInt("Total", 0));

	}

}
