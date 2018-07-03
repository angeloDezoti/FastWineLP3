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
@Table(name = "Produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")})
public class Produto implements Serializable {

    @Column(name = "caminho_imagem_produto")
    private String caminhoImagemProduto;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_produto")
    private Integer idProduto;
    @Column(name = "nome_produto")
    private String nomeProduto;
    @Column(name = "pais_de_origem_produto")
    private String paisDeOrigemProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "teor_alcoolico_produto")
    private Double teorAlcoolicoProduto;
    @Column(name = "volume_produto")
    private Double volumeProduto;
    @Column(name = "preco_produto")
    private Double precoProduto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<VendahasProduto> vendahasProdutoList;
    @JoinColumn(name = "TipoVinho_id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoVinho tipoVinhoidtipo;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getPaisDeOrigemProduto() {
        return paisDeOrigemProduto;
    }

    public void setPaisDeOrigemProduto(String paisDeOrigemProduto) {
        this.paisDeOrigemProduto = paisDeOrigemProduto;
    }

    public Double getTeorAlcoolicoProduto() {
        return teorAlcoolicoProduto;
    }

    public void setTeorAlcoolicoProduto(Double teorAlcoolicoProduto) {
        this.teorAlcoolicoProduto = teorAlcoolicoProduto;
    }

    public Double getVolumeProduto() {
        return volumeProduto;
    }

    public void setVolumeProduto(Double volumeProduto) {
        this.volumeProduto = volumeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public List<VendahasProduto> getVendahasProdutoList() {
        return vendahasProdutoList;
    }

    public void setVendahasProdutoList(List<VendahasProduto> vendahasProdutoList) {
        this.vendahasProdutoList = vendahasProdutoList;
    }

    public TipoVinho getTipoVinhoidtipo() {
        return tipoVinhoidtipo;
    }

    public void setTipoVinhoidtipo(TipoVinho tipoVinhoidtipo) {
        this.tipoVinhoidtipo = tipoVinhoidtipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idProduto + "-" + getNomeProduto();
    }

    public String getCaminhoImagemProduto() {
        return caminhoImagemProduto;
    }

    public void setCaminhoImagemProduto(String caminhoImagemProduto) {
        this.caminhoImagemProduto = caminhoImagemProduto;
    }
    
}
