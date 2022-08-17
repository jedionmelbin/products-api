package com.jedionmelbin.api.sales.dto;

import com.jedionmelbin.api.sales.domain.common.FeatureStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Builder
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String description;
    private FeatureStatus featureStatus;
    private Double capacity;
    private String packaging;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private String lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
}
