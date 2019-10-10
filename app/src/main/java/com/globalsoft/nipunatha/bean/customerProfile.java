package com.globalsoft.nipunatha.bean;

public class customerProfile {


    /**
     * status : valid
     * message : User record is found
     * result : {"uid":"1","reg_id":"CUS160857","fname":"charishma","lname":"p","email":"charishma@gmail.com","mobile_no":"9121421235","state":"Andhra Pradesh","city":"Garacharma","area":"Old Guntur","image":"http://labs.sslotus.com/services/uploads/customer/user.png","verify_email":"1","user_active":"1","created":"2019-03-16 05:33:21","updated":"2019-09-24 22:54:08"}
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
         * uid : 1
         * reg_id : CUS160857
         * fname : charishma
         * lname : p
         * email : charishma@gmail.com
         * mobile_no : 9121421235
         * state : Andhra Pradesh
         * city : Garacharma
         * area : Old Guntur
         * image : http://labs.sslotus.com/services/uploads/customer/user.png
         * verify_email : 1
         * user_active : 1
         * created : 2019-03-16 05:33:21
         * updated : 2019-09-24 22:54:08
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
        private String image;
        private String verify_email;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getVerify_email() {
            return verify_email;
        }

        public void setVerify_email(String verify_email) {
            this.verify_email = verify_email;
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
