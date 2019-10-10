package com.globalsoft.nipunatha.bean;

public class verifyotp {


    /**
     * status : valid
     * message : This user already verified
     * uid : 31
     * reg_id : CUS230149
     */

    private String status;
    private String message;
    private String uid;
    private String reg_id;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }
}
