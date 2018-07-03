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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author angelodezoti
 */
@Entity
@Table(name = "TipoVinho")
@NamedQueries({
    @NamedQuery(name = "TipoVinho.findAll", query = "SELECT t FROM TipoVinho t")})
public class TipoVinho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Column(name = "nome_tipo")
    private String nomeTipo;
    @JoinColumn(name = "Uva_id_uva", referencedColumnName = "id_uva")
    @ManyToOne(optional = false)
    private Uva uvaiduva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoVinhoidtipo")
    private List<Produto> produtoList;

    public TipoVinho() {
    }

    public TipoVinho(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public Uva getUvaiduva() {
        return uvaiduva;
    }

    public void setUvaiduva(Uva uvaiduva) {
        this.uvaiduva = uvaiduva;
    }

    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVinho)) {
            return false;
        }
        TipoVinho other = (TipoVinho) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idTipo + "-" + getNomeTipo();
    }
    
}
