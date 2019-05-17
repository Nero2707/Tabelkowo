package com.company.com.company.model;

public class Laptop {
    public String manufacturer;
    public Screen screen;
    public Processor processor;
    public Disc disc;
    public String ram;
    public GraphicCard graphic_card;
    public String os;
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
