package com.fr.sinapps.model;

public class CarPark {


     private String ident, name, address, status, url, info;
     private int id, freeSpot, totalSpot, carpollingSpot, bikeSpot;
     private Double posX, posY;

    public CarPark(){

    }

    public CarPark(int id, String ident, String name, String address, String status, String url, String info, String pos, int freeSpot, int totalSpot, int carpollingSpot, int bikeSpot) {
        super();
        this.id = id;
        this.ident = ident;
        this.name = name;
        this.address = address;
        this.status = status;
        this.url = url;
        this.info = info;
        this.setPos(pos);
        this.freeSpot = freeSpot;
        this.totalSpot = totalSpot;
        this.carpollingSpot = carpollingSpot;
        this.bikeSpot = bikeSpot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getPosX() {
        return posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPos(String pos){
        String[] tempoPosTab = pos.split(" ");
        this.posX = Double.parseDouble(tempoPosTab[0]);
        this.posY = Double.parseDouble(tempoPosTab[1]);
    }

    public int getFreeSpot() {
        return freeSpot;
    }

    public void setFreeSpot(int freeSpot) {
        this.freeSpot = freeSpot;
    }

    public int getTotalSpot() {
        return totalSpot;
    }

    public void setTotalSpot(int totalSpot) {
        this.totalSpot = totalSpot;
    }

    public int getCarpollingSpot() {
        return carpollingSpot;
    }

    public void setCarpollingSpot(int carpollingSpot) {
        this.carpollingSpot = carpollingSpot;
    }

    public int getBikeSpot() {
        return bikeSpot;
    }

    public void setBikeSpot(int bikeSpot) {
        this.bikeSpot = bikeSpot;
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "ident='" + ident + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                ", info='" + info + '\'' +
                ", id=" + id +
                ", freeSpot=" + freeSpot +
                ", totalSpot=" + totalSpot +
                ", carpollingSpot=" + carpollingSpot +
                ", bikeSpot=" + bikeSpot +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
