package com.example.webook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesFragment extends Fragment {
    private RecyclerView recyclerView;

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

        recyclerView = rootView.findViewById(R.id.recycler_viewNote);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    private void getTambahDataActivity(View.OnClickListener onClickListener) {
        Intent intent = new Intent(getActivity(), AddData.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
