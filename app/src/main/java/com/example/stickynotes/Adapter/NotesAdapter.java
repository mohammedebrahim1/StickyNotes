package com.example.stickynotes.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.stickynotes.DB.Note;
import com.example.stickynotes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.BeanHolder> {


    private List<Note> mList;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnNoteItemClick onNoteItemClick;

    public NotesAdapter(List<Note> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.mList = list;
        this.context = context;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }

    public void submitList(List<Note> list) {
        mList.clear();
        mList = list;

    }

    @Override
    public BeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item, parent, false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: " + mList.get(position));
        holder.textViewTitle.setText(mList.get(position).getTitle());
        holder.textViewContent.setText(mList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnNoteItemClick {
        void onNoteClick(int pos);
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewContent;
        TextView textViewTitle;

        public BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            onNoteItemClick.onNoteClick(getAdapterPosition());
        }
    }

}
