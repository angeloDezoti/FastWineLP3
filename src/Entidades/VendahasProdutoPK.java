/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author angelodezoti
 */
@Embeddable
public class VendahasProdutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Venda_id_venda")
    private int vendaidvenda;
    @Basic(optional = false)
    @Column(name = "Produto_id_produto")
    private int produtoidproduto;

    public VendahasProdutoPK() {
    }

    public VendahasProdutoPK(int vendaidvenda, int produtoidproduto) {
        this.vendaidvenda = vendaidvenda;
        this.produtoidproduto = produtoidproduto;
    }

    public int getVendaidvenda() {
        return vendaidvenda;
    }

    public void setVendaidvenda(int vendaidvenda) {
        this.vendaidvenda = vendaidvenda;
    }

    public int getProdutoidproduto() {
        return produtoidproduto;
    }

    public void setProdutoidproduto(int produtoidproduto) {
        this.produtoidproduto = produtoidproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) vendaidvenda;
        hash += (int) produtoidproduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendahasProdutoPK)) {
            return false;
        }
        VendahasProdutoPK other = (VendahasProdutoPK) object;
        if (this.vendaidvenda != other.vendaidvenda) {
            return false;
        }
        if (this.produtoidproduto != other.produtoidproduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.VendahasProdutoPK[ vendaidvenda=" + vendaidvenda + ", produtoidproduto=" + produtoidproduto + " ]";
    }
    
}
