package com.wchy.vo;


/**
 * @Description 店铺基本信息 
 * @author guowenbo
 * @date 2016年3月3日 下午4:40:14
 */
public class ShopInfoVo {

	private Long id;   
	private Long userId;    
	private Integer backgroundIndex;   
	private String name;   
	private String icon; 
	private Integer status;
	private String type;
	private String description;
	private String url;
	private Double vshopX;
	private Double vshopY;
	private String vshopArea;
	private Long provinceId;
	private Long cityId;
	private Long countyId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getBackgroundIndex() {
		return backgroundIndex;
	}
	public void setBackgroundIndex(Integer backgroundIndex) {
		this.backgroundIndex = backgroundIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVshopArea() {
		return vshopArea;
	}
	public void setVshopArea(String vshopArea) {
		this.vshopArea = vshopArea;
	}
	public Double getVshopX() {
		return vshopX;
	}
	public void setVshopX(Double vshopX) {
		this.vshopX = vshopX;
	}
	public Double getVshopY() {
		return vshopY;
	}
	public void setVshopY(Double vshopY) {
		this.vshopY = vshopY;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getCountyId() {
		return countyId;
	}
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	
}
