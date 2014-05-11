package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teambad.oneup.Challenge;
import com.teambad.oneup.HomeActivity;
import com.teambad.oneup.R;

public class FragmentMap extends Fragment implements OnMarkerClickListener{
	static final LatLng HERE = new LatLng(37.7799301, -122.3948669);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_map, null);
		GoogleMap map;
		
			
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		for(Challenge c : ((HomeActivity)getActivity()).challenges){
			this.addChallengeToMap(c, map);
		}
		map.addMarker(new MarkerOptions().position(HERE).title("CHALLENGE TITLE"));
		map.setOnMarkerClickListener(this);

		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HERE, 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		return root;
	}
	
	public void addChallengeToMap(Challenge challenge, GoogleMap map) {
		LatLng latlng = new LatLng(challenge.getLat(), challenge.getLng());
		map.addMarker(new MarkerOptions().position(latlng).title("CHALLENGE"));
		map.setOnMarkerClickListener(this);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		double markerPosLat = marker.getPosition().latitude;
		double markerPosLng = marker.getPosition().longitude;
		LatLng latlng = new LatLng(markerPosLat, markerPosLng);
		for(Challenge c : ((HomeActivity)getActivity()).challenges){
			LatLng clatlng = new LatLng(c.getLat(), c.getLng());
			if (Math.abs(clatlng.latitude- latlng.latitude) < 1.0) {
				((HomeActivity)getActivity()).loadChallengeFragment(c);
			}
		}
		return false;
	}
}
