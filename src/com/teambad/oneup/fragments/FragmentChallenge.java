package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.teambad.oneup.Challenge;
import com.teambad.oneup.R;

public class FragmentChallenge extends Fragment implements
		OnInitializedListener {
	Challenge challenge;

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
		try {
			youtube.initialize("AIzaSyDnMV8eHcCCPni5zdxKhsDWv41e332sAv0", this);
		} catch (Exception e) {
			// premature initialization
		}
	}

	YouTubePlayerView youtube;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_challenge, null);
		// if challenge.url contains youtube:
		youtube = (YouTubePlayerView) root
				.findViewById(R.id.fragment_challenge_video_view);

		try {
			youtube.initialize("AIzaSyDnMV8eHcCCPni5zdxKhsDWv41e332sAv0", this);
		} catch (Exception e) {
			// premature initialization
		}
		// else:

		return root;
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitializationSuccess(Provider provider,
			YouTubePlayer player, boolean arg2) {
		player.cueVideo(parseVideoIdFromUrl());
		player.play();
	}
	
	private String parseVideoIdFromUrl() {
		String url = challenge.getUrl();
		String id = (String) url.subSequence(url.lastIndexOf("=")+1, url.length());
		return id;
	}
}
