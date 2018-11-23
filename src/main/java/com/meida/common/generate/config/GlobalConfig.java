package com.meida.common.generate.config;

/**
 * <p>
 * 全局配置
 * </p>
 *
 * @author hubin
 * @since 2016-12-02
 */
public class GlobalConfig {

    /**
     * 生成java文件的输出目录【默认 D 盘根目录】
     */
    private String outputDir = "D://";

    /**
     * 生成jsp文件的输出目录【默认 D 盘根目录】
     */
    private String outputJspDir = "D://";
    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;

    /**
     * 是否打开输出目录
     */
    private boolean open = false;

    /**
     * 开发人员
     */
    private String author;

    /**
     * 各层文件名称方式，例如： %Action 生成 UserAction
     */
    private String poName;
    private String voName;
    private String daoName;
    private String daoImplName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private String managerListName;
    private String addOrUpdateName;

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
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

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
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

	public String getOutputJspDir() {
		return outputJspDir;
	}

	public void setOutputJspDir(String outputJspDir) {
		this.outputJspDir = outputJspDir;
	}

}
