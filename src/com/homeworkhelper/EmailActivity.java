package com.homeworkhelper;

import com.homeworkhelper.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends Activity{

	EditText emailAdd, emailSubject, emailBody;
	Button emailButton;
	String emailSub, emailBod;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		
		emailAdd = (EditText) findViewById(R.id.emailAdd);
		emailSubject = (EditText) findViewById(R.id.emailSubject);
		emailBody = (EditText) findViewById(R.id.emailBody);
		
		emailButton = (Button) findViewById(R.id.emailButton);
		
		emailButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (!emailAdd.getText().toString().equals("") &&
						!emailSubject.getText().toString().equals("")
						&& !emailBody.getText().toString().equals(""))
				{
					String[] emailAddress = {emailAdd.getText().toString() };
					Intent send = new Intent(android.content.Intent.ACTION_SEND);
					send.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
					send.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject.getText().toString());
					send.setType("plain/text");
					send.putExtra(android.content.Intent.EXTRA_TEXT, emailBody.getText().toString());
				//	send.putExtra(android.content.Intent.ACTION_ATTACH_DATA, value)
					startActivity(send);
				}
			}
			
		});
	}
	
	

}
