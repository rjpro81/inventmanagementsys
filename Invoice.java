package com.ralph.inventmanagementsys;

import java.io.Serializable;
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
 * This entity class creates invoice objects for the application.
 * @author Ralph Julsaint
 */
@Entity
@Table(name = "INVOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceno", query = "SELECT i FROM Invoice i WHERE i.invoiceno = :invoiceno"),
    @NamedQuery(name = "Invoice.findByInvoicedate", query = "SELECT i FROM Invoice i WHERE i.invoicedate = :invoicedate")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "INVOICENO")
    private Integer invoiceno;
    @Basic(optional = false)
    @Column(name = "INVOICEDATE")
    @Temporal(TemporalType.DATE)
    private Date invoicedate;
    @JoinColumn(name = "CUSTOMERNO", referencedColumnName = "CUSTOMERNO")
    @ManyToOne(optional = false)
    private Customer customerno;

    public Invoice() {
    }

    public Invoice(Integer invoiceno) {
        this.invoiceno = invoiceno;
    }

    public Invoice(Integer invoiceno, Date invoicedate) {
        this.invoiceno = invoiceno;
        this.invoicedate = invoicedate;
    }

    public Integer getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(Integer invoiceno) {
        this.invoiceno = invoiceno;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
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
        hash += (invoiceno != null ? invoiceno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceno == null && other.invoiceno != null) || (this.invoiceno != null && !this.invoiceno.equals(other.invoiceno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ralph.inventmanagementsys.Invoice[ invoiceno=" + invoiceno + " ]";
    }
    
}
