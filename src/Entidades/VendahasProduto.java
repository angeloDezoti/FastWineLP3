/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author angelodezoti
 */
@Entity
@Table(name = "Venda_has_Produto")
@NamedQueries({
    @NamedQuery(name = "VendahasProduto.findAll", query = "SELECT v FROM VendahasProduto v")})
public class VendahasProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendahasProdutoPK vendahasProdutoPK;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_de_venda")
    private Double precoDeVenda;
    @JoinColumn(name = "Produto_id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "Venda_id_venda", referencedColumnName = "id_venda", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public VendahasProduto() {
    }

    public VendahasProduto(VendahasProdutoPK vendahasProdutoPK) {
        this.vendahasProdutoPK = vendahasProdutoPK;
    }

    public VendahasProduto(int vendaidvenda, int produtoidproduto) {
        this.vendahasProdutoPK = new VendahasProdutoPK(vendaidvenda, produtoidproduto);
    }

    public VendahasProdutoPK getVendahasProdutoPK() {
        return vendahasProdutoPK;
    }

    public void setVendahasProdutoPK(VendahasProdutoPK vendahasProdutoPK) {
        this.vendahasProdutoPK = vendahasProdutoPK;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(Double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendahasProdutoPK != null ? vendahasProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendahasProduto)) {
            return false;
        }
        VendahasProduto other = (VendahasProduto) object;
        if ((this.vendahasProdutoPK == null && other.vendahasProdutoPK != null) || (this.vendahasProdutoPK != null && !this.vendahasProdutoPK.equals(other.vendahasProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.VendahasProduto[ vendahasProdutoPK=" + vendahasProdutoPK + " ]";
    }
    
}
