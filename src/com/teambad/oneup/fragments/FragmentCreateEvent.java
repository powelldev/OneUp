package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teambad.oneup.R;

public class FragmentCreateEvent extends Fragment{

	EditText urlEditText;
	Button submitUrlBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_create_event, null);
		urlEditText = (EditText) root.findViewById(R.id.fragment_create_event_url);
		submitUrlBtn = (Button) root.findViewById(R.id.fragment_create_event_submit);
		return root;
	}

}
