package com.example.ensolapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ensolapp.Activity.LoginActivity;
import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.R;

public class PerfilFragment extends Fragment {

    private TextView tv_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes(view);
        onClickController();
    }

    private void onClickController() {
        tv_logout.setOnClickListener(v -> logout());
    }

    private void logout() {
        FirebaseService.logOut();
        Intent loginIntent = new Intent(requireActivity(), LoginActivity.class);
        startActivity(loginIntent);
        requireActivity().finish();
    }

    private void inicializarComponentes(View view) {
        tv_logout = view.findViewById(R.id.tv_logout);
    }
}