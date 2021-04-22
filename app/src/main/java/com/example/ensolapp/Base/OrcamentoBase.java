package com.example.ensolapp.Base;

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Orcamento;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OrcamentoBase {
    /*
     Acesso aos dados do firebase
  */
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String tecnicoId = FirebaseService.getFirebaseAuth().getCurrentUser().getUid();

    /*
       Estrutura de dados utilizada
    */
    private ArrayList<Orcamento> orcamentos;
    private ArrayList<Orcamento> orcamentosPorDia;
    private ArrayList<String> orcamentosId = new ArrayList<>();
    private ArrayList<String>  orcamentosPorDiaId = new ArrayList<>();

    // Instância da classe singleton
    private static OrcamentoBase mOrcamentosBase;

    private OrcamentoBase (){
        db.collection("orcamentos").whereEqualTo("tecnicoId", tecnicoId).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots != null){
                orcamentos = null;
                orcamentos = new ArrayList<>();
                for (DocumentSnapshot document: queryDocumentSnapshots.getDocuments()) {
                    orcamentos.add(document.toObject(Orcamento.class));
                    orcamentosId.add(document.getId());
                }
            }
        });

        firebaseListenner();
    }

    /*
        Escuta por atualizações na base de dados
     */
    private void firebaseListenner() {
        db.collection("orcamentos").whereEqualTo("tecnicoId", tecnicoId).addSnapshotListener((value, error) -> {
            if (value != null) {
                orcamentos = null;
                orcamentos = new ArrayList<>();
                for (DocumentSnapshot document: value.getDocuments()) {
                    orcamentos.add(document.toObject(Orcamento.class));
                    orcamentosId.add(document.getId());
                }
            }
        });
    }

    /*
       Retorna a instância da classe
    */
    public static OrcamentoBase getInstance(){
        if(mOrcamentosBase == null){
            mOrcamentosBase = new OrcamentoBase();
        }
        return mOrcamentosBase;
    }

    /*
        Retorna os orcamentos
     */
    public ArrayList<Orcamento> getOrcamentos(){
        return orcamentos;
    }

    /*
        Retorna os orcamentos por dia
     */
    public ArrayList<Orcamento> getOrcamentosPorDia(){
        return orcamentosPorDia;
    }

    /*
        Define os orcamentos por dia
     */
    public void setOrcamentosPorDia(ArrayList<Orcamento> itemsByDay){
        this.orcamentosPorDia = itemsByDay;
    }

    /*
        Reseta os orcamentos por dia
     */
    public void resetarOrcamentosPorDia(){
        this.orcamentosPorDia = null;
    }

    /*
        Retorna os IDs dos orcamentos
     */
    public ArrayList<String> getOrcamentosId(){
        return this.orcamentosId;
    }

    /*
        Retorna os IDs dos orcamentos por dia
     */
    public ArrayList<String> getOrcamentosPorDiaId(){
        return this.orcamentosPorDiaId;
    }

    /*
        Define os IDs dos orcamentos por dia
     */
    public void setOrcamentosPorDiaId(ArrayList<String> orcamentosPorDiaId){
        this.orcamentosPorDiaId = orcamentosPorDiaId;
    }

    /*
        Remove uma entrega do dia de determinada posição
     */
    public void removeOrcamentosDoDia(int position) {
        this.orcamentosPorDia.remove(position);
        this.orcamentosPorDiaId.remove(position);
    }

    /*
        Limpa as entregas
     */
    public void LimparOrcamentos() {
        mOrcamentosBase = null;
    }
}
