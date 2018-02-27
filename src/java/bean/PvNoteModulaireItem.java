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
import javax.persistence.OneToOne;

/**
 *
 * @author Abed
 */
@Entity
public class PvNoteModulaireItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private NoteModulaire noteModulaire;

    @ManyToOne
    private PvNoteSemestreItem pvNoteSemestreItem;

    public NoteModulaire getNoteModulaire() {
        return noteModulaire;
    }

    public void setNoteModulaire(NoteModulaire noteModulaire) {
        this.noteModulaire = noteModulaire;
    }

    public PvNoteSemestreItem getPvNoteSemestreItem() {
        return pvNoteSemestreItem;
    }

    public void setPvNoteSemestreItem(PvNoteSemestreItem pvNoteSemestreItem) {
        this.pvNoteSemestreItem = pvNoteSemestreItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PvNoteModulaireItem)) {
            return false;
        }
        PvNoteModulaireItem other = (PvNoteModulaireItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PvNoteModulaireItem[ id=" + id + " ]";
    }

}
