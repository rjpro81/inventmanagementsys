package com.ralph.inventmanagementsys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This entity class creates item objects for the application.
 * @author Ralph Julsaint
 */
@Entity
@Table(name = "ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByItemno", query = "SELECT i FROM Item i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "Item.findByItemname", query = "SELECT i FROM Item i WHERE i.itemname = :itemname"),
    @NamedQuery(name = "Item.findByItemqoh", query = "SELECT i FROM Item i WHERE i.itemqoh = :itemqoh"),
    @NamedQuery(name = "Item.findByItemprice", query = "SELECT i FROM Item i WHERE i.itemprice = :itemprice"),
    @NamedQuery(name = "Item.findByItemexpdate", query = "SELECT i FROM Item i WHERE i.itemexpdate = :itemexpdate")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ITEMNO")
    private Integer itemno;
    @Basic(optional = false)
    @Column(name = "ITEMNAME")
    private String itemname;
    @Column(name = "ITEMQOH")
    private Integer itemqoh;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ITEMPRICE")
    private BigDecimal itemprice;
    @Column(name = "ITEMEXPDATE")
    @Temporal(TemporalType.DATE)
    private Date itemexpdate;
    @JoinColumn(name = "CUSTOMERNO", referencedColumnName = "CUSTOMERNO")
    @ManyToOne(optional = false)
    private Customer customerno;

    public Item() {
    }

    public Item(Integer itemno) {
        this.itemno = itemno;
    }

    public Item(Integer itemno, String itemname) {
        this.itemno = itemno;
        this.itemname = itemname;
    }

    public Integer getItemno() {
        return itemno;
    }

    public void setItemno(Integer itemno) {
        this.itemno = itemno;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getItemqoh() {
        return itemqoh;
    }

    public void setItemqoh(Integer itemqoh) {
        this.itemqoh = itemqoh;
    }

    public BigDecimal getItemprice() {
        return itemprice;
    }

    public void setItemprice(BigDecimal itemprice) {
        this.itemprice = itemprice;
    }

    public Date getItemexpdate() {
        return itemexpdate;
    }

    public void setItemexpdate(Date itemexpdate) {
        this.itemexpdate = itemexpdate;
    }

    public Customer getCustomerno() {
        return customerno;
    }

    public void setCustomerno(Customer customerno) {
        this.customerno = customerno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemno != null ? itemno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemno == null && other.itemno != null) || (this.itemno != null && !this.itemno.equals(other.itemno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ralph.inventmanagementsys.Item[ itemno=" + itemno + " ]";
    }
    
}
