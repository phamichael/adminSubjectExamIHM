package facade;


import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import facade.erreurs.IndividuNonConnecteException;
import facade.erreurs.RoleDejaAttribueException;
import facade.erreurs.UtilisateurDejaExistantException;
import modele.personnes.Client;
import modele.personnes.Personne;
import modele.personnes.Utilisateur;
import modele.produit.CD;
import modele.produit.Livre;
import modele.produit.Produit;
import modele.produit.ProduitQuantite;
import modele.stock.StockProduits;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static facade.securite.AccessControl.checkAnnotationAccess;

public class ServiceImpl implements AdminService, ConnexionService {
    public static String ADMIN = "ADMIN";
    public static String VENDEUR = "VENDEUR";
    public static String RESPONSABLESTOCK = "RESPONSABLESTOCK";
    private static StockProduits stockProduits = new StockProduits();
    private static Map<Long,Produit> catalogue = new HashMap<>();
    private static Map<Long,Client> clients = new HashMap<>();
    private static Map<Long,Utilisateur> utilisateurs = new HashMap<>();
    private static Collection<Long> idsConnectes = new ArrayList<>();



    public ServiceImpl() {
        // ajout d'utilisateurs à la base initiale
        Utilisateur admin = new Utilisateur("admin","admin");
        Utilisateur vendeur = new Utilisateur("vendeur","vendeur");
        Utilisateur stock1 = new Utilisateur("stock","stock");
        try {
            admin.addRole(ADMIN);
            admin.addRole(VENDEUR);
            admin.addRole(RESPONSABLESTOCK);
            vendeur.addRole(VENDEUR);
            stock1.addRole(RESPONSABLESTOCK);
        } catch (RoleDejaAttribueException e) {
            e.printStackTrace();
        }





        utilisateurs.put(admin.getIdentifiant(),admin);
        utilisateurs.put(vendeur.getIdentifiant(),vendeur);
        utilisateurs.put(stock1.getIdentifiant(),stock1);

        // ajout de quelques clients
        Client yohan = new Client("boichut","yohan","orleans",45100,"ORLEANS",LocalDate.now());
        clients.put(yohan.getIdentifiant(),yohan);
        Client fred = new Client("moal","frederic","orleans",45100,"ORLEANS",LocalDate.now());
        clients.put(fred.getIdentifiant(),fred);

        Livre jfx = new Livre("JavaFX", 45.99, "123456-45", 512, "Oracle press", LocalDate.parse("2014-01-03"));
        catalogue.put(jfx.getId(),jfx);
        Livre jfx2 = new Livre("JavaFX2", 48.99, "123456-33", 542, "Oracle press", LocalDate.parse("2016-01-03"));
        catalogue.put(jfx2.getId(),jfx2);
        CD cd = new CD("Greatest Hits", 12.99, "Leonard Cohen");
        catalogue.put(cd.getId(),cd);
        stockProduits.addStock(jfx, 4);
        stockProduits.addStock(jfx2, 9);
        stockProduits.addStock(cd, 2);

        yohan.addAchats(new ProduitQuantite(jfx,1));
        fred.addAchats(new ProduitQuantite(jfx,1));
        fred.addAchats(new ProduitQuantite(jfx2,2));
        fred.addAchats(new ProduitQuantite(cd,1));
    }


    @Override
    public Personne creerUtilisateur(long idU, String nom, String mdp) throws IndividuNonConnecteException,UtilisateurDejaExistantException {
        if (idsConnectes.contains(idU)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(idU));

            long existe = utilisateurs.values().stream().filter(u -> u.getNom().equals(nom)).count();
            if (existe>0) throw new UtilisateurDejaExistantException();

            Utilisateur nouvelUtilisateur = new Utilisateur(nom, mdp);
            utilisateurs.put(nouvelUtilisateur.getIdentifiant(), nouvelUtilisateur);
            return nouvelUtilisateur;
        }
        else {
            throw new IndividuNonConnecteException();
        }

    }

    @Override
    public void associerRoleUtilisateur(long u, long utilisateurConcerne, String role) throws RoleDejaAttribueException {
        if (idsConnectes.contains(u)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(u));
            Utilisateur user = utilisateurs.get(utilisateurConcerne);
            user.addRole(role);
        }
        else {
            throw new IndividuNonConnecteException();
        }


    }

    @Override
    public void supprimerClient(long u, long utilisateurConcerne) {
        if (idsConnectes.contains(u)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(u));
            utilisateurs.remove(utilisateurConcerne);
        }
        else {
            throw new IndividuNonConnecteException();
        }


    }

    @Override
    public Collection<Utilisateur> getListeUtilisateur(long idDemandeur) {
        if (idsConnectes.contains(idDemandeur)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(idDemandeur));
            return utilisateurs.values();
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Utilisateur getUserById(long identifiant, long identifiant1) {
        if (idsConnectes.contains(identifiant)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(identifiant));
            return utilisateurs.get(identifiant1);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void supprimerRoleUtilisateur(long identifiant, long identifiant1, String role) {
        if (idsConnectes.contains(identifiant)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(identifiant));
            utilisateurs.get(identifiant1).supprimerRole(role);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public void changerMotDePasseUtilisateur(long identifiant, long identifiant1, String mdp) {
        if (idsConnectes.contains(identifiant)) {
            checkAnnotationAccess(new Object() {
            }.getClass().getEnclosingMethod(), utilisateurs.get(identifiant));
            utilisateurs.get(identifiant1).setMdp(mdp);
        }
        else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public Utilisateur connexion(String pseudo, String mdp) throws CoupleUtilisateurMDPInconnuException {
        for (Utilisateur u : utilisateurs.values()) {
            if ((pseudo.equals(u.getNom())) && (mdp.equals(u.getMdp()))) {
                idsConnectes.add(u.getIdentifiant());
                return u;
            }
        }
        throw new CoupleUtilisateurMDPInconnuException();
    }

    @Override
    public void deconnexion(long idUtilisateur) {
        if (idsConnectes.contains(idUtilisateur)) {
            idsConnectes.remove(idUtilisateur);
        } else {
            throw new IndividuNonConnecteException();
        }
    }

    @Override
    public boolean estUnUtilisateurConnu(String pseudo) {
        for (Utilisateur u : utilisateurs.values()) {
            if ((pseudo.equals(u.getNom()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estUnAdmin(long idUtilisateur) {
        return utilisateurs.get(idUtilisateur).hasRole(ADMIN);
    }

    @Override
    public boolean estUnVendeur(long idUtilisateur) {
        boolean resultat= utilisateurs.get(idUtilisateur).hasRole(VENDEUR);
        return resultat;
    }

    @Override
    public boolean estResponsableDesStocks(long idUtilisateur) {
        return utilisateurs.get(idUtilisateur).hasRole(RESPONSABLESTOCK);
    }
}
