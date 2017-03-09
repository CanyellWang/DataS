package com.wchy.vo;


public class VshopDistributionItemVo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2245073718798546231L;
    private Long id;
	private String newItemId; // 商品id
	private Long vshopId; // 微店编码
	private Long newUserId;//用户Id
	private String pshopId;
	private Integer status;
	
	public Long getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(Long newUserId) {
		this.newUserId = newUserId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNewItemId() {
		return newItemId;
	}
	public void setNewItemId(String newItemId) {
		this.newItemId = newItemId;
	}
	public Long getVshopId() {
		return vshopId;
	}
	public void setVshopId(Long vshopId) {
		this.vshopId = vshopId;
	}
    public String getPshopId() {
        return pshopId;
    }
    public void setPshopId(String pshopId) {
        this.pshopId = pshopId;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
	
}
