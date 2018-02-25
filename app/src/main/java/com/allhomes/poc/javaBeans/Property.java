package com.allhomes.poc.javaBeans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cli on 25/02/2018.
 */

public class Property implements Parcelable{
    private String AgencyLogoUrl;
    private Long AdId;
    private int Bathrooms;
    private int Bedrooms;
    private int Carspaces;
    private String DisplayPrice;
    private String DisplayableAddress;
    private String TruncatedDescription;
    private String RetinaDisplayThumbUrl;
    private String SecondRetinaDisplayThumbUrl;
    private int IsElite;

    protected Property(Parcel in) {
        AdId = in.readLong();
        AgencyLogoUrl = in.readString();
        Bathrooms = in.readInt();
        Bedrooms = in.readInt();
        Carspaces = in.readInt();
        DisplayPrice = in.readString();
        DisplayableAddress = in.readString();
        TruncatedDescription = in.readString();
        RetinaDisplayThumbUrl = in.readString();
        SecondRetinaDisplayThumbUrl = in.readString();
        IsElite = in.readInt();
    }

    public static final Creator<Property> CREATOR = new Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(AdId);
        dest.writeString(AgencyLogoUrl);
        dest.writeInt(Bathrooms);
        dest.writeInt(Bedrooms);
        dest.writeInt(Carspaces);
        dest.writeString(DisplayPrice);
        dest.writeString(DisplayableAddress);
        dest.writeString(TruncatedDescription);
        dest.writeString(RetinaDisplayThumbUrl);
        dest.writeString(SecondRetinaDisplayThumbUrl);
        dest.writeInt(IsElite);
    }

    public Long getAdId() {
        return AdId;
    }

    public void setAdId(Long adId) {
        AdId = adId;
    }

    public String getAgencyLogoUrl() {
        return AgencyLogoUrl;
    }

    public void setAgencyLogoUrl(String agencyLogoUrl) {
        AgencyLogoUrl = agencyLogoUrl;
    }

    public int getBathrooms() {
        return Bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        Bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return Bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        Bedrooms = bedrooms;
    }

    public int getCarspaces() {
        return Carspaces;
    }

    public void setCarspaces(int carspaces) {
        Carspaces = carspaces;
    }

    public String getDisplayPrice() {
        return DisplayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        DisplayPrice = displayPrice;
    }

    public String getDisplayableAddress() {
        return DisplayableAddress;
    }

    public void setDisplayableAddress(String displayableAddress) {
        DisplayableAddress = displayableAddress;
    }

    public String getTruncatedDescription() {
        return TruncatedDescription;
    }

    public void setTruncatedDescription(String truncatedDescription) {
        TruncatedDescription = truncatedDescription;
    }

    public String getRetinaDisplayThumbUrl() {
        return RetinaDisplayThumbUrl;
    }

    public void setRetinaDisplayThumbUrl(String retinaDisplayThumbUrl) {
        RetinaDisplayThumbUrl = retinaDisplayThumbUrl;
    }

    public String getSecondRetinaDisplayThumbUrl() {
        return SecondRetinaDisplayThumbUrl;
    }

    public void setSecondRetinaDisplayThumbUrl(String secondRetinaDisplayThumbUrl) {
        SecondRetinaDisplayThumbUrl = secondRetinaDisplayThumbUrl;
    }

    public int getIsElite() {
        return IsElite;
    }

    public void setIsElite(int isElite) {
        IsElite = isElite;
    }

}
