package com.noon.tawfiqyah;

import java.io.Serializable;

public class Apartment implements Serializable {

    private String apartmentId;
    private String apartmentImage;
    private String apartmentAddingDate;
    private String apartmentPrice;
    private String apartmentLocation;
    private String apartmentNOBath;
    private String apartmentNOBeds;
    private String apartmentSpace;
    private String apartmentNumber;
    private String apartmentNote;
    private String  apartmentGPS;
    private String  apartmentFloor;
    private String  apartmentFinishing;
    private String apartmentValidationDate;
    private String  apartmentView;
    private String  apartmentPaymentMethod;
    private String  apartmentDescreption;



    public Apartment(String apartmentId, String apartmentImage, String apartmentAddingDate,
                     String apartmentPrice, String apartmentLocation, String apartmentNOBath,
                     String apartmentNOBeds, String apartmentSpace, String apartmentNumber) {
        this.apartmentId = apartmentId;
        this.apartmentImage = apartmentImage;
        this.apartmentAddingDate = apartmentAddingDate;
        this.apartmentPrice = apartmentPrice;
        this.apartmentLocation = apartmentLocation;
        this.apartmentNOBath = apartmentNOBath;
        this.apartmentNOBeds = apartmentNOBeds;
        this.apartmentSpace = apartmentSpace;
        this.apartmentNumber = apartmentNumber;
    }


    public Apartment(String apartmentId,
                     String apartmentImage,
                     String apartmentAddingDate,
                     String apartmentPrice,
                     String apartmentLocation,
                     String apartmentNOBath,
                     String apartmentNOBeds,
                     String apartmentSpace,
                     String apartmentNumber,
                     String apartmentNote,
                     String apartmentGPS,
                     String apartmentFloor,
                     String apartmentFinishing,
                     String apartmentView,
                     String apartmentPaymentMethod,
                     String apartmentDescreption) {

        this.apartmentId = apartmentId;
        this.apartmentImage = apartmentImage;
        this.apartmentAddingDate = apartmentAddingDate;
        this.apartmentPrice = apartmentPrice;
        this.apartmentLocation = apartmentLocation;
        this.apartmentNOBath = apartmentNOBath;
        this.apartmentNOBeds = apartmentNOBeds;
        this.apartmentSpace = apartmentSpace;
        this.apartmentNumber = apartmentNumber;
        this.apartmentNote = apartmentNote;
        this.apartmentGPS = apartmentGPS;
        this.apartmentFloor = apartmentFloor;
        this.apartmentFinishing = apartmentFinishing;
        this.apartmentView = apartmentView;
        this.apartmentPaymentMethod = apartmentPaymentMethod;
        this.apartmentDescreption = apartmentDescreption;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void setApartmentImage(String apartmentImage) {
        this.apartmentImage = apartmentImage;
    }

    public void setApartmentAddingDate(String apartmentAddingDate) {
        this.apartmentAddingDate = apartmentAddingDate;
    }

    public void setApartmentPrice(String apartmentPrice) {
        this.apartmentPrice = apartmentPrice;
    }

    public void setApartmentLocation(String apartmentLocation) {
        this.apartmentLocation = apartmentLocation;
    }

    public void setApartmentNOBath(String apartmentNOBath) {
        this.apartmentNOBath = apartmentNOBath;
    }

    public void setApartmentNOBeds(String apartmentNOBeds) {
        this.apartmentNOBeds = apartmentNOBeds;
    }

    public void setApartmentSpace(String apartmentSpace) {
        this.apartmentSpace = apartmentSpace;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setApartmentNote(String apartmentNote) {
        this.apartmentNote = apartmentNote;
    }

    public void setApartmentGPS(String apartmentGPS) {
        this.apartmentGPS = apartmentGPS;
    }

    public void setApartmentFloor(String apartmentFloor) {
        this.apartmentFloor = apartmentFloor;
    }

    public void setApartmentFinishing(String apartmentFinishing) {
        this.apartmentFinishing = apartmentFinishing;
    }

    public void setApartmentValidationDate(String apartmentValidationDate) {
        this.apartmentValidationDate = apartmentValidationDate;
    }

    public void setApartmentView(String apartmentView) {
        this.apartmentView = apartmentView;
    }

    public void setApartmentPaymentMethod(String apartmentPaymentMethod) {
        this.apartmentPaymentMethod = apartmentPaymentMethod;
    }

    public void setApartmentDescreption(String apartmentDescreption) {
        this.apartmentDescreption = apartmentDescreption;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public String getApartmentImage() {
        return apartmentImage;
    }

    public String getApartmentAddingDate() {
        return apartmentAddingDate;
    }

    public String getApartmentPrice() {
        return apartmentPrice;
    }

    public String getApartmentLocation() {
        return apartmentLocation;
    }

    public String getApartmentNOBath() {
        return apartmentNOBath;
    }

    public String getApartmentNOBeds() {
        return apartmentNOBeds;
    }

    public String getApartmentSpace() {
        return apartmentSpace;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getApartmentNote() {
        return apartmentNote;
    }

    public String getApartmentGPS() {
        return apartmentGPS;
    }

    public String getApartmentFloor() {
        return apartmentFloor;
    }

    public String getApartmentFinishing() {
        return apartmentFinishing;
    }

    public String getApartmentValidationDate() {
        return apartmentValidationDate;
    }

    public String getApartmentView() {
        return apartmentView;
    }

    public String getApartmentPaymentMethod() {
        return apartmentPaymentMethod;
    }

    public String getApartmentDescreption() {
        return apartmentDescreption;
    }
}
