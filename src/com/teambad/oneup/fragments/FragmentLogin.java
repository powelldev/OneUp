package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teambad.oneup.HomeActivity;
import com.teambad.oneup.HomeActivity.FragmentTypes;
import com.teambad.oneup.R;

public class FragmentLogin extends Fragment implements View.OnClickListener{
	
	EditText username;
	Button submitBtn;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_login, null);
		username = (EditText) root.findViewById(R.id.fragment_login_et);
		submitBtn = (Button) root.findViewById(R.id.fragment_login_submit_btn);
		submitBtn.setOnClickListener(this);
		return root;
	}


	@Override
	public void onClick(View v) {
		((HomeActivity) getActivity()).loadFragment(FragmentTypes.MAP);
	}

	class VerifyUser extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... usernameArray) {
			String username = usernameArray[0];
			// jdbc.getAllUsers();
			// if username in resultSet
			// start main menu
			return null;
		}
		
	}

}
