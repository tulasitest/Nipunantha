package com.globalsoft.nipunatha.bean;

public class customerregister {

    /**
     * status : valid
     * message : You Have Successfully Register..! Reg Id CUS210896 Please verify Mobile No and Email
     * reg_id : CUS210896
     * uid : 24
     * otp : 815962
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
