package com.wchy.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangchangye on 2017/3/9.
 *
 * @Author wangchangye
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7150347895001604498L;

    private Long id;//主键ID

    private Integer version = 0;//版本号

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private Integer isDelete=0;//删除标志  0未删除1已删除

    private String createOperator;//创建人名称

    private String updateOperator;//修改人名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }


    /**
     * @return the isDelete
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the createOperator
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * @param createOperator the createOperator to set
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    /**
     * @return the updateOperator
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * @param updateOperator the updateOperator to set
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }



}

