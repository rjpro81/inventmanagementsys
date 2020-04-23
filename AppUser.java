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
 * This entity class creates new users for the application
 * @author Ralph Julsaint
 */
@Entity
@Table(name = "APP_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a"),
    @NamedQuery(name = "AppUser.findByUserno", query = "SELECT a FROM AppUser a WHERE a.userno = :userno"),
    @NamedQuery(name = "AppUser.findByUserfirstname", query = "SELECT a FROM AppUser a WHERE a.userfirstname = :userfirstname"),
    @NamedQuery(name = "AppUser.findByUserlastname", query = "SELECT a FROM AppUser a WHERE a.userlastname = :userlastname"),
    @NamedQuery(name = "AppUser.findByUseremail", query = "SELECT a FROM AppUser a WHERE a.useremail = :useremail"),
    @NamedQuery(name = "AppUser.findByUserphone", query = "SELECT a FROM AppUser a WHERE a.userphone = :userphone"),
    @NamedQuery(name = "AppUser.findByUsername", query = "SELECT a FROM AppUser a WHERE a.username = :username"),
    @NamedQuery(name = "AppUser.findByUserpassword", query = "SELECT a FROM AppUser a WHERE a.userpassword = :userpassword")})
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERNO")
    private Integer userno;
    @Basic(optional = false)
    @Column(name = "USERFIRSTNAME")
    private String userfirstname;
    @Basic(optional = false)
    @Column(name = "USERLASTNAME")
    private String userlastname;
    @Column(name = "USEREMAIL")
    private String useremail;
    @Column(name = "USERPHONE")
    private String userphone;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "USERPASSWORD")
    private String userpassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userno")
    private List<OrderTable> orderTableList;

    protected AppUser() {
    }

    protected AppUser(Integer userno) {
        this.userno = userno;
    }

    /**
     *
     * @param userno
     * @param userfirstname
     * @param userlastname
     * @param username
     * @param userpassword
     */
    protected AppUser(Integer userno, String userfirstname, String userlastname, String username, String userpassword) {
        this.userno = userno;
        this.userfirstname = userfirstname;
        this.userlastname = userlastname;
        this.username = username;
        this.userpassword = userpassword;
    }

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @XmlTransient
    public List<OrderTable> getOrderTableList() {
        return orderTableList;
    }

    public void setOrderTableList(List<OrderTable> orderTableList) {
        this.orderTableList = orderTableList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userno != null ? userno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.userno == null && other.userno != null) || (this.userno != null && !this.userno.equals(other.userno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ralph.inventmanagementsys.AppUser[ userno=" + userno + " ]";
    }
    
}
