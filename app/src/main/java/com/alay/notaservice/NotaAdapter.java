package com.alay.notaservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<com.alay.notaservice.NotaAdapter.ViewAdapter> {
    private List<Nota> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public NotaAdapter(Context context, List<Nota> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nota, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.nop.setText(list.get(position).hp);
        holder.jb.setText(list.get(position).jb);
        holder.kerusakan.setText(list.get(position).kerusakan);
        holder.teknisi.setText(list.get(position).teknisi);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView name, nop, jb, kerusakan, teknisi;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nop = itemView.findViewById(R.id.nop);
            jb = itemView.findViewById(R.id.jb);
            kerusakan = itemView.findViewById(R.id.kerusakan);
            teknisi = itemView.findViewById(R.id.teknisi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
