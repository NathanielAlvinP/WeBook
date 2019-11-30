package com.example.webook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerViewAdapterToDo extends RecyclerView.Adapter<RecyclerViewAdapterToDo.ViewHolder> {
    private List<ToDo> toDos;
    private FirebaseFirestore firestore;

    public RecyclerViewAdapterToDo(List<ToDo> toDos){
        this.toDos = toDos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_todo_recycler,
                parent,false);
        firestore = FirebaseFirestore.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.todo.setText(toDos.get(position).getTodo());
    }

    @Override
    public int getItemCount() {
        return toDos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView todo;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            todo = itemView.findViewById(R.id.todoList);
        }
    }
}
