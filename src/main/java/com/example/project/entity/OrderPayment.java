package com.example.project.entity;

import java.util.stream.Stream;

public enum OrderPayment {
     UNPAID("Chưa thanh toán"),
     PAID("Đã thanh toán"),
     REFUNDED("Hoàn trả"),
     PARTIALLY_REFUND("Hoàn trả một phần");

     private String code;

     OrderPayment(String code) {
          this.code = code;
     }

     public String getCode() {
          return code;
     }

     public void setCode(String code) {
          this.code = code;
     }

     public static OrderPayment of(String status){
          return Stream.of(OrderPayment.values())
                  .filter(os -> os.getCode().equals(status))
                  .findFirst()
                  .orElseThrow(IllegalArgumentException::new);
     }
}
