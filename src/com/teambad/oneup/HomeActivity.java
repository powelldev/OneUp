package com.teambad.oneup;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.teambad.oneup.fragments.FragmentChallenge;
import com.teambad.oneup.fragments.FragmentChoice;
import com.teambad.oneup.fragments.FragmentCreateEvent;
import com.teambad.oneup.fragments.FragmentLogin;
import com.teambad.oneup.fragments.FragmentMap;

public class HomeActivity extends YouTubeBaseActivity {

	FragmentLogin fragmentLogin = new FragmentLogin();
	FragmentCreateEvent fragmentCreateEvent = new FragmentCreateEvent();
	FragmentChallenge fragmentChallenge = new FragmentChallenge();
	FragmentMap fragmentMap = new FragmentMap();
	FragmentChoice fragmentChoice = new FragmentChoice();

	OneUpDb db = new OneUpDb();
	public ArrayList<Challenge> challenges;
	public ArrayList<UserId> users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		try {
			challenges = db.select();
			users = db.selectUser();
		} catch (Exception e) {
			throw new RuntimeException("DB SELECT HOME");
		}
		for (UserId u : users) {
			Toast.makeText(getApplicationContext(), u.userName,
					Toast.LENGTH_LONG).show();
		}
		fragmentChallenge.setChallenge(challenges.get(0));
		this.getFragmentManager().beginTransaction()
				.add(R.id.large_container, fragmentLogin, "login_fragment")
				.commit();

		// challenges = jdbc.connect();
		// users = jdbc.connect();
	}

	public static enum FragmentTypes {
		LOGIN, CREATE, CHALLENGE, CHOICE, MAP;
	}

	public void loadFragment(FragmentTypes fragType) {
		Fragment frag = null;
		FragmentTransaction trans = this.getFragmentManager()
				.beginTransaction();
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
		case CHOICE:
			frag = fragmentChoice;
			break;
		case MAP:
			frag = fragmentMap;
			break;
		}
		trans.replace(R.id.large_container, frag).commit();
	}

	public void loadChallengeFragment(double lat, double lng) {
		for (Challenge c : challenges) {
			if ((c.lat == lat) && (c.lng == lng)) {
				fragmentChallenge.setChallenge(c);
			}
		}
		getFragmentManager().beginTransaction()
				.replace(R.id.large_container, fragmentChallenge).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void loadChallengeFragment(Challenge c) {

		fragmentChallenge.setChallenge(c);
		getFragmentManager().beginTransaction()
				.replace(R.id.large_container, fragmentChallenge).commit();
	}

	public void updateChallenge(Challenge challenge, int rating) {
		double newRating = ((challenge.getRating() * challenge.getNumRatings()) + rating) / (challenge.getNumRatings()+1);
		challenge.setNumRatings(challenge.getNumRatings()+1);
		challenge.setRating(newRating);
		db.update(challenge);
	}
	public void createNewChallenge(String url) {
		Challenge challenge = new Challenge();
		challenge.setLat(37.7799301);
		challenge.setLng(-122.3948669);
		challenge.setUrl(url);
		challenge.setNumRatings(0);
		challenge.setRating(0);
		challenge.setUserid(users.get(0).userId);
		db.insert(challenge);
	}
}
