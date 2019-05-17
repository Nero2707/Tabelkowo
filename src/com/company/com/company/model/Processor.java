package com.company.com.company.model;

public class Processor {
    public String name;
    public String physical_cores;
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
