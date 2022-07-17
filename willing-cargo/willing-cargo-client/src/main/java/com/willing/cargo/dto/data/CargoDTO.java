package com.willing.cargo.dto.data;

import lombok.Data;

/**
 * @author Szg
 * @ClassName:
 * @Description:
 * @date 2022/7/317:24
 */
@Data
public class CargoDTO {
    private String id;
    private String senderPhone;
    private String description;
    private String originLocationName;
    private String destinationLocationCode;
    private String destinationLocationName;
}
