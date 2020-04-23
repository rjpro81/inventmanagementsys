package com.ralph.inventmanagementsys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class creates activity objects for the application.
 * @author Ralph Julsaint
 */
@Entity
@Table(name = "ACTIVITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByActivityid", query = "SELECT a FROM Activity a WHERE a.activityid = :activityid"),
    @NamedQuery(name = "Activity.findByActivity", query = "SELECT a FROM Activity a WHERE a.activity = :activity")})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACTIVITYID")
    private Integer activityid;
    @Basic(optional = false)
    @Column(name = "ACTIVITY")
    private String activity;

    protected Activity() {
    }

    protected Activity(Integer activityid) {
        this.activityid = activityid;
    }
    
    protected Activity(String activity){
        this.activity = activity;
    }

    protected Activity(Integer activityid, String activity) {
        this.activityid = activityid;
        this.activity = activity;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityid != null ? activityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        return !((this.activityid == null && other.activityid != null) || (this.activityid != null && !this.activityid.equals(other.activityid)));
    }

    @Override
    public String toString() {
        return activity;
    }
    
}
