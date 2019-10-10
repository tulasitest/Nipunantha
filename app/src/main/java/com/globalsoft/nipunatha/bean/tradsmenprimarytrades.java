package com.globalsoft.nipunatha.bean;

import java.util.List;

public class tradsmenprimarytrades {

    /**
     * status : valid
     * message : records found
     * response : [{"id":"1","name":"Carpenter","status":"1"},{"id":"2","name":"Carpet Cleaner","status":"1"},{"id":"3","name":"Duplicate Key Maker","status":"1"},{"id":"4","name":"Electrician","status":"1"},{"id":"5","name":"Painter","status":"1"},{"id":"6","name":"Plumber","status":"1"}]
     */

    private String status;
    private String message;
    private List<ResponseBean> response;

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

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 1
         * name : Carpenter
         * status : 1
         */

        private String id;
        private String name;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
