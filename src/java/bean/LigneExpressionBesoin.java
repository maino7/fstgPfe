/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
@Table(name="LigneExpressionBesoin")
public class LigneExpressionBesoin extends Ligne implements Serializable {


    @ManyToOne
    private ExpressionBesoin expressionBesoin;


    


    public ExpressionBesoin getExpressionBesoin() {
        if (expressionBesoin == null) {
            expressionBesoin = new ExpressionBesoin();
        }
        return expressionBesoin;
    }

    public void setExpressionBesoin(ExpressionBesoin expressionBesoin) {
        this.expressionBesoin = expressionBesoin;
    }
    
    

    public LigneExpressionBesoin() {
    }

    public LigneExpressionBesoin(Long id) {
        super(id);
    }

    public LigneExpressionBesoin(Long id, double quantite) {
        super(id, quantite);
    }
    
    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneExpressionBesoin)) {
            return false;
        }
        LigneExpressionBesoin other = (LigneExpressionBesoin) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + "";
    }

}
