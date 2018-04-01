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
public class ExpressionBesoin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int recu;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateExpressionBesoin;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "expressionBesoin")
    private List<Reception> receptions;
    @OneToMany(mappedBy = "expressionBesoin")
    private List<LigneExpressionBesoin> ligneExpressionBesoins;
    @ManyToOne
    private UserStock userStock;

    public ExpressionBesoin() {
    }

    public ExpressionBesoin(Long id) {
        this.id = id;
    }

    public int getRecu() {
        return recu;
    }

    public void setRecu(int recu) {
        this.recu = recu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserStock getUserStock() {
        return userStock;
    }

    public void setUserStock(UserStock userStock) {
        this.userStock = userStock;
    }

    public Date getDateExpressionBesoin() {
        return dateExpressionBesoin;
    }

    public void setDateExpressionBesoin(Date dateExpressionBesoin) {
        this.dateExpressionBesoin = dateExpressionBesoin;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reception> getReceptions() {
        if (receptions == null) {
            receptions = new ArrayList();
        }
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<LigneExpressionBesoin> getLigneExpressionBesoins() {
        if (ligneExpressionBesoins == null) {
            ligneExpressionBesoins = new ArrayList();
        }
        return ligneExpressionBesoins;
    }

    public void setLigneExpressionBesoins(List<LigneExpressionBesoin> ligneExpressionBesoins) {
        this.ligneExpressionBesoins = ligneExpressionBesoins;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final ExpressionBesoin other = (ExpressionBesoin) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + "";
    }

}
