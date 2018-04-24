/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
@Entity
public class Reception implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateReception;
    @OneToMany(mappedBy = "reception")
    private List<LigneReception> ligneReceptions;
    @ManyToOne
    private ExpressionBesoin expressionBesoin;
    @ManyToOne
    private User user;
    @ManyToOne
    private UserStock userStock;

    public Reception() {
    }

    public Reception(String id) {
        this.id = id;
    }

    public UserStock getUserStock() {
        return userStock;
    }

    public void setUserStock(UserStock userStock) {
        this.userStock = userStock;
    }

    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }

    public List<LigneReception> getLigneReceptions() {
        if (ligneReceptions == null) {
            ligneReceptions = new ArrayList();
        }
        return ligneReceptions;
    }

    public void setLigneReceptions(List<LigneReception> ligneReceptions) {
        this.ligneReceptions = ligneReceptions;
    }

    public ExpressionBesoin getExpressionBesoin() {
        if (expressionBesoin == null) {
            expressionBesoin = new ExpressionBesoin();
        }
        return expressionBesoin;
    }

    public void setExpressionBesoin(ExpressionBesoin expressionBesoin) {
        this.expressionBesoin = expressionBesoin;
    }

    public User getUser() {
        if (expressionBesoin == null) {
            expressionBesoin = new ExpressionBesoin();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reception other = (Reception) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id;
    }

}
