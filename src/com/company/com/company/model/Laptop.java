package com.company.com.company.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "laptop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Laptop {

    @XmlElement(name = "manufacturer")
    public String manufacturer;

    @XmlElement(name = "screen")
    public Screen screen;

    @XmlElement(name = "processor")
    public Processor processor;

    @XmlElement(name = "disc")
    public Disc disc;

    @XmlElement(name = "ram")
    public String ram;

    @XmlElement(name = "graphic_card")
    public GraphicCard graphic_card;

    @XmlElement(name = "os")
    public String os;

    @XmlElement(name = "disc_reader")
    public String disc_reader;


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public GraphicCard getGraphic_card() {
        return graphic_card;
    }

    public void setGraphic_card(GraphicCard graphic_card) {
        this.graphic_card = graphic_card;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDisc_reader() {
        return disc_reader;
    }

    public void setDisc_reader(String disc_reader) {
        this.disc_reader = disc_reader;
    }
}
