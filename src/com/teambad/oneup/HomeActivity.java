package com.teambad.oneup;

import com.teambad.oneup.fragments.FragmentCreateEvent;
import com.teambad.oneup.fragments.FragmentLogin;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HomeActivity extends Activity {
	
	FragmentLogin fragmentLogin = new FragmentLogin();
	FragmentCreateEvent fragmentCreateEvent = new FragmentCreateEvent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		this.getFragmentManager()
				.beginTransaction()
				.add(R.id.large_container, fragmentLogin,
						"login_fragment").commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
