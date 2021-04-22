package com.example.ensolapp.Base;

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.Entrega;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EntregaBase {
    /*
      Acesso aos dados do firebase
   */
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String tecnicoId = FirebaseService.getFirebaseAuth().getCurrentUser().getUid();

    /*
       Estrutura de dados utilizada
    */
    private ArrayList<Entrega> entregas;
    private ArrayList<Entrega> entregasPorDia;
    private ArrayList<String> entregasId = new ArrayList<>();
    private ArrayList<String>  entregasPorDiaId = new ArrayList<>();

    // Instância da classe singleton
    private static EntregaBase mEntregaBase;

    private EntregaBase (){
        db.collection("entregas").whereEqualTo("tecnicoId", tecnicoId).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots != null){
                entregas = null;
                entregas = new ArrayList<>();
                for (DocumentSnapshot document: queryDocumentSnapshots.getDocuments()) {
                    entregas.add(document.toObject(Entrega.class));
                    entregasId.add(document.getId());
                }
            }
        });

        firebaseListenner();
    }

    /*
       Escuta por atualizações na base de dados
    */
    private void firebaseListenner() {
        db.collection("entregas").whereEqualTo("tecnicoId", tecnicoId).addSnapshotListener((value, error) -> {
            if (value != null) {
                entregas = null;
                entregas = new ArrayList<>();
                for (DocumentSnapshot document: value.getDocuments()) {
                    entregas.add(document.toObject(Entrega.class));
                    entregasId.add(document.getId());
                }
            }
        });
    }

    /*
       Retorna a instância da classe
    */
    public static EntregaBase getInstance(){
        if(mEntregaBase == null){
            mEntregaBase = new EntregaBase();
        }
        return mEntregaBase;
    }

    /*
        Retorna as entregas
     */
    public ArrayList<Entrega> getEntregas(){
        return entregas;
    }

    /*
        Retorna as entregas por dia
     */
    public ArrayList<Entrega> getEntregasPorDia(){
        return entregasPorDia;
    }

    /*
        Define as entregas por dia
     */
    public void setEntregasPorDia(ArrayList<Entrega> itemsByDay){
        this.entregasPorDia = itemsByDay;
    }

    /*
        Reseta as entregas por dia
     */
    public void resetarEntregasPorDia(){
        this.entregasPorDia = null;
    }

    /*
        Retorna os IDs das entregas
     */
    public ArrayList<String> getEntregasId(){
        return this.entregasId;
    }

    /*
        Retorna os IDs das entregas por dia
     */
    public ArrayList<String> getEntregasPorDiaId(){
        return this.entregasPorDiaId;
    }

    /*
        Define os IDs das entregas por dia
     */
    public void setEntregasPorDiaId(ArrayList<String> entregasPorDiaId){
        this.entregasPorDiaId = entregasPorDiaId;
    }

    /*
        Remove uma entrega do dia de determinada posição
     */
    public void removeEntregasDoDia(int position) {
        this.entregasPorDia.remove(position);
        this.entregasPorDiaId.remove(position);
    }

    /*
        Limpa as entregas
     */
    public void LimparEntregas() {
        mEntregaBase = null;
    }

}
