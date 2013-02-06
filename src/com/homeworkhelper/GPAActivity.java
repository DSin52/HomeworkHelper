package com.homeworkhelper;

import java.text.DecimalFormat;

import com.homeworkhelper.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * This gets the gpa for the class. 
 */
public class GPAActivity extends Activity implements OnClickListener {
	TextView tvclass1;
	TextView tvclass2;
	TextView tvclass3;
	TextView tvclass4;
	TextView tvclass5;
	TextView tvclass6;
	
	EditText gradeclass1;
	EditText gradeclass2;
	EditText gradeclass3;
	EditText gradeclass4;
	EditText gradeclass5;
	EditText gradeclass6;
	
	boolean classPresent = false;
	int numClass;
	
	TextView numCredits;
	
	Button bCalc;
	
	TextView[] textArray;
	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpa);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		
		tvclass1 = (TextView) findViewById(R.id.tvclass1);
		tvclass2 = (TextView) findViewById(R.id.tvclass2);
		tvclass3 = (TextView) findViewById(R.id.tvclass3);
		tvclass4 = (TextView) findViewById(R.id.tvclass4);
		tvclass5 = (TextView) findViewById(R.id.tvclass5);
		tvclass6 = (TextView) findViewById(R.id.tvclass6);
		
		gradeclass1 = (EditText) findViewById(R.id.gradeclass1);
		gradeclass2 = (EditText) findViewById(R.id.gradeclass2);
		gradeclass3 = (EditText) findViewById(R.id.gradeclass3);
		gradeclass4 = (EditText) findViewById(R.id.gradeclass4);
		gradeclass5 = (EditText) findViewById(R.id.gradeclass5);
		gradeclass6 = (EditText) findViewById(R.id.gradeclass6);
		
		numCredits = (TextView) findViewById(R.id.numCredits);
		
		tvclass1.setText(settings.getString("Class 0", ""));
		tvclass2.setText(settings.getString("Class 1", ""));
		tvclass3.setText(settings.getString("Class 2", ""));
		tvclass4.setText(settings.getString("Class 3", ""));
		tvclass5.setText(settings.getString("Class 4", ""));
		tvclass6.setText(settings.getString("Class 5", ""));
		
		bCalc = (Button) findViewById(R.id.bCalc);
		bCalc.setOnClickListener(this);
		
		textArray = new TextView[6];
		textArray[0] = tvclass1;
		textArray[1] = tvclass2;
		textArray[2] = tvclass3;
		textArray[3] = tvclass4;
		textArray[4] = tvclass5;
		textArray[5] = tvclass6;
		
		EditText[] gradeClass = {gradeclass1, gradeclass2, gradeclass3, gradeclass4, gradeclass5, gradeclass6};
		
		for (int i = 0; i < textArray.length; i++){
			
			if (settings.getString("Class " + i, "").equals(""))
			{
				textArray[i].setVisibility(View.INVISIBLE);
				gradeClass[i].setVisibility(View.INVISIBLE);
			}
			else {
				textArray[i].setText(settings.getString("Class " + i, "failed"));
				numClass ++;
			}
			
		}
		
		if (numClass == 0)
		{
			bCalc.setVisibility(View.INVISIBLE);
		}
		


	}

	public void onClick(View v) {
		
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

		String[] grades = {gradeclass1.getText().toString(), gradeclass2.getText().toString(), 
				gradeclass3.getText().toString(), gradeclass4.getText().toString(), 
				gradeclass5.getText().toString(), gradeclass6.getText().toString()};
		int[] credits = new int[6];
		double qualPoints = 0.00;
		double credCount = 0.00;
		
		for (int i = 0; i < grades.length; i ++)
		{
			credits[i] = Integer.parseInt(settings.getString("Credit 0", ""));

			if (!grades[i].equals(""))
			{
				if (grades[i].equalsIgnoreCase("A"))
				{
					qualPoints += 4.00 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("A-"))
				{
					qualPoints += 3.70 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("B+"))
				{
					qualPoints += 3.30 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("B"))
				{
					qualPoints += 3.00 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("B-"))
				{
					qualPoints += 2.70 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("C+"))
				{
					qualPoints += 2.30 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("C"))
				{
					qualPoints += 2.00 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("C-"))
				{
					qualPoints += 1.70 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("D+"))
				{
					qualPoints += 1.30 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("D"))
				{
					qualPoints += 1.00 * credits[i];
				}
				else if (grades[i].equalsIgnoreCase("D-"))
				{
					qualPoints += 0.70 * credits[i];
				}
				else {
					qualPoints += 0.00;
				}
				credCount += credits[i];
			
			}
		}
		double actGpa = qualPoints/credCount;
		DecimalFormat df = new DecimalFormat("#.##");
		
		numCredits.setText("" + df.format(actGpa));
		

	}
	
	public void  setPresent(boolean guess)
	{
		classPresent = guess;
	}
	
	public boolean getPresent() {
		return classPresent;
	}


	
}
