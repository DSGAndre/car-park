package com.fr.sinapps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarPark {

    private @Id @GeneratedValue long id;
    private String ident, name, address, status, url, info;
    private int freeSpot, totalSpot, carpollingSpot, bikeSpot;
    private double posLat, posLon;

    public CarPark() {

    }

    public CarPark(long id, String ident, String name, String address, String status, String url, String info, String pos, int freeSpot, int totalSpot, int carpollingSpot, int bikeSpot) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getPosLat() {
        return posLat;
    }

    public double getPosLon() {
        return posLon;
    }

    public void setPos(String pos) {
        String[] tempoPosTab = pos.split(" ");
        this.posLat = Double.parseDouble(tempoPosTab[0]);
        this.posLon = Double.parseDouble(tempoPosTab[1]);
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
                ", posLat=" + posLat +
                ", posLon=" + posLon +
                '}';
    }

    @Override
    public boolean equals(Object currentCp) {

        if (this == currentCp)
            return true;

        if (!(currentCp instanceof CarPark))
            return false;
        CarPark newCp = (CarPark) currentCp;

        return this.id == newCp.id && this.ident.equals(newCp.ident);
    }

    public void updateCarPark(CarPark carPark) {
        this.name = carPark.name;
        this.address =  carPark.address;
        this.status =  carPark.status;
        this.url =  carPark.url;
        this.info =  carPark.info;
        this.posLat =  carPark.posLat;
        this.posLon =  carPark.posLon;
        this.freeSpot =  carPark.freeSpot;
        this.totalSpot =  carPark.totalSpot;
        this.carpollingSpot =  carPark.carpollingSpot;
        this.bikeSpot =  carPark.bikeSpot;
    }

}
