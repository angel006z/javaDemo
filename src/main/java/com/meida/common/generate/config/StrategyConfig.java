package com.meida.common.generate.config;

import com.meida.common.generate.config.rules.NamingStrategy;

/**
 * 策略配置项
 *
 */
public class StrategyConfig {

	/**
	 * 数据库表映射到实体的命名策略
	 */
	private NamingStrategy naming = NamingStrategy.nochange;

	private NamingStrategy fieldNaming;

	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 表后缀
	 */
	private String tableBehind;

	/**
	 * po自定义继承的Model类全称，带包名
	 */
	private String superModelClass;

	/**
	 * vo自定义继承的Model类全称，带包名
	 */
	private String superModelVoClass;

	/**
	 * 自定义基础的Model类，公共字段
	 */
	private String[] superModelColumns;
	/**
	 * 自定义继承的SubmitDto类全称，带包名
	 */
	private String superSubmitDtoClass = ConstVal.SUPERD_SUBMITDTO_CLASS;
	/**
	 * 自定义继承的SubmitParamDto类全称，带包名
	 */
	private String superSubmitParamDtoClass = ConstVal.SUPERD_SUBMITPARAMDTO_CLASS;

	/**
	 * 自定义继承的Dao类全称，带包名
	 */
	private String superDaoClass = ConstVal.SUPERD_DAO_CLASS;
	/**
	 * 自定义继承的DaoImpl类全称，带包名
	 */
	private String superDaoImplClass = ConstVal.SUPERD_DAOIMPL_CLASS;

	/**
	 * 自定义继承的Service类全称，带包名
	 */
	private String superServiceClass = ConstVal.SUPERD_SERVICE_CLASS;

	/**
	 * 自定义继承的ServiceImpl类全称，带包名
	 */
	private String superServiceImplClass = ConstVal.SUPERD_SERVICEIMPL_CLASS;

	/**
	 * 自定义继承的Controller类全称，带包名
	 */
	private String superControllerClass;

	/*
	 * 需要包含的表名（与exclude二选一配置）
	 */
	private String[] include = null;

	/**
	 * 需要排除的表名
	 */
	private String[] exclude = null;
	/**
	 * 【实体】是否生成字段常量（默认 false）<br>
	 * -----------------------------------<br>
	 * public static final String ID = "test_id";
	 */
	private boolean modelColumnConstant = false;

	/**
	 * 【实体】是否为构建者模型（默认 false）<br>
	 * -----------------------------------<br>
	 * public User setName(String name) { this.name = name; return this; }
	 */
	private boolean modelBuliderModel = false;

	public NamingStrategy getNaming() {
		return naming;
	}

	public void setNaming(NamingStrategy naming) {
		this.naming = naming;
	}

	public NamingStrategy getFieldNaming() {
		return fieldNaming;
	}

	public void setFieldNaming(NamingStrategy fieldNaming) {
		this.fieldNaming = fieldNaming;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getTableBehind() {
		return tableBehind;
	}

	public void setTableBehind(String tableBehind) {
		this.tableBehind = tableBehind;
	}

	public String getSuperModelClass() {
		return superModelClass;
	}

	public void setSuperModelClass(String superModelClass) {
		this.superModelClass = superModelClass;
	}

	public boolean includeSuperModelColumns(String fieldName) {
		if (null != superModelColumns) {
			for (String column : superModelColumns) {
				if (column.contains(fieldName)) {
					return true;
				}
			}
		}
		return false;
	}

	public String[] getSuperModelColumns() {
		return superModelColumns;
	}

	public void setSuperModelColumns(String[] superModelColumns) {
		this.superModelColumns = superModelColumns;
	}

	public String getSuperDaoClass() {
		return superDaoClass;
	}

	public void setSuperDaoClass(String superDaoClass) {
		this.superDaoClass = superDaoClass;
	}

	public String getSuperServiceClass() {
		return superServiceClass;
	}

	public void setSuperServiceClass(String superServiceClass) {
		this.superServiceClass = superServiceClass;
	}

	public String getSuperServiceImplClass() {
		return superServiceImplClass;
	}

	public void setSuperServiceImplClass(String superServiceImplClass) {
		this.superServiceImplClass = superServiceImplClass;
	}

	public String getSuperControllerClass() {
		return superControllerClass;
	}

	public void setSuperControllerClass(String superControllerClass) {
		this.superControllerClass = superControllerClass;
	}

	public String[] getInclude() {
		return include;
	}

	public void setInclude(String[] include) {
		this.include = include;
	}

	public String[] getExclude() {
		return exclude;
	}

	public void setExclude(String[] exclude) {
		this.exclude = exclude;
	}

	public boolean isModelColumnConstant() {
		return modelColumnConstant;
	}

	public void setModelColumnConstant(boolean modelColumnConstant) {
		this.modelColumnConstant = modelColumnConstant;
	}

	public boolean isModelBuliderModel() {
		return modelBuliderModel;
	}

	public void setModelBuliderModel(boolean modelBuliderModel) {
		this.modelBuliderModel = modelBuliderModel;
	}

	public String getSuperDaoImplClass() {
		return superDaoImplClass;
	}

	public void setSuperDaoImplClass(String superDaoImplClass) {
		this.superDaoImplClass = superDaoImplClass;
	}

	public String getSuperModelVoClass() {
		return superModelVoClass;
	}

	public void setSuperModelVoClass(String superModelVoClass) {
		this.superModelVoClass = superModelVoClass;
	}

	public String getSuperSubmitDtoClass() {
		return superSubmitDtoClass;
	}

	public void setSuperSubmitDtoClass(String superSubmitDtoClass) {
		this.superSubmitDtoClass = superSubmitDtoClass;
	}

	public String getSuperSubmitParamDtoClass() {
		return superSubmitParamDtoClass;
	}

	public void setSuperSubmitParamDtoClass(String superSubmitParamDtoClass) {
		this.superSubmitParamDtoClass = superSubmitParamDtoClass;
	}
}
