package com.zlcm.server.model.apprep;

import java.util.List;

public class Periphery {
    String address;
    Integer did;
    List<String> advert;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAdvert() {
        return advert;
    }

    public void setAdvert(List<String> advert) {
        this.advert = advert;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}
