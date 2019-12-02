package com.example.notes;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class TodoRecyclerAdapter extends FirestoreRecyclerAdapter<ToDo, TodoRecyclerAdapter.TodoViewHolder> {

    TodoListener todoListener;
    private static final String TAG = "TodoRecyclerAdapter";

    public TodoRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ToDo> options, TodoListener todoListener) {
        super(options);
        this.todoListener = todoListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull TodoViewHolder holder, int position, @NonNull ToDo model) {
        holder.todoTask.setText(model.getText());
        CharSequence charSequenceDate = DateFormat.format("h:mm:ss a", model.getCreatedDate().toDate());
        holder.todoDate.setText(charSequenceDate);

    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.todo_row, parent, false);
        return new TodoRecyclerAdapter.TodoViewHolder(view);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView todoTask;
        TextView todoDate;
        CheckBox checkBox;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoTask = itemView.findViewById(R.id.todoTask);
            todoDate = itemView.findViewById(R.id.todoDate);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DocumentSnapshot snapshot = getSnapshots().getSnapshot(getAdapterPosition());
                    ToDo toDo = getItem(getAdapterPosition());
                    if (toDo.isCompletedTask() == isChecked) {
                        todoListener.handleCheckChanged(isChecked, snapshot);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocumentSnapshot snapshot = getSnapshots().getSnapshot(getAdapterPosition());
                    todoListener.handleEditTodo(snapshot);
                }
            });
        }
        public void deleteItem() {
            todoListener.handleDeleteItem(getSnapshots().getSnapshot(getAdapterPosition()));
        }
    }

    interface TodoListener {
        void handleCheckChanged(boolean isChecked, DocumentSnapshot snapshot);

        void handleEditTodo(DocumentSnapshot snapshot);

        void handleDeleteItem(DocumentSnapshot snapshot);
    }
}
