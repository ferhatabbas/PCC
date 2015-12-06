package common;

/**
 * Created by ferhat on 2015-11-12.
 */
public class Action {


    private String idaction;
    private String description;
    private int valeur;


    public Action(String ID,String descr,int val){
        this.idaction=ID;
        this.description=descr;
        this.valeur=val;
    }

    public String getIDaction() {
        return idaction;
    }

    public void setIDaction(String IDaction) {
        this.idaction = IDaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        valeur = valeur;
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
        return idaction.hashCode();
    }
}
