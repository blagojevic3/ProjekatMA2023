package com.example.projekatma2023;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListaIgaraFragment extends Fragment {

    Button koZnaZnaBtn;
    Button spojniceBtn;
    Button asocijacijeBtn;
    Button skockoBtn;
    Button korakPoKorakBtn;
    Button mojBrojBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_igara, container, false);


        Button koZnaZnaBtn = view.findViewById(R.id.ko_zna_znaBtn);
        Button spojniceBtn = view.findViewById(R.id.spojniceBtn);
        Button asocijacijeBtn = view.findViewById(R.id.asocijacijeBtn);
        Button skockoBtn = view.findViewById(R.id.skockoBtn);
        Button korakPoKorakBtn = view.findViewById(R.id.korak_po_korakBtn);
        Button mojBrojBtn = view.findViewById(R.id.moj_brojBtn);

        koZnaZnaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment koZnaZnaFrag = new KoZnaZnaFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,koZnaZnaFrag).commit();
            }
        });

        spojniceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment spojniceFrag = new SpojniceFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,spojniceFrag).commit();
            }
        });

        asocijacijeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment asocijacijeFrag = new AsocijacijeFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,asocijacijeFrag).commit();
            }
        });

        skockoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment skockoFrag = new SkockoFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,skockoFrag).commit();
            }
        });

        korakPoKorakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment korakPoKorakFrag = new KorakPoKorak();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,korakPoKorakFrag).commit();
            }
        });
        mojBrojBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mojBrojFrag = new MojBrojFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,mojBrojFrag).commit();
            }
        });

        return view;
    }
}