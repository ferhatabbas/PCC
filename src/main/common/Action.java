package main.common;

/**
 * Created by ferhat on 2015-11-12.
 */
public class Action {


    private String IDaction;
    private String Description;
    private int Valeur;
    private Status status;
    private Response reponse;

    public enum Status { ATTENTE,VALIDER,REFUSER,RIEN }
    public enum Response { YES, NO ,RIEN }
    public Action(String ID,String descr,int val,Status stat,Response rep){
        this.IDaction=ID;
        this.Description=descr;
        this.Valeur=val;
        this.status=stat;
        reponse=rep;
    }

    public String getIDaction() {
        return IDaction;
    }

    public void setIDaction(String IDaction) {
        this.IDaction = IDaction;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getValeur() {
        return Valeur;
    }

    public void setValeur(int valeur) {
        Valeur = valeur;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Response getReponse() {
        return reponse;
    }

    public void setReponse(Response reponse) {
        this.reponse = reponse;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;

        Action action = (Action) o;

        return !(getIDaction() != null ? !getIDaction().equals(action.getIDaction()) : action.getIDaction() != null);

    }

    @Override
    public int hashCode() {
        return IDaction.hashCode();
    }
}
