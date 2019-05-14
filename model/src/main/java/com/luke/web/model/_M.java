package com.luke.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class _M implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;

    @Column(nullable = false)
    private Boolean b_isDel = false;

    @Column(nullable = false)
    private Date b_wtime = new Date() ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getB_isDel() {
        return b_isDel;
    }

    public void setB_isDel(Boolean b_isDel) {
        this.b_isDel = b_isDel;
    }

    public Date getB_wtime() {
        return b_wtime;
    }

    public void setB_wtime(Date b_wtime) {
        this.b_wtime = b_wtime;
    }
}
