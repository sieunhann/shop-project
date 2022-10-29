package com.example.project.entity;

public enum OrderStatus {
    NEW("Mới"), CONFIRMED("Xác thực"), CANCELED("Hủy") ,COMPLETED("Thành công");

    private String code;

    OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
