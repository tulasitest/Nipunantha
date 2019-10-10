package com.globalsoft.nipunatha.bean;

public class Tradsmenprofile {


    /**
     * status : valid
     * message : User record is found
     * result : {"uid":"2","reg_id":"TMA270268","fname":"Phani","lname":"Akula","email":"tradesmen@gmail.com","mobile_no":"9121421235","state":"Andhra Pradesh","city":"Garacharma","area":"Old Guntur","pincode":"522002","service_id":"6","service_name":"Plumber","no_of_employees":"40","verify_email":"1","image":"http://labs.sslotus.com/services/uploads/tradesmen/9a84da17cafea3e521d4d9fab256e8e2.jpg","user_active":"1","created":"2019-03-27 12:46:58","updated":"2019-10-07 12:06:25"}
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
         * uid : 2
         * reg_id : TMA270268
         * fname : Phani
         * lname : Akula
         * email : tradesmen@gmail.com
         * mobile_no : 9121421235
         * state : Andhra Pradesh
         * city : Garacharma
         * area : Old Guntur
         * pincode : 522002
         * service_id : 6
         * service_name : Plumber
         * no_of_employees : 40
         * verify_email : 1
         * image : http://labs.sslotus.com/services/uploads/tradesmen/9a84da17cafea3e521d4d9fab256e8e2.jpg
         * user_active : 1
         * created : 2019-03-27 12:46:58
         * updated : 2019-10-07 12:06:25
         */

        private String uid;
        private String reg_id;
        private String fname;
        private String lname;
        private String email;
        private String mobile_no;
        private String state;
        private String city;
        private String area;
        private String pincode;
        private String service_id;
        private String service_name;
        private String no_of_employees;
        private String verify_email;
        private String image;
        private String user_active;
        private String created;
        private String updated;

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

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getNo_of_employees() {
            return no_of_employees;
        }

        public void setNo_of_employees(String no_of_employees) {
            this.no_of_employees = no_of_employees;
        }

        public String getVerify_email() {
            return verify_email;
        }

        public void setVerify_email(String verify_email) {
            this.verify_email = verify_email;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUser_active() {
            return user_active;
        }

        public void setUser_active(String user_active) {
            this.user_active = user_active;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}
