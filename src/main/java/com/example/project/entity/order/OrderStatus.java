package com.example.project.entity.order;

import java.util.stream.Stream;

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

    public static OrderStatus of(String status){
        return Stream.of(OrderStatus.values())
                .filter(os -> os.getCode().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
