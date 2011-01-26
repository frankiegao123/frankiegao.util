package net.blogjava.frankiegao123.stuts2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.blogjava.frankiegao123.jpa.query.DateQuery;
import net.blogjava.frankiegao123.jpa.query.QueryMode;
import net.blogjava.frankiegao123.jpa.query.annotation.JpaQueries;
import net.blogjava.frankiegao123.jpa.query.annotation.JpaQuery;
import net.blogjava.frankiegao123.jpa.query.annotation.JpaQueryIgnore;

@Entity
@Table(name = "T_USER")
public class User {

    private Integer id;
    private String username;
    private String password;
    private Date createTime;

    private DateQuery date = new DateQuery();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JpaQueryIgnore
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @JpaQuery(mode = QueryMode.LIKE)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JpaQueryIgnore
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Transient
    @JpaQueries({
        @JpaQuery(property = "createTime", mode = QueryMode.GE, objectProperty = "fromDate"),
        @JpaQuery(property = "createTime", mode = QueryMode.LT, objectProperty = "nextToDate")
    })
    public DateQuery getDate() {
        return date;
    }
}
