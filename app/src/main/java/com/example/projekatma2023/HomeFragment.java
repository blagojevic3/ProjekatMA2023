package com.example.projekatma2023;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    Button listaIgaraBtn;
    Activity context;
    Button singlePlayerBtn;
    Activity singlePlayerActivity = new SinglePlayerActivity();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context=getActivity();

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        listaIgaraBtn = view.findViewById(R.id.lista_igaraBtn);
        singlePlayerBtn = view.findViewById(R.id.samostalna_igraBtn);

        singlePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),SinglePlayerActivity.class);
                startActivity(i);
            }
        });


        listaIgaraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment listaIgaraFragment = new ListaIgaraFragment();

                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,listaIgaraFragment).commit();
            }
        });
        return view;



    }
}