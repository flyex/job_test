package com.datetime.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Timekeeper {
    private String timekeeperId;
    private Timestamp dateTime;
    private String inOut;

    @Id
    @Column(name = "Timekeeper_Id", nullable = false, length = 36)
    public String getTimekeeperId() {
        return timekeeperId;
    }

    public void setTimekeeperId(String timekeeperId) {
        this.timekeeperId = timekeeperId;
    }

    @Basic
    @Column(name = "Date_Time", nullable = false)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "In_Out", nullable = false, length = 1)
    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timekeeper that = (Timekeeper) o;
        return Objects.equals(timekeeperId, that.timekeeperId) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(inOut, that.inOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timekeeperId, dateTime, inOut);
    }
}
