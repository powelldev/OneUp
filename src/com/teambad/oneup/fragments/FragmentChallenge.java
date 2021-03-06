package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dolby.dap.DolbyAudioProcessing;
import com.dolby.dap.DolbyAudioProcessing.PROFILE;
import com.dolby.dap.OnDolbyAudioProcessingEventListener;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.teambad.oneup.Challenge;
import com.teambad.oneup.HomeActivity;
import com.teambad.oneup.HomeActivity.FragmentTypes;
import com.teambad.oneup.R;

public class FragmentChallenge extends Fragment implements
		OnInitializedListener, OnClickListener {
	Challenge challenge;

	DolbyAudioProcessing dap;

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
		
		((Button) root.findViewById(R.id.fragment_challenge_oneup_btn)).setOnClickListener(this);

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
			dap = DolbyAudioProcessing.getDolbyAudioProcessing(getActivity(), DolbyAudioProcessing.PROFILE.MOVIE, new OnDolbyAudioProcessingEventListener() {

			@Override
			public void onDolbyAudioProcessingClientConnected() {
				dap.setEnabled(true);
			}

			@Override
			public void onDolbyAudioProcessingClientDisconnected() {
				dap.setEnabled(false);
			}

			@Override
			public void onDolbyAudioProcessingEnabled(boolean arg0) {
			}

			@Override
			public void onDolbyAudioProcessingProfileSelected(PROFILE arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		player.cueVideo(parseVideoIdFromUrl());
		player.play();
	}
	
	private String parseVideoIdFromUrl() {
		String url = challenge.getUrl();
		String id = (String) url.subSequence(url.lastIndexOf("=")+1, url.length());
		if (id.contains("/")) {
			id = (String) url.subSequence(url.lastIndexOf("/")+1, url.length());
		}
		Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();
		return id;
	}

	@Override
	public void onClick(View v) {
		((HomeActivity) getActivity()).loadFragment(FragmentTypes.CREATE);
	}
}
