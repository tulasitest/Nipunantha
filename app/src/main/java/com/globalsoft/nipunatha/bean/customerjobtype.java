package com.globalsoft.nipunatha.bean;

import java.util.List;

public class customerjobtype {

    /**
     * status : valid
     * message : records found
     * response : [{"id":"2","name":"Emergency House Wiring","status":"1"},{"id":"3","name":"Internal Lighting","status":"1"},{"id":"4","name":"Washing Machine Repair","status":"1"},{"id":"5","name":"Fridge/freezer Repair","status":"1"},{"id":"6","name":"Tv Repair","status":"1"},{"id":"7","name":"Others","status":"1"}]
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
         * id : 2
         * name : Emergency House Wiring
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
