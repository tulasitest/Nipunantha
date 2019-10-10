package com.globalsoft.nipunatha.bean;

public class Tradsmenregister {

    /**
     * status : valid
     * message : You Have Successfully Register..! Reg Id TMA090276 Please verify Mobile Number and Email
     * reg_id : TMA090276
     * uid : 17
     * otp : 948176
     */

    private String status;
    private String message;
    private String reg_id;
    private int uid;
    private String otp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

