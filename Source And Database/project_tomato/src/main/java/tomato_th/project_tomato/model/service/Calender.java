package tomato_th.project_tomato.model.service;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Calender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calender_id;
    private int status;
    @Temporal(TemporalType.DATE)
    private Date solar_calendar;
    @Temporal(TemporalType.DATE)
    private Date lunar_calendar;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated;
    @ManyToOne
    @JoinColumn(name = "service_id",referencedColumnName = "service_id")
    private Services services;

    public Calender() {
    }

    public Calender(int calender_id, int status, Date solar_calendar, Date lunar_calendar, Date created, Date updated, Services services) {
        this.calender_id = calender_id;
        this.status = status;
        this.solar_calendar = solar_calendar;
        this.lunar_calendar = lunar_calendar;
        this.created = created;
        this.updated = updated;
        this.services = services;
    }

    public int getCalender_id() {
        return calender_id;
    }

    public void setCalender_id(int calender_id) {
        this.calender_id = calender_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSolar_calendar() {
        return solar_calendar;
    }

    public void setSolar_calendar(Date solar_calendar) {
        this.solar_calendar = solar_calendar;
    }

    public Date getLunar_calendar() {
        return lunar_calendar;
    }

    public void setLunar_calendar(Date lunar_calendar) {
        this.lunar_calendar = lunar_calendar;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
