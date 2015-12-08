package common;

import java.util.Date;
import java.util.List;
/**
 * Created by fy on 2015-12-07.
 */
public interface PPCReqHandlerInterface {
    public Couple connection(String login, String pass);

    //commentaire pour sylvain
    //pour toutes les fonctions de cette interface, il y a un code= Subject
    // le server attend de recevoir un objet a chaque requete .
    // Ce objet sera une liste sil y a plus d'un parametre
    // cette liste contiendra toujours les parametres dans le meme ordre que
    // les fonctions ci dessous
    // cela veut dire que si le server fait une requete avec Subject = ADD_ACTION_R
    // il enverra un objet que tu vas caster en ActionReal
    // et le server s'atend a rien recevoir(void)


    // Abbas, j'ai ajouter de modif dans tes classes
    //puis, maxime ta dis de corriger le equals de User et le faire plutot avec des id
    // tu ne la pas fait
    //why rien comme status? normalement a la creation status = attente
    // tu dois corriger tes fonctions comme ceci sil te plait
    // il serait necessaire de tester tes fonctions avec de petit main
    // pour voire si c'est le bon resultat
    // si non on aura la misere pour que client et server communiquent

    //Creer un nouveau couple et l'ajouter à la liste des couples
    public Couple addCouple(User patner1, User patner2) ;

    //liste des actions total de la BD
    public List<Action> getActions();

    //Ajouter ar a la liste des action realier du couple
    // renommer createAR par addActionReal et considerer ce nouveau parametre
    public void addActionReal(ActionReal AR);

    // modifier une action realiser quand levaluer ajoute un comment et change le statut
    // si le statut est accepter, il faut mettre a jour le nombre de point de levaluer
    public void updateAR(String id, String comment, ActionReal.Status statut);

    //Chercher la liste des action realiser(historique) dun couple donnée pendant une periode donnée
    public List<ActionReal> getHistorique( Couple couple, Date from, Date to);

}

