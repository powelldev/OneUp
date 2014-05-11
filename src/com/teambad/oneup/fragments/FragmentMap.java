package com.teambad.oneup.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teambad.oneup.R;

public class FragmentMap extends Fragment implements OnMarkerClickListener{
	static final LatLng ANTIOCH = new LatLng(28.0050, 121.8058);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_map, null);
		GoogleMap map;
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		map.addMarker(new MarkerOptions().position(ANTIOCH).title("CHALLENGE TITLE"));
		map.setOnMarkerClickListener(this);

		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(ANTIOCH, 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		return root;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		double markerPosLat = marker.getPosition().latitude;
		double markerPosLng = marker.getPosition().longitude;
		// find
		return false;
	}
}
