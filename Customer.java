package com.ralph.inventmanagementsys;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This entity class creates customer objects for the application.
 * @author Ralph Julsaint
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerno", query = "SELECT c FROM Customer c WHERE c.customerno = :customerno"),
    @NamedQuery(name = "Customer.findByCustomername", query = "SELECT c FROM Customer c WHERE c.customername = :customername"),
    @NamedQuery(name = "Customer.findByCustomeraddress", query = "SELECT c FROM Customer c WHERE c.customeraddress = :customeraddress"),
    @NamedQuery(name = "Customer.findByCustomercity", query = "SELECT c FROM Customer c WHERE c.customercity = :customercity"),
    @NamedQuery(name = "Customer.findByCustomerstate", query = "SELECT c FROM Customer c WHERE c.customerstate = :customerstate"),
    @NamedQuery(name = "Customer.findByCustomerzip", query = "SELECT c FROM Customer c WHERE c.customerzip = :customerzip"),
    @NamedQuery(name = "Customer.findByCustomeremail", query = "SELECT c FROM Customer c WHERE c.customeremail = :customeremail"),
    @NamedQuery(name = "Customer.findByCustomerphone", query = "SELECT c FROM Customer c WHERE c.customerphone = :customerphone")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTOMERNO")
    private Integer customerno;
    @Basic(optional = false)
    @Column(name = "CUSTOMERNAME")
    private String customername;
    @Column(name = "CUSTOMERADDRESS")
    private String customeraddress;
    @Column(name = "CUSTOMERCITY")
    private String customercity;
    @Column(name = "CUSTOMERSTATE")
    private String customerstate;
    @Column(name = "CUSTOMERZIP")
    private String customerzip;
    @Column(name = "CUSTOMEREMAIL")
    private String customeremail;
    @Column(name = "CUSTOMERPHONE")
    private String customerphone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerno")
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerno")
    private List<Invoice> invoiceList;

    protected Customer() {
    }

    protected Customer(Integer customerno) {
        this.customerno = customerno;
    }

    protected Customer(Integer customerno, String customername) {
        this.customerno = customerno;
        this.customername = customername;
    }
    
    protected Customer(Integer customerno, String customername, String customeraddress,
            String customercity, String customerstate, String customerzip, 
            String customeremail, String customerphone){
        this.customerno = customerno;
        this.customername = customername;
        this.customeraddress = customeraddress;
        this.customercity = customercity;
        this.customerstate = customerstate;
        this.customerzip = customerzip;
        this.customeremail = customeremail;
        this.customerphone = customerphone;
    }

    public Integer getCustomerno() {
        return customerno;
    }

    public void setCustomerno(Integer customerno) {
        this.customerno = customerno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getCustomercity() {
        return customercity;
    }

    public void setCustomercity(String customercity) {
        this.customercity = customercity;
    }

    public String getCustomerstate() {
        return customerstate;
    }

    public void setCustomerstate(String customerstate) {
        this.customerstate = customerstate;
    }

    public String getCustomerzip() {
        return customerzip;
    }

    public void setCustomerzip(String customerzip) {
        this.customerzip = customerzip;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerno != null ? customerno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        return !((this.customerno == null && other.customerno != null) || (this.customerno != null && !this.customerno.equals(other.customerno)));
    }

    @Override
    public String toString() {
        return customername;
    }
    
}
