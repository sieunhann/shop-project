package com.example.project.utils;

import com.example.project.entity.product.ProductEntity;
import com.example.project.entity.product.VariantEntity;

import java.util.List;
import java.util.Set;

public class Utils {
    public static String generateRoleString(List<String> roles) {
        // Convert List => Array
        String[] arrNames = roles.toArray(new String[0]);

        // Convert Array => String
        return String.join(", ", arrNames);
    }

    public static Double generateProductPrice(ProductEntity product){
        Set<VariantEntity> variants = product.getVariantEntities();
        List<VariantEntity> list = variants.stream().toList();
        return list.get(0).getPrice();
    }

}
