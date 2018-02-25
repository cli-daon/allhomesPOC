package com.allhomes.poc.javaBeans;

import java.util.List;

/**
 * Created by cli on 25/02/2018.
 */

public class ListingResults {
    private List<Property> Listings;

    public List<Property> getListings() {
        return Listings;
    }

    public void setListings(List<Property> Listings) {
        this.Listings = Listings;
    }
}
