package com.zxcl.report.common.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 排序字段
 *
 * @author YAZUO)WU-TONG
 *
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRequestSort implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 5255059459988467968L;

    /** 排序字段 */
    private String field;

    /** 排序类型：desc、asc */
    private String type;

    /** 构造方法 */
    public RestRequestSort() {
        super();
    }
}
