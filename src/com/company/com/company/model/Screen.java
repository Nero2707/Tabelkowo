package com.company.com.company.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "laptop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Screen {

    @XmlElement(name = "size")
    public String size;

    @XmlElement(name = "type")
    public String type;

    @XmlElement(name = "touchscreen")
    public String touchscreen;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTouchscreen() {
        return touchscreen;
    }

    public void setTouchscreen(String touchscreen) {
        this.touchscreen = touchscreen;
    }
}
