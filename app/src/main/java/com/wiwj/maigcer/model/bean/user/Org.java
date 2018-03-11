package com.wiwj.maigcer.model.bean.user;

import java.io.Serializable;

public class Org implements Serializable{
	private long id;
	private String name;
	private int areaId;
	private String areaName;
	private String type;
	private String uniquecode;
	private int parentId;
	private String parentIds;
	private String description;
	private AdditionalBean additional;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUniquecode() {
		return uniquecode;
	}

	public void setUniquecode(String uniquecode) {
		this.uniquecode = uniquecode;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AdditionalBean getAdditional() {
		return additional;
	}

	public void setAdditional(AdditionalBean additional) {
		this.additional = additional;
	}
	
	public class AdditionalBean implements Serializable{
		private OrgnizationBean orgnization;
		
		public OrgnizationBean getOrgnization() {
			return orgnization;
		}

		public void setOrgnization(OrgnizationBean orgnization) {
			this.orgnization = orgnization;
		}

		@Override
		public String toString() {
			return "AdditionalBean [orgnization=" + orgnization + "]";
		}

		public class OrgnizationBean implements Serializable{
			private long id;
			private String name;
			private int areaId;
			private String areaName;
			private String type;
			private String uniquecode;
			private int parentId;
			private String parentIds;
			private String description;
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public int getAreaId() {
				return areaId;
			}
			public void setAreaId(int areaId) {
				this.areaId = areaId;
			}
			public String getAreaName() {
				return areaName;
			}
			public void setAreaName(String areaName) {
				this.areaName = areaName;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getUniquecode() {
				return uniquecode;
			}
			public void setUniquecode(String uniquecode) {
				this.uniquecode = uniquecode;
			}
			public int getParentId() {
				return parentId;
			}
			public void setParentId(int parentId) {
				this.parentId = parentId;
			}
			public String getParentIds() {
				return parentIds;
			}
			public void setParentIds(String parentIds) {
				this.parentIds = parentIds;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			@Override
			public String toString() {
				return "OrgnizationBean [id=" + id + ", name=" + name
						+ ", areaId=" + areaId + ", areaName=" + areaName
						+ ", type=" + type + ", uniquecode=" + uniquecode
						+ ", parentId=" + parentId + ", parentIds=" + parentIds
						+ ", description=" + description + "]";
			}
			
		}
	}

	@Override
	public String toString() {
		return "Org [id=" + id + ", name=" + name + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", type=" + type + ", uniquecode="
				+ uniquecode + ", parentId=" + parentId + ", parentIds="
				+ parentIds + ", description=" + description + ", additional="
				+ additional + "]";
	}
	
}
