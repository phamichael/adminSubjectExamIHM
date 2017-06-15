package modele.stock;

import modele.produit.Produit;
import modele.produit.ProduitQuantite;

import java.util.HashMap;
import java.util.Map;

public class StockProduits {
    private Map<Long,ProduitQuantite> stocks = new HashMap<>();

    public int getStock(Produit produit) {
        ProduitQuantite stock = stocks.get(produit.getId());
        if  (stock==null) {
            stock = new ProduitQuantite(produit,0);
            stocks.put(produit.getId(),stock);
        }
      return stock.getQuantite();
    }
    public void addStock(Produit produit, int quantite) {
        ProduitQuantite stock = stocks.get(produit.getId());
        if (stock==null) {
            stock = new ProduitQuantite(produit,quantite);
            stocks.put(produit.getId(), stock);
        }
        else {
            stock.setQuantite(stock.getQuantite()+quantite);
        }
    }


    public ProduitQuantite getProduitQuantite(Produit produit) {
        return stocks.get(produit.getId());
    }
}
