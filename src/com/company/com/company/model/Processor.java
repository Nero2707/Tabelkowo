package com.company.com.company.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "laptop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Processor {
    @XmlElement(name = "name")
    public String name;

    @XmlElement(name = "physical_cores")
    public String physical_cores;

    @XmlElement(name = "clock_speed")
    public String clock_speed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysical_cores() {
        return physical_cores;
    }

    public void setPhysical_cores(String physical_cores) {
        this.physical_cores = physical_cores;
    }

    public String getClock_speed() {
        return clock_speed;
    }

    public void setClock_speed(String clock_speed) {
        this.clock_speed = clock_speed;
    }
}
