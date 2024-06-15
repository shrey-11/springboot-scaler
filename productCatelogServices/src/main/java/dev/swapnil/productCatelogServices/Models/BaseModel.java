package dev.swapnil.productCatelogServices.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdated;
    private boolean isDeleted;
}
