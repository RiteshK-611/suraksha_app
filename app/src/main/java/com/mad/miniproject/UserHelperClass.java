package com.mad.miniproject;

public class UserHelperClass {
    String name, contact, email, loi, bdesc, complaint;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String contact, String email, String loi, String bdesc, String complaint) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.loi = loi;
        this.bdesc = bdesc;
        this.complaint = complaint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }

    public String getBdesc() {
        return bdesc;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
