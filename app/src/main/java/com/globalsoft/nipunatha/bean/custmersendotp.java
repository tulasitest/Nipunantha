package com.globalsoft.nipunatha.bean;

public class custmersendotp {

    /**
     * status : valid
     * message : OTP is Sent to you registered Mobile Number
     * reg_id : CUS150743
     * uid : 11
     * otp : 587432
     */

    private String status;
    private String message;
    private String reg_id;
    private String uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
