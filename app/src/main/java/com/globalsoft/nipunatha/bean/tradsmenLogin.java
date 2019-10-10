package com.globalsoft.nipunatha.bean;

public class tradsmenLogin {

    /**
     * status : valid
     * message : Successfully Logged In
     * result : {"uid":"18","reg_id":"TMA090917","email":"susmitha.adaveni@gmail.com","name":"Sushmitha Adaveni","tradesmenLogIn":"true","service_id":null,"service_name":null}
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
         * uid : 18
         * reg_id : TMA090917
         * email : susmitha.adaveni@gmail.com
         * name : Sushmitha Adaveni
         * tradesmenLogIn : true
         * service_id : null
         * service_name : null
         */
//36 uid for customer
        private String uid;
        private String reg_id;
        private String email;
        private String name;
        private String tradesmenLogIn;
        private Object service_id;
        private Object service_name;

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

        public String getTradesmenLogIn() {
            return tradesmenLogIn;
        }

        public void setTradesmenLogIn(String tradesmenLogIn) {
            this.tradesmenLogIn = tradesmenLogIn;
        }

        public Object getService_id() {
            return service_id;
        }

        public void setService_id(Object service_id) {
            this.service_id = service_id;
        }

        public Object getService_name() {
            return service_name;
        }

        public void setService_name(Object service_name) {
            this.service_name = service_name;
        }
    }
}
