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
 * This entity class creates order table objects for the application.
 * @author Ralph Julsaint
 */
@Entity
@Table(name = "ORDER_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderTable.findAll", query = "SELECT o FROM OrderTable o"),
    @NamedQuery(name = "OrderTable.findByOrderno", query = "SELECT o FROM OrderTable o WHERE o.orderno = :orderno"),
    @NamedQuery(name = "OrderTable.findByOrderdate", query = "SELECT o FROM OrderTable o WHERE o.orderdate = :orderdate"),
    @NamedQuery(name = "OrderTable.findByOrdshippingaddress", query = "SELECT o FROM OrderTable o WHERE o.ordshippingaddress = :ordshippingaddress"),
    @NamedQuery(name = "OrderTable.findByOrdcity", query = "SELECT o FROM OrderTable o WHERE o.ordcity = :ordcity"),
    @NamedQuery(name = "OrderTable.findByOrdstate", query = "SELECT o FROM OrderTable o WHERE o.ordstate = :ordstate"),
    @NamedQuery(name = "OrderTable.findByOrdzip", query = "SELECT o FROM OrderTable o WHERE o.ordzip = :ordzip")})
public class OrderTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ORDERNO")
    private Integer orderno;
    @Basic(optional = false)
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    @Basic(optional = false)
    @Column(name = "ORDSHIPPINGADDRESS")
    private String ordshippingaddress;
    @Basic(optional = false)
    @Column(name = "ORDCITY")
    private String ordcity;
    @Basic(optional = false)
    @Column(name = "ORDSTATE")
    private String ordstate;
    @Basic(optional = false)
    @Column(name = "ORDZIP")
    private int ordzip;
    @JoinColumn(name = "USERNO", referencedColumnName = "USERNO")
    @ManyToOne(optional = false)
    private AppUser userno;

    public OrderTable() {
    }

    public OrderTable(Integer orderno) {
        this.orderno = orderno;
    }

    public OrderTable(Integer orderno, Date orderdate, String ordshippingaddress, String ordcity, String ordstate, int ordzip) {
        this.orderno = orderno;
        this.orderdate = orderdate;
        this.ordshippingaddress = ordshippingaddress;
        this.ordcity = ordcity;
        this.ordstate = ordstate;
        this.ordzip = ordzip;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdshippingaddress() {
        return ordshippingaddress;
    }

    public void setOrdshippingaddress(String ordshippingaddress) {
        this.ordshippingaddress = ordshippingaddress;
    }

    public String getOrdcity() {
        return ordcity;
    }

    public void setOrdcity(String ordcity) {
        this.ordcity = ordcity;
    }

    public String getOrdstate() {
        return ordstate;
    }

    public void setOrdstate(String ordstate) {
        this.ordstate = ordstate;
    }

    public int getOrdzip() {
        return ordzip;
    }

    public void setOrdzip(int ordzip) {
        this.ordzip = ordzip;
    }

    public AppUser getUserno() {
        return userno;
    }

    public void setUserno(AppUser userno) {
        this.userno = userno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderno != null ? orderno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderTable)) {
            return false;
        }
        OrderTable other = (OrderTable) object;
        if ((this.orderno == null && other.orderno != null) || (this.orderno != null && !this.orderno.equals(other.orderno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ralph.inventmanagementsys.OrderTable[ orderno=" + orderno + " ]";
    }
    
}
