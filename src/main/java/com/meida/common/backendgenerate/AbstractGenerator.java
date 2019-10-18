package com.meida.common.backendgenerate;


import com.meida.common.backendgenerate.config.DataSourceConfig;
import com.meida.common.backendgenerate.config.GlobalConfig;
import com.meida.common.backendgenerate.config.PackageConfig;
import com.meida.common.backendgenerate.config.StrategyConfig;
import com.meida.common.backendgenerate.config.TemplateConfig;
import com.meida.common.backendgenerate.config.builder.ConfigBuilder;

/**
 * <p>
 * 插件基类，用于属性配置 设计成抽象类主要是用于后期可扩展，共享参数配置。
 * </p>
 */
public abstract class AbstractGenerator {

    protected ConfigBuilder config;
    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageInfo;
    /**
     * 模板 相关配置
     */
    private TemplateConfig template;
    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

    /**
     * 初始化配置
     * @throws Exception 
     */
    protected void initConfig() throws Exception {
        if (null == config) {
            config = new ConfigBuilder(packageInfo, dataSource, strategy, template, globalConfig);
        }
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
    }

    public StrategyConfig getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyConfig strategy) {
        this.strategy = strategy;
    }

    public PackageConfig getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageConfig packageInfo) {
        this.packageInfo = packageInfo;
    }

    public TemplateConfig getTemplate() {
        return template;
    }

    public void setTemplate(TemplateConfig template) {
        this.template = template;
    }

    public ConfigBuilder getConfig() {
        return config;
    }

    public void setConfig(ConfigBuilder config) {
        this.config = config;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

}
