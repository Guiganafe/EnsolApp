package com.example.ensolapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ensolapp.Models.VisitaTecnica;
import com.example.ensolapp.R;

import java.util.ArrayList;

public class VisitaTecnicaAdapter extends RecyclerView.Adapter<VisitaTecnicaAdapter.VisitaTecnicaItemHolder> {

    ArrayList<VisitaTecnica> visitaTecnicas;
    private onVisitaTecnicaItemListenner onVisitaTecnicaItemListenner;

    public VisitaTecnicaAdapter(ArrayList<VisitaTecnica> visitaTecnicas, onVisitaTecnicaItemListenner onVisitaTecnicaItemListenner){
        this.visitaTecnicas = visitaTecnicas;
        this.onVisitaTecnicaItemListenner = onVisitaTecnicaItemListenner;
    }

    @NonNull
    @Override
    public VisitaTecnicaItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visita_tecnica_item,
                parent, false);
        return new VisitaTecnicaItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitaTecnicaItemHolder holder, int position) {
        String nome_cliente = visitaTecnicas.get(position).getCliente().get("nomeCliente").toString();
        String endereco_cliente = visitaTecnicas.get(position).getCliente().get("endereco").toString();

        holder.textView_nome.setText(nome_cliente);
        holder.textView_endereco.setText(endereco_cliente);
    }

    @Override
    public int getItemCount() {
        return visitaTecnicas.size();
    }

    class VisitaTecnicaItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView_nome, textView_endereco;

        public VisitaTecnicaItemHolder(@NonNull View itemView) {
            super(itemView);

            textView_nome = itemView.findViewById(R.id.nome_cliente_item);
            textView_endereco = itemView.findViewById(R.id.endereco_cliente_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onVisitaTecnicaItemListenner.onVisitaTecnicaItemClick(getAdapterPosition());
        }
    }

    public interface onVisitaTecnicaItemListenner {
        void onVisitaTecnicaItemClick(int position);
    }
}
