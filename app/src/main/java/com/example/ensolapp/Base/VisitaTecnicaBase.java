package com.example.ensolapp.Base;

import com.example.ensolapp.Firebase.FirebaseService;
import com.example.ensolapp.Models.VisitaTecnica;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VisitaTecnicaBase {
    /*
       Acesso aos dados do firebase
    */
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID = FirebaseService.getFirebaseAuth().getCurrentUser().getUid();

    /*
       Estrutura de dados utilizada
    */
    private ArrayList<VisitaTecnica> visitaTecnica;
    private ArrayList<VisitaTecnica> visitaTecnicaPorDia;
    private ArrayList<String> visitaTecnicaId = new ArrayList<>();
    private ArrayList<String>  visitaTecnicaIdPorDia = new ArrayList<>();

    // Instância da classe singleton
    private static VisitaTecnicaBase mVisitaTecnicaBase;

    private VisitaTecnicaBase (){
        firebaseListenner();
    }

    /*
        Escuta por atualizações na base de dados
     */
    private void firebaseListenner() {
        db.collection("visitas_tecnicas").whereEqualTo("tecnicoId", userID).addSnapshotListener((value, error) -> {
            if (value != null) {
                visitaTecnica = null;
                visitaTecnica = new ArrayList<>();
                for (DocumentSnapshot document: value.getDocuments()) {
                    visitaTecnica.add(document.toObject(VisitaTecnica.class));
                    visitaTecnicaId.add(document.getId());
                }
            }
        });
    }

    /*
        Retorna a instância da classe
     */
    public static VisitaTecnicaBase getInstance(){
        if(mVisitaTecnicaBase == null){
            mVisitaTecnicaBase = new VisitaTecnicaBase();
        }
        return mVisitaTecnicaBase;
    }

    /*
        Retorna as visitasTecnicas
     */
    public ArrayList<VisitaTecnica> getVisitaTecnica(){
        return visitaTecnica;
    }

    /*
        Retorna as visitasTecnicas por dia
     */
    public ArrayList<VisitaTecnica> getVisitaTecnicaPorDia(){
        return visitaTecnicaPorDia;
    }

    /*
        Define as visitasTecnicas por dia
     */
    public void setVisitaTecnicaPorDia(ArrayList<VisitaTecnica> itemsByDay){
        this.visitaTecnicaPorDia = itemsByDay;
    }

    /*
        Reseta as visitasTecnicas por dia
     */
    public void resetarVisitasTecnicasPorDia(){
        this.visitaTecnicaPorDia = null;
    }

    /*
        Retorna os IDs das visitasTecnicas
     */
    public ArrayList<String> getVisitaTecnicaId(){
        return this.visitaTecnicaId;
    }

    /*
        Retorna os IDs das visitasTecnicas por dia
     */
    public ArrayList<String> getVisitaTecnicaIdPorDia(){
        return this.visitaTecnicaIdPorDia;
    }

    /*
        Define os IDs das visitas técnicas por dia
     */
    public void setVisitaTecnicaIdPorDia(ArrayList<String> visitaTecnicaIdPorDia){
        this.visitaTecnicaIdPorDia = visitaTecnicaIdPorDia;
    }

    /*
        Remove uma visita técnica do dia de determinada posição
     */
    public void removeVisitaTecnicaDoDia(int position) {
        this.visitaTecnicaPorDia.remove(position);
        this.visitaTecnicaIdPorDia.remove(position);
    }

    /*
        Limpa as visitas
     */
    public void LimparVisitaTecnica() {
        mVisitaTecnicaBase = null;
    }
}
