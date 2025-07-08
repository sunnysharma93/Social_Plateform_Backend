package com.zosh.social.media.plateformw.reponse;

public class ApiResponse {

    private String messgae;
    private boolean status;

    public ApiResponse() {
    }

    public ApiResponse(String messgae, boolean status) {
        this.messgae = messgae;
        this.status = status;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
