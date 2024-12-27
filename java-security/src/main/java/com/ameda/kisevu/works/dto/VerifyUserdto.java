package com.ameda.kisevu.works.dto;
/*
*
@author ameda
@project java-security
*
*/

public class VerifyUserdto {

    private String email;
    private String verificationCode;

    public VerifyUserdto() {
    }

    public VerifyUserdto(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "VerifyUserdto{" +
                "email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
