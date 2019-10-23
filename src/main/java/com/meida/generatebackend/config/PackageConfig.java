package com.meida.generatebackend.config;

import java.io.File;

import com.meida.common.util.StringUtils;

public class PackageConfig {

	/**
	 * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
	 */
	private String parent = "";

	/**
	 * 父包模块名。
	 */
	private String moduleName = null;

	/**
	 * Model PO 包名
	 */
	private String po = "po";
	/**
	 * Model VO 包名
	 */
	private String vo = "vo";
	/**
	 * Model DTO 包名
	 */
	private String dto = "dto";

	/**
	 * Service包名
	 */
	private String service = "service.inter";

	/**
	 * Service Impl包名
	 */
	private String serviceImpl = "service.impl";
	/**
	 * Mapper包名
	 */
	private String dao = "dao.inter";

	private String daoMapping = "dao.mapping";

	/**
	 * Mapper XML包名
	 */
	private String daoImpl = "dao.impl";

	/**
	 * Controller包名
	 */
	private String controller = "controller";

	/**
	 * url基本路径
	 */
	public String baseUrl = "";

	/**
	 * 父文件路径。如果为空，将下面子路径必须写全部， 否则就只需写子路径
	 */
	private String parentJsp = "";

	/**
	 * 父文件模块路径。
	 */
	private String moduleJspName = "";

	/**
	 * jsp managerList 文件名
	 */
	private String managerList = "";
	/**
	 * jsp addOrUpdate 文件名
	 */
	private String addOrUpdate = "";

	public String getParent() {
		if (!StringUtils.isEmpty(moduleName)) {
			return parent + "." + moduleName;
		}
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(String serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public String getController() {
		if (StringUtils.isEmpty(controller)) {
			return "controller";
		}
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getVo() {
		return vo;
	}

	public void setVo(String vo) {
		this.vo = vo;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getDaoMapping() {
		return daoMapping;
	}

	public void setDaoMapping(String daoMapping) {
		this.daoMapping = daoMapping;
	}

	public String getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(String daoImpl) {
		this.daoImpl = daoImpl;
	}

	public String getParentJsp() {
		if (StringUtils.isEmpty(moduleJspName)) {
			return parentJsp;
		} else {
			return parentJsp + File.separator + moduleJspName;
		}
	}

	public void setParentJsp(String parentJsp) {
		this.parentJsp = parentJsp;
	}

	public String getModuleJspName() {
		return moduleJspName;
	}

	public void setModuleJspName(String moduleJspName) {
		this.moduleJspName = moduleJspName;
	}

	public String getManagerList() {
		return managerList;
	}

	public void setManagerList(String managerList) {
		this.managerList = managerList;
	}

	public String getAddOrUpdate() {
		return addOrUpdate;
	}

	public void setAddOrUpdate(String addOrUpdate) {
		this.addOrUpdate = addOrUpdate;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDto() {
		return dto;
	}

	public void setDto(String dto) {
		this.dto = dto;
	}
}
