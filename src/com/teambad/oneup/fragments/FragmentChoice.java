package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teambad.oneup.R;
import com.teambad.oneup.HomeActivity;
import com.teambad.oneup.HomeActivity.FragmentTypes;

public class FragmentChoice extends Fragment implements View.OnClickListener{
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_choice, null);
		((Button) root.findViewById(R.id.fragment_choice_map_btn)).setOnClickListener(this);
		((Button) root.findViewById(R.id.fragment_choice_event_btn)).setOnClickListener(this);
		return root;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.fragment_choice_map_btn:
			((HomeActivity)getActivity()).loadFragment(FragmentTypes.MAP);
			break;
		case R.id.fragment_choice_event_btn:
			((HomeActivity)getActivity()).loadFragment(FragmentTypes.CREATE);
			break;
		}
	}
}
