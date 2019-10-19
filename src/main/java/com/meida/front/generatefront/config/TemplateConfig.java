package com.meida.front.generatefront.config;

public class TemplateConfig {

    private String po = ConstVal.TEMPLATE_PO;

    private String vo = ConstVal.TEMPLATE_VO;

    private String listDto = ConstVal.TEMPLATE_LISTDTO;

    private String listParamDto = ConstVal.TEMPLATE_LISTPARAMDTO;

    private String submitDto = ConstVal.TEMPLATE_SUBMITDTO;

    private String submitParamDto = ConstVal.TEMPLATE_SUBMITPARAMDTO;

    private String service = ConstVal.TEMPLATE_SERVICE;

    private String serviceImpl = ConstVal.TEMPLATE_SERVICEIMPL;

    private String dao = ConstVal.TEMPLATE_DAO;

    private String daoMapping = ConstVal.TEMPLATE_DAOMAPPING;

    private String daoImpl = ConstVal.TEMPLATE_DAOIMPL;

    private String controller = ConstVal.TEMPLATE_CONTROLLER;

    private String managerList = ConstVal.TEMPLATE_MANAGERLIST;
    
    private String addOrUpdate = ConstVal.TEMPLATE_ADDORUPDATE;

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

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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

    public String getListDto() {
        return listDto;
    }

    public void setListDto(String listDto) {
        this.listDto = listDto;
    }

    public String getListParamDto() {
        return listParamDto;
    }

    public void setListParamDto(String listParamDto) {
        this.listParamDto = listParamDto;
    }

    public String getSubmitDto() {
        return submitDto;
    }

    public void setSubmitDto(String submitDto) {
        this.submitDto = submitDto;
    }

    public String getSubmitParamDto() {
        return submitParamDto;
    }

    public void setSubmitParamDto(String submitParamDto) {
        this.submitParamDto = submitParamDto;
    }
}
