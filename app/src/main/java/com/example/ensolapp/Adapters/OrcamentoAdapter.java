package com.example.ensolapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ensolapp.Models.Orcamento;
import com.example.ensolapp.R;

import java.util.ArrayList;

public class OrcamentoAdapter extends RecyclerView.Adapter<OrcamentoAdapter.OrcamentoItemHolder>{
    ArrayList<Orcamento> orcamentos;
    private onOrcamentoItemListenner onOrcamentoItemListenner;

    public OrcamentoAdapter(ArrayList<Orcamento> orcamentos, onOrcamentoItemListenner onOrcamentoItemListenner){
        this.orcamentos = orcamentos;
        this.onOrcamentoItemListenner = onOrcamentoItemListenner;
    }

    @NonNull
    @Override
    public OrcamentoItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orcamento_item,
                parent, false);
        return new OrcamentoItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrcamentoItemHolder holder, int position) {
        String nome_cliente = orcamentos.get(position).getNomeCliente();
        String endereco_cliente = orcamentos.get(position).getLocalizacao();

        holder.textView_nome.setText(nome_cliente);
        holder.textView_endereco.setText(endereco_cliente);
    }

    @Override
    public int getItemCount() {
        return orcamentos.size();
    }

    class OrcamentoItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView_nome, textView_endereco;

        public OrcamentoItemHolder(@NonNull View itemView) {
            super(itemView);

            textView_nome = itemView.findViewById(R.id.nome_cliente_orcamento_item);
            textView_endereco = itemView.findViewById(R.id.endereco_cliente_orcamento_item);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onOrcamentoItemListenner.onOrcamentoItemClick(getAdapterPosition());
        }
    }

    public interface onOrcamentoItemListenner {
        void onOrcamentoItemClick(int position);
    }
}
