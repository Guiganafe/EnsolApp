package com.example.ensolapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ensolapp.Models.Entrega;
import com.example.ensolapp.R;

import java.util.ArrayList;

public class EntregasAdapter extends RecyclerView.Adapter<EntregasAdapter.EntregaItemHolder>{

    ArrayList<Entrega> entregas;
    private onEntregaItemListenner onEntregaItemListenner;

    public EntregasAdapter(ArrayList<Entrega> entregas, onEntregaItemListenner onEntregaItemListenner){
        this.entregas = entregas;
        this.onEntregaItemListenner = onEntregaItemListenner;
    }

    @NonNull
    @Override
    public EntregaItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entrega_item,
                parent, false);
        return new EntregaItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EntregaItemHolder holder, int position) {
        String nome_cliente = entregas.get(position).getCliente().get("nomeCliente").toString();
        String endereco_cliente = entregas.get(position).getCliente().get("endereco").toString();

        holder.textView_nome.setText(nome_cliente);
        holder.textView_endereco.setText(endereco_cliente);
    }

    @Override
    public int getItemCount() {
        return entregas.size();
    }

    class EntregaItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView_nome, textView_endereco;

        public EntregaItemHolder(@NonNull View itemView) {
            super(itemView);

            textView_nome = itemView.findViewById(R.id.nome_cliente_entrega_item);
            textView_endereco = itemView.findViewById(R.id.endereco_cliente_entrega_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onEntregaItemListenner.onEntregaItemClick(getAdapterPosition());
        }
    }

    public interface onEntregaItemListenner {
        void onEntregaItemClick(int position);
    }
}
