/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author angelodezoti
 */
@Entity
@Table(name = "Uva")
@NamedQueries({
    @NamedQuery(name = "Uva.findAll", query = "SELECT u FROM Uva u")})
public class Uva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_uva")
    private Integer idUva;
    @Column(name = "nome_uva")
    private String nomeUva;
    @Column(name = "produtor_uva")
    private String produtorUva;
    @Column(name = "pais_de_origem_uva")
    private String paisDeOrigemUva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uvaiduva")
    private List<TipoVinho> tipoVinhoList;

    public Uva() {
    }

    public Uva(Integer idUva) {
        this.idUva = idUva;
    }

    public Integer getIdUva() {
        return idUva;
    }

    public void setIdUva(Integer idUva) {
        this.idUva = idUva;
    }

    public String getNomeUva() {
        return nomeUva;
    }

    public void setNomeUva(String nomeUva) {
        this.nomeUva = nomeUva;
    }

    public String getProdutorUva() {
        return produtorUva;
    }

    public void setProdutorUva(String produtorUva) {
        this.produtorUva = produtorUva;
    }

    public String getPaisDeOrigemUva() {
        return paisDeOrigemUva;
    }

    public void setPaisDeOrigemUva(String paisDeOrigemUva) {
        this.paisDeOrigemUva = paisDeOrigemUva;
    }

    public List<TipoVinho> getTipoVinhoList() {
        return tipoVinhoList;
    }

    public void setTipoVinhoList(List<TipoVinho> tipoVinhoList) {
        this.tipoVinhoList = tipoVinhoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUva != null ? idUva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uva)) {
            return false;
        }
        Uva other = (Uva) object;
        if ((this.idUva == null && other.idUva != null) || (this.idUva != null && !this.idUva.equals(other.idUva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "Entidades.Uva[ idUva=" + idUva + " ]";
        return idUva + "-" + getNomeUva();
    }
    
}
