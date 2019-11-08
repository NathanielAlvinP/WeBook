package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        FloatingActionButton tambahData = rootView.findViewById(R.id.floatingButtonAdd);
        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTambahDataActivity(this);
            }
        });

        return rootView;
    }

    private void getTambahDataActivity(View.OnClickListener onClickListener) {
        Intent intent = new Intent(getActivity(), AddData.class);
        startActivity(intent);
    }
}
