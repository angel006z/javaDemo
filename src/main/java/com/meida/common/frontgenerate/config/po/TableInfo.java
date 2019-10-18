package com.meida.common.frontgenerate.config.po;

import java.util.List;

import com.meida.common.util.StringUtils;

/**
 * 表信息，关联到当前字段信息
 */
public class TableInfo {

	private String name;
	private String baseName; // 表名首字母大写，去下化线
	private String comment; // 表备注
	private String baseComment; // 表备注，如果最后字符是表则去除

	private String poName;
	private String voName;
	private String dtoName;
	private String listDtoName;
	private String listParamDtoName;
	private String submitDtoName;
	private String submitParamDtoName;

	private String daoName;
	private String daoMappingName;
	private String daoImplName;
	private String serviceName;
	private String serviceImplName;
	private String controllerName;

	private String managerListName; // 列表jsp
	private String addOrUpdateName; // 添加修改jsp

	private List<TableField> fields;
	private String fieldNames;
	private boolean hasDate;
	private boolean hasBigDecimal;

	public String getDtoName() {
		return dtoName;
	}

	public void setDtoName(String dtoName) {
		this.dtoName = dtoName;
	}


	public String getListDtoName() {
		return listDtoName;
	}

	public void setListDtoName(String listDtoName) {
		this.listDtoName = listDtoName;
	}

	public String getListParamDtoName() {
		return listParamDtoName;
	}

	public void setListParamDtoName(String listParamDtoName) {
		this.listParamDtoName = listParamDtoName;
	}

	public String getSubmitDtoName() {
		return submitDtoName;
	}

	public void setSubmitDtoName(String submitDtoName) {
		this.submitDtoName = submitDtoName;
	}

	public String getSubmitParamDtoName() {
		return submitParamDtoName;
	}

	public void setSubmitParamDtoName(String submitParamDtoName) {
		this.submitParamDtoName = submitParamDtoName;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPoName() {
		return poName;
	}

	public void setPoName(String poName) {
		this.poName = poName;
	}

	public String getVoName() {
		return voName;
	}

	public void setVoName(String voName) {
		this.voName = voName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getDaoMappingName() {
		return daoMappingName;
	}

	public void setDaoMappingName(String daoMappingName) {
		this.daoMappingName = daoMappingName;
	}

	public String getDaoImplName() {
		return daoImplName;
	}

	public void setDaoImplName(String daoImplName) {
		this.daoImplName = daoImplName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImplName() {
		return serviceImplName;
	}

	public void setServiceImplName(String serviceImplName) {
		this.serviceImplName = serviceImplName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public List<TableField> getFields() {
		return fields;
	}

	public void setFields(List<TableField> fields) {
		this.fields = fields;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getBaseComment() {
		return baseComment;
	}

	public void setBaseComment(String baseComment) {
		this.baseComment = baseComment;
	}

	public String getManagerListName() {
		return managerListName;
	}

	public void setManagerListName(String managerListName) {
		this.managerListName = managerListName;
	}

	public String getAddOrUpdateName() {
		return addOrUpdateName;
	}

	public void setAddOrUpdateName(String addOrUpdateName) {
		this.addOrUpdateName = addOrUpdateName;
	}

	/**
	 * 转换filed实体为xmlmapper中的basecolumn字符串信息
	 *
	 * @return
	 */
	public String getFieldNames() {
		if (StringUtils.isEmpty(fieldNames)) {
			StringBuilder names = new StringBuilder();
			for (int i = 0; i < fields.size(); i++) {
				TableField fd = fields.get(i);
				if (i == fields.size() - 1) {
					names.append(cov2col(fd));
				} else {
					names.append(cov2col(fd)).append(", ");
				}
			}
			fieldNames = names.toString();
		}
		return fieldNames;
	}

	/**
	 * 判断字段中是否包含日期类型
	 *
	 * @return 是否
	 */
	public boolean isHasDate() {
		for (TableField fieldInfo : fields) {
			if (fieldInfo.getPropertyType().equals("Date")) {
				hasDate = true;
				break;
			}
		}
		return hasDate;
	}

	/**
	 * 判断字段中是否包含BigDecimal类型
	 *
	 * @return 是否
	 */
	public boolean isHasBigDecimal() {
		for (TableField fieldInfo : fields) {
			if (fieldInfo.getPropertyType().equals("BigDecimal")) {
				hasBigDecimal = true;
				break;
			}
		}
		return hasBigDecimal;
	}

	/**
	 * mapper xml中的字字段添加as
	 *
	 * @param field 字段实体
	 * @return 转换后的信息
	 */
	private String cov2col(TableField field) {
		if (null != field) {
			return field.isConvert() ? field.getName() + " AS " + field.getPropertyName() : field.getName();
		}
		return "";
	}

}
