package com.example.project.entity.order;

import java.util.stream.Stream;

public enum OrderFulfillment {
    UNFULFILLED("Chờ xử lý"),
    PARTIALLY_FULFILLED("Đang giao hàng"),
    DONE("Giao hàng thành công");
    // chưa, fulfill 1 phần, đã fulfill

    private String code;

    OrderFulfillment(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static OrderFulfillment of(String status){
        return Stream.of(OrderFulfillment.values())
                .filter(os -> os.getCode().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
