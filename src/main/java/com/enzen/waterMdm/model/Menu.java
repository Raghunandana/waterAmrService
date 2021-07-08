package com.enzen.waterMdm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "menumaster")
public class Menu implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "menumaster_menuid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "menumaster_menuid_seq", sequenceName = "menumaster_menuid_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "code", nullable = true)
	private String code;
	
	@Column(name = "description")
	private String desc;
	
	@Column(name = "parentmenuid")
	private Integer parentId;
	
	@Column(name = "haschildren")
	private boolean hasChild;
	
	@Column(name = "menuorder")
	private Integer menuOrder;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "detailtext")
	private String detailText;
	
	@Column(name = "showinmenu")
	private boolean showInMenu;
	
	@Column(name = "menulevel")
	private Integer menuLevel;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "iconpath")
	private String iconPath;
	
	@Column(name = "showonlyicon")
	private boolean showOnlyIcon;
	
	public Menu() {
		
	}

	public Menu(Integer id, String code, String desc, Integer parentId, boolean hasChild, Integer menuOrder, String url, 
			String detailText, boolean showInMenu, Integer menuLevel, boolean active, String iconPath, boolean showOnlyIcon) {
		
		super();
		this.id = id;
		this.code = code;
		this.desc = desc;
		this.parentId = parentId;
		this.hasChild = hasChild;
		this.menuOrder = menuOrder;
		this.url = url;
		this.detailText = detailText;
		this.showInMenu = showInMenu;
		this.menuLevel = menuLevel;
		this.active = active;
		this.iconPath = iconPath;
		this.showOnlyIcon = showOnlyIcon;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDetailText() {
		return detailText;
	}

	public void setDetailText(String detailText) {
		this.detailText = detailText;
	}

	public boolean isShowInMenu() {
		return showInMenu;
	}

	public void setShowInMenu(boolean showInMenu) {
		this.showInMenu = showInMenu;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public boolean isShowOnlyIcon() {
		return showOnlyIcon;
	}

	public void setShowOnlyIcon(boolean showOnlyIcon) {
		this.showOnlyIcon = showOnlyIcon;
	}


}
