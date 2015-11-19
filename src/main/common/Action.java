package main.common;

/**
 * Created by ferhat on 2015-11-12.
 */
public class Action {


    private String IDaction;
    private String Description;
    private int Valeur;


    public Action(String ID,String descr,int val){
        this.IDaction=ID;
        this.Description=descr;
        this.Valeur=val;
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
