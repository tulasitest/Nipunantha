package com.globalsoft.nipunatha.bean;

public class customerLogin {

    /**
     * status : valid
     * message : Successfully Logged In
     * result : {"uid":"8","reg_id":"CUS300264","email":"charishma.patri1256@gmail.com","name":"charishma patri","custmerLogIn":"true"}
     */

    private String status;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * uid : 8
         * reg_id : CUS300264
         * email : charishma.patri1256@gmail.com
         * name : charishma patri
         * custmerLogIn : true
         */

        private String uid;
        private String reg_id;
        private String email;
        private String name;
        private String custmerLogIn;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCustmerLogIn() {
            return custmerLogIn;
        }

        public void setCustmerLogIn(String custmerLogIn) {
            this.custmerLogIn = custmerLogIn;
        }
    }
}
