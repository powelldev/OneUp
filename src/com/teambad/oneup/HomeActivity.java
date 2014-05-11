package com.teambad.oneup;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

import com.teambad.oneup.fragments.FragmentChallenge;
import com.teambad.oneup.fragments.FragmentCreateEvent;
import com.teambad.oneup.fragments.FragmentLogin;
import com.teambad.oneup.fragments.FragmentMap;

public class HomeActivity extends Activity {

	FragmentLogin fragmentLogin = new FragmentLogin();
	FragmentCreateEvent fragmentCreateEvent = new FragmentCreateEvent();
	FragmentChallenge fragmentChallenge = new FragmentChallenge();
	FragmentMap fragmentMap = new FragmentMap();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		this.getFragmentManager().beginTransaction()
				.add(R.id.large_container, fragmentLogin, "login_fragment")
				.commit();

		// challenges = jdbc.connect();
		// users = jdbc.connect();
	}
	
	public static enum FragmentTypes {
		LOGIN, CREATE, CHALLENGE, MAP;
	}
	public void loadFragment(FragmentTypes fragType) {
		Fragment frag = null;
		FragmentTransaction trans = this.getFragmentManager().beginTransaction();
		switch (fragType) {
		case LOGIN:
			frag = fragmentLogin;
			break;
		case CREATE:
			frag = fragmentCreateEvent;
			break;
		case CHALLENGE:
			frag = fragmentChallenge;
			break;
		case MAP:
			frag = fragmentMap;
			break;
		}
		trans.replace(R.id.large_container, frag).commit();
	}
	
	public void loadChallengeFragment(double lat, double lng) {
		// search
		// fragmentChallenge.setChallenge()
		// getFragmentManager().beginTransaction().replace(R.id.large_container, fragmentChallenge).commit(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
