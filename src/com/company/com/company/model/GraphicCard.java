package com.company.com.company.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "graphic_card")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphicCard {

    @XmlElement(name = "name")
    public String name;

    @XmlElement(name = "memory")
    public String memory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }
}
