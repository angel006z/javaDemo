package com.meida.generatebackend.config.builder;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.meida.generatebackend.config.ConstVal;
import com.meida.generatebackend.config.DataSourceConfig;
import com.meida.generatebackend.config.GlobalConfig;
import com.meida.generatebackend.config.PackageConfig;
import com.meida.generatebackend.config.StrategyConfig;
import com.meida.generatebackend.config.TemplateConfig;
import com.meida.generatebackend.config.po.TableField;
import com.meida.generatebackend.config.po.TableInfo;
import com.meida.generatebackend.config.rules.DbType;
import com.meida.generatebackend.config.rules.NamingStrategy;
import com.meida.generatebackend.config.rules.QuerySQL;
import com.meida.common.util.StringUtils;

/**
 * 配置汇总 传递给文件生成工具
 */
public class ConfigBuilder {

    /**
     * 模板路径配置信息
     */
    private final TemplateConfig template;
    /**
     * SQL连接
     */
    private Connection connection;
    /**
     * SQL语句类型
     */
    private QuerySQL querySQL;
    private String superModelClass;
    private String superModelVoClass;
    private String superDaoClass;
    private String superDaoImplClass;
    /**
     * service超类定义
     */
    private String superServiceClass;
    private String superServiceImplClass;
    private String superControllerClass;
    /**
     * 数据库表信息
     */
    private List<TableInfo> tableInfoList;
    /**
     * 包配置详情
     */
    private Map<String, String> packageInfo;
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;
    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;

    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    /**
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     * @throws Exception
     */
    public ConfigBuilder(PackageConfig packageConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                         TemplateConfig template, GlobalConfig globalConfig) throws Exception {
        // 全局配置
        if (null == globalConfig) {
            this.globalConfig = new GlobalConfig();
        } else {
            this.globalConfig = globalConfig;
        }
        // 包配置
        if (null == packageConfig) {
            handlerPackage(this.globalConfig.getOutputDir(), this.globalConfig.getOutputJspDir(), new PackageConfig());
        } else {
            handlerPackage(this.globalConfig.getOutputDir(), this.globalConfig.getOutputJspDir(), packageConfig);
        }
        handlerDataSource(dataSourceConfig);
        // 策略配置
        if (null == strategyConfig) {
            this.strategyConfig = new StrategyConfig();
        } else {
            this.strategyConfig = strategyConfig;
        }
        handlerStrategy(this.strategyConfig);
        // 模板配置
        if (null == template) {
            this.template = new TemplateConfig();
        } else {
            this.template = template;
        }
    }

    // ************************ 曝露方法 BEGIN*****************************

    /**
     * 所有包配置信息
     *
     * @return 包配置
     */
    public Map<String, String> getPackageInfo() {
        return packageInfo;
    }

    /**
     * 所有路径配置
     *
     * @return 路径配置
     */
    public Map<String, String> getPathInfo() {
        return pathInfo;
    }

    public String getSuperModelClass() {
        return superModelClass;
    }

    public String getSuperModelVoClass() {
        return superModelVoClass;
    }

    public String getSuperDaoClass() {
        return superDaoClass;
    }

    public String getSuperDaoImplClass() {
        return superDaoImplClass;
    }

    /**
     * 获取超类定义
     *
     * @return 完整超类名称
     */
    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    /**
     * 表信息
     *
     * @return 所有表信息
     */
    public List<TableInfo> getTableInfoList() {
        return tableInfoList;
    }

    /**
     * 模板路径配置信息
     *
     * @return 所以模板路径配置信息
     */
    public TemplateConfig getTemplate() {
        return template == null ? new TemplateConfig() : template;
    }

    // ****************************** 曝露方法 END**********************************

    /**
     * 处理包配置
     *
     * @param config PackageConfig
     */
    private void handlerPackage(String outputDir, String outputJspDir, PackageConfig config) {
        packageInfo = new HashMap<String, String>();
        packageInfo.put(ConstVal.MODULENAME, config.getModuleName());
        packageInfo.put(ConstVal.PO, joinPackage(config.getParent(), config.getPo()));
        packageInfo.put(ConstVal.VO, joinPackage(config.getParent(), config.getVo()));
        packageInfo.put(ConstVal.DTO, joinPackage(config.getParent(), config.getDto()));
        packageInfo.put(ConstVal.DAO, joinPackage(config.getParent(), config.getDao()));
        packageInfo.put(ConstVal.DAOMAPPING, joinPackage(config.getParent(), config.getDaoMapping()));
        packageInfo.put(ConstVal.DAOIMPL, joinPackage(config.getParent(), config.getDaoImpl()));
        packageInfo.put(ConstVal.SERIVCE, joinPackage(config.getParent(), config.getService()));
        packageInfo.put(ConstVal.SERVICEIMPL, joinPackage(config.getParent(), config.getServiceImpl()));
        packageInfo.put(ConstVal.CONTROLLER, joinPackage(config.getParent(), config.getController()));
        packageInfo.put(ConstVal.MANAGER, config.getManagerList());
        packageInfo.put(ConstVal.ADDORUPDATE, config.getAddOrUpdate());
        packageInfo.put(ConstVal.BASEURL, config.getBaseUrl());
        packageInfo.put(ConstVal.JSPURL, transPath(config.getModuleJspName()));

        pathInfo = new HashMap<String, String>();
        pathInfo.put(ConstVal.PO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.PO)));
        pathInfo.put(ConstVal.VO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.VO)));
        pathInfo.put(ConstVal.LISTDTO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DTO)));
        pathInfo.put(ConstVal.LISTPARAMDTO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DTO)));
        pathInfo.put(ConstVal.SUBMITDTO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DTO)));
        pathInfo.put(ConstVal.SUBMITPARAMDTO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DTO)));
        pathInfo.put(ConstVal.DAO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DAO)));
        pathInfo.put(ConstVal.DAOMAPPING_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DAOMAPPING)));
        pathInfo.put(ConstVal.DAOIMPL_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DAOIMPL)));
        pathInfo.put(ConstVal.SERIVCE_PATH, joinPath(outputDir, packageInfo.get(ConstVal.SERIVCE)));
        pathInfo.put(ConstVal.SERVICEIMPL_PATH, joinPath(outputDir, packageInfo.get(ConstVal.SERVICEIMPL)));
        pathInfo.put(ConstVal.CONTROLLER_PATH, joinPath(outputDir, packageInfo.get(ConstVal.CONTROLLER)));

        String managerlist = "";
        String addOrUpdate = "";
        if (StringUtils.isEmpty(config.getManagerList())) {
            managerlist = config.getParentJsp() + config.getManagerList();
        } else {
            managerlist = config.getParentJsp() + File.separator + config.getManagerList();
        }
        if (StringUtils.isEmpty(config.getAddOrUpdate())) {
            addOrUpdate = config.getParentJsp() + config.getAddOrUpdate();
        } else {
            addOrUpdate = config.getParentJsp() + File.separator + config.getAddOrUpdate();
        }
        pathInfo.put(ConstVal.MANAGERLIST_PATH, outputJspDir + managerlist);
        pathInfo.put(ConstVal.ADDORPDATE_PATH, outputJspDir + addOrUpdate);
    }

    /**
     * 处理数据源配置
     *
     * @param config DataSourceConfig
     * @throws Exception
     */
    private void handlerDataSource(DataSourceConfig config) throws Exception {
        connection = config.getConn();
        querySQL = getQuerySQL(config.getDbType());
    }

    /**
     * 处理数据库表 加载数据库表、列、注释相关数据集
     *
     * @param config StrategyConfig
     */
    private void handlerStrategy(StrategyConfig config) {
        processTypes(config);
        tableInfoList = getTablesInfo(config);
    }

    /**
     * 处理superClassName,IdClassType,IdStrategy配置
     *
     * @param config 策略配置
     */
    private void processTypes(StrategyConfig config) {
        if (StringUtils.isEmpty(config.getSuperServiceClass())) {
            superServiceClass = ConstVal.SUPERD_SERVICE_CLASS;
        } else {
            superServiceClass = config.getSuperServiceClass();
        }
        if (StringUtils.isEmpty(config.getSuperServiceImplClass())) {
            superServiceImplClass = ConstVal.SUPERD_SERVICEIMPL_CLASS;
        } else {
            superServiceImplClass = config.getSuperServiceImplClass();
        }
        if (StringUtils.isEmpty(config.getSuperDaoClass())) {
            superDaoClass = ConstVal.SUPERD_DAO_CLASS;
        } else {
            superDaoClass = config.getSuperDaoClass();
        }
        if (StringUtils.isEmpty(config.getSuperDaoImplClass())) {
            superDaoImplClass = ConstVal.SUPERD_DAOIMPL_CLASS;
        } else {
            superDaoImplClass = config.getSuperDaoImplClass();
        }
        superModelClass = config.getSuperModelClass();
        superModelVoClass = config.getSuperModelVoClass();
        superControllerClass = config.getSuperControllerClass();
    }

    /**
     * 处理表对应的类名称
     *
     * @param tableList   表名称
     * @param strategy    命名策略
     * @param tablePrefix
     * @return 补充完整信息后的表
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, NamingStrategy strategy, String tablePrefix, String tableBehind) {
        for (TableInfo tableInfo : tableList) {
            if (!StringUtils.isEmpty(tableInfo.getComment())) {
                String comment = tableInfo.getComment();
                if (comment.substring(comment.length() - 1, comment.length()).equals("表")) {
                    comment = comment.substring(0, comment.length() - 1);
                    tableInfo.setBaseComment(comment);
                } else {
                    tableInfo.setBaseComment(comment);
                }
            }
            tableInfo.setBaseName(NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix, tableBehind)));
            tableInfo.setPoName(NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix, tableBehind)));
            tableInfo.setVoName(NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix, tableBehind)));
            //dao
            if (!StringUtils.isEmpty(globalConfig.getDaoName())) {
                tableInfo.setDaoName(String.format(globalConfig.getDaoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setDaoName(tableInfo.getPoName() + ConstVal.DAO);
            }
            //daoMapping
            if (!StringUtils.isEmpty(globalConfig.getDaoMappingName())) {
                tableInfo.setDaoMappingName(String.format(globalConfig.getDaoMappingName(), tableInfo.getPoName()));
            } else {
                tableInfo.setDaoMappingName(tableInfo.getPoName() + ConstVal.DAO);
            }
            //daoImpl
            if (!StringUtils.isEmpty(globalConfig.getDaoImplName())) {
                tableInfo.setDaoImplName(String.format(globalConfig.getDaoImplName(), tableInfo.getPoName()));
            } else {
                tableInfo.setDaoImplName(tableInfo.getPoName() + ConstVal.DAOIMPL);
            }
            //service
            if (!StringUtils.isEmpty(globalConfig.getServiceName())) {
                tableInfo.setServiceName(String.format(globalConfig.getServiceName(), tableInfo.getPoName()));
            } else {
                tableInfo.setServiceName(tableInfo.getPoName() + ConstVal.SERIVCE);
            }
            //serviceImpl
            if (!StringUtils.isEmpty(globalConfig.getServiceImplName())) {
                tableInfo.setServiceImplName(String.format(globalConfig.getServiceImplName(), tableInfo.getPoName()));
            } else {
                tableInfo.setServiceImplName(tableInfo.getPoName() + ConstVal.SERVICEIMPL);
            }
            //controller
            if (!StringUtils.isEmpty(globalConfig.getControllerName())) {
                tableInfo.setControllerName(String.format(globalConfig.getControllerName(), tableInfo.getPoName()));
            } else {
                tableInfo.setControllerName(tableInfo.getPoName() + ConstVal.CONTROLLER);
            }
            //vo
            if (!StringUtils.isEmpty(globalConfig.getVoName())) {
                tableInfo.setVoName(String.format(globalConfig.getVoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setVoName(tableInfo.getPoName() + ConstVal.VO);
            }
            //listDto
            if (!StringUtils.isEmpty(globalConfig.getListDtoName())) {
                tableInfo.setListDtoName(String.format(globalConfig.getListDtoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setListDtoName(tableInfo.getPoName() + ConstVal.LISTDTO);
            }
            //listParamDto
            if (!StringUtils.isEmpty(globalConfig.getListParamDtoName())) {
                tableInfo.setListParamDtoName(String.format(globalConfig.getListParamDtoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setListParamDtoName(tableInfo.getPoName() + ConstVal.LISTPARAMDTO);
            }
            //submitDto
            if (!StringUtils.isEmpty(globalConfig.getSubmitDtoName())) {
                tableInfo.setSubmitDtoName(String.format(globalConfig.getSubmitDtoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setSubmitDtoName(tableInfo.getPoName() + ConstVal.SUBMITDTO);
            }
            //submitParamDto
            if (!StringUtils.isEmpty(globalConfig.getSubmitParamDtoName())) {
                tableInfo.setSubmitParamDtoName(String.format(globalConfig.getSubmitParamDtoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setSubmitParamDtoName(tableInfo.getPoName() + ConstVal.SUBMITPARAMDTO);
            }
            //ManagerList
            if (!StringUtils.isEmpty(globalConfig.getManagerListName())) {
                tableInfo.setManagerListName(String.format(globalConfig.getManagerListName(), tableInfo.getPoName()));
            } else {
                tableInfo.setManagerListName(NamingStrategy.capitalToLow(tableInfo.getPoName()) + ConstVal.MANAGER);
            }
            //AddOrUpdate
            if (!StringUtils.isEmpty(globalConfig.getAddOrUpdateName())) {
                tableInfo.setAddOrUpdateName(String.format(globalConfig.getAddOrUpdateName(), tableInfo.getPoName()));
            } else {
                tableInfo.setAddOrUpdateName(ConstVal.ADDORUPDATE + tableInfo.getPoName());
            }
            //po
            if (!StringUtils.isEmpty(globalConfig.getPoName())) {
                tableInfo.setPoName(String.format(globalConfig.getPoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setPoName(tableInfo.getPoName() + ConstVal.PO);
            }
        }
        return tableList;
    }

    /**
     * 获取所有的数据库表信息
     *
     * @return 表信息
     */
    private List<TableInfo> getTablesInfo(StrategyConfig config) {
        boolean isInclude = (null != config.getInclude() && config.getInclude().length > 0);
        boolean isExclude = (null != config.getExclude() && config.getExclude().length > 0);
        if (isInclude && isExclude) {
            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        List<TableInfo> tableList = new ArrayList<TableInfo>();
        Set<String> notExistTables = new HashSet<String>();
        NamingStrategy strategy = config.getNaming();
        NamingStrategy fieldStrategy = config.getFieldNaming();
        PreparedStatement pstate = null;
        try {
            pstate = connection.prepareStatement(querySQL.getTableCommentsSql());
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                String tableName = results.getString(querySQL.getTableName());
                if (!StringUtils.isEmpty(tableName)) {
                    String tableComment = results.getString(querySQL.getTableComment());
                    TableInfo tableInfo = new TableInfo();
                    if (isInclude) {
                        for (String includeTab : config.getInclude()) {
                            if (includeTab.equalsIgnoreCase(tableName)) {
                                tableInfo.setName(tableName);
                                tableInfo.setComment(tableComment);
                            } else {
                                notExistTables.add(includeTab);
                            }
                        }
                    } else if (isExclude) {
                        for (String excludeTab : config.getExclude()) {
                            if (!excludeTab.equalsIgnoreCase(tableName)) {
                                tableInfo.setName(tableName);
                                tableInfo.setComment(tableComment);
                            } else {
                                notExistTables.add(excludeTab);
                            }
                        }
                    } else {
                        tableInfo.setName(tableName);
                        tableInfo.setComment(tableComment);
                    }
                    if (!StringUtils.isEmpty(tableInfo.getName())) {
                        List<TableField> fieldList = getListFields(tableInfo.getName(), fieldStrategy);
                        tableInfo.setFields(fieldList);
                        tableList.add(tableInfo);
                    }
                } else {
                    System.err.println("当前数据库为空！！！");
                }
            }
            // 将已经存在的表移除
            for (TableInfo tabInfo : tableList) {
                notExistTables.remove(tabInfo.getName());
            }
            if (notExistTables.size() > 0) {
                System.err.println("表 " + notExistTables + " 在数据库中不存在！！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (pstate != null) {
                    pstate.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return processTable(tableList, strategy, config.getTablePrefix(), config.getTableBehind());
    }

    /**
     * 将字段信息与表信息关联
     *
     * @param tableName 表名称
     * @param strategy  命名策略
     * @return 表信息
     */
    private List<TableField> getListFields(String tableName, NamingStrategy strategy) throws SQLException {
        boolean havedId = false;

        PreparedStatement pstate = connection.prepareStatement(String.format(querySQL.getTableFieldsSql(), tableName));
        ResultSet results = pstate.executeQuery();

        List<TableField> fieldList = new ArrayList<TableField>();
//        int i = 0;
//        String s = "";
//        while(results.next()){
//            s += results.getObject(i) + "#";
//            i++;
//        }
//        System.out.println(s);
        while (results.next()) {
            TableField field = new TableField();
            String key = results.getString(querySQL.getFieldKey());
            // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
            boolean isId = !StringUtils.isEmpty(key) && key.toUpperCase().equals("PRI");
            // 处理ID
            if (isId && !havedId) {
                field.setKeyFlag(true);
                //出来主键是否自动增长
                //String isAuto = results.getString(querySQL.getIsAuto());
                boolean isAutoKey = false;//!StringUtils.isEmpty(isAuto) && key.toUpperCase().equals("1");
                if (isAutoKey && !havedId) {
                    field.setAuto(true);
                } else {
                    field.setAuto(false);
                }
                havedId = true;
            } else {
                field.setKeyFlag(false);
            }
            // 处理其它信息
            field.setName(results.getString(querySQL.getFieldName()));
            if (strategyConfig.includeSuperModelColumns(field.getName())) {
                // 跳过公共字段
                continue;
            }
            field.setType(results.getString(querySQL.getFieldType()));
            field.setPropertyName(processName(field.getName(), strategy));
            field.setPropertyType(processFiledType(field.getType()));
            field.setComment(results.getString(querySQL.getFieldComment()));
            fieldList.add(field);
        }
        return fieldList;
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isEmpty(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!endsWith(parentDir, File.separator, false)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }

    /**
     * \转为/
     *
     * @return 连接后的路径
     */
    private String transPath(String pathName) {
        if (StringUtils.isEmpty(pathName)) {
            return pathName;
        }
        pathName = pathName.replaceAll("\\" + File.separator, "/");
        return pathName;
    }

    /**
     * 连接父子包名
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    private String joinPackage(String parent, String subPackage) {
        if (StringUtils.isEmpty(parent)) {
            return subPackage;
        }
        return parent + "." + subPackage;
    }

    /**
     * 处理字段类型
     *
     * @return 转换成JAVA包装类型
     */
    private String processFiledType(String type) {
        if (QuerySQL.SQLSERVER == querySQL) {
            return processSqlserverType(type);
        } else if (QuerySQL.MYSQL == querySQL) {
            return processMySqlType(type);
        } else if (QuerySQL.ORACLE == querySQL) {
            return processOracleType(type);
        }
        return null;
    }

    /**
     * 处理字段名称
     *
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy) {
        return processName(name, strategy, null, null);
    }

    /**
     * 处理字段名称
     *
     * @param name
     * @param strategy
     * @param tablePrefix
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy, String tablePrefix, String tableBehind) {
        String propertyName = "";
        if (strategy == NamingStrategy.remove_all_and_camel) {
            propertyName = NamingStrategy.removeAllAndCamel(name, tablePrefix, tableBehind);
        } else if (strategy == NamingStrategy.remove_prefix_and_camel) {
            propertyName = NamingStrategy.removePrefixAndCamel(name, tablePrefix);
        } else if (strategy == NamingStrategy.underline_to_camel) {
            propertyName = NamingStrategy.underlineToCamel(name);
        } else if (strategy == NamingStrategy.remove_prefix) {
            propertyName = NamingStrategy.removePrefix(name, tablePrefix);
        } else {
            propertyName = name;
        }
        return propertyName;
    }

    /**
     * SQLSERVER字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processSqlserverType(String type) {
        String t = type.toLowerCase();
        if (t.contains("char") || t.contains("text") || t.contains("variant")) {
            return "String";
        } else if (t.contains("bigint")) {
            return "Long";
        } else if (t.contains("int")) {
            return "Integer";
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            return "Date";
        } else if (t.contains("text")) {
            return "String";
        } else if (t.contains("bit")) {
            return "Boolean";
        } else if (t.contains("decimal")) {
            return "BigDecimal";
        } else if (t.contains("blob")) {
            return "byte[]";
        } else if (t.contains("float")) {
            return "Float";
        } else if (t.contains("double") || t.contains("money")) {
            return "Double";
        } else if (t.contains("json") || t.contains("enum") || t.contains("varchar")) {
            return "String";
        } else if (t.contains("smallint")) {
            return "Short";
        }
        return "String";
    }

    /**
     * MYSQL字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processMySqlType(String type) {
        String t = type.toLowerCase();
        if (t.contains("char") || t.contains("text")) {
            return "String";
        } else if (t.contains("bigint")) {
            return "Long";
        } else if (t.contains("int")) {
            return "Integer";
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            return "Date";
        } else if (t.contains("text")) {
            return "String";
        } else if (t.contains("bit")) {
            return "Boolean";
        } else if (t.contains("decimal")) {
            return "BigDecimal";
        } else if (t.contains("blob")) {
            return "byte[]";
        } else if (t.contains("float")) {
            return "Float";
        } else if (t.contains("double")) {
            return "Double";
        } else if (t.contains("json") || t.contains("enum")) {
            return "String";
        }
        return "String";
    }

    /**
     * ORACLE字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processOracleType(String type) {
        String t = type.toUpperCase();
        if (t.contains("CHAR")) {
            return "String";
        } else if (t.contains("DATE") || t.contains("TIMESTAMP")) {
            return "Date";
        } else if (t.contains("NUMBER")) {
            if (t.matches("NUMBER\\(+\\d{1}+\\)")) {
                return "Integer";
            } else if (t.matches("NUMBER\\(+\\d{2}+\\)")) {
                return "Long";
            }
            return "Double";
        } else if (t.contains("FLOAT")) {
            return "Float";
        } else if (t.contains("BLOB")) {
            return "Object";
        } else if (t.contains("RAW")) {
            return "byte[]";
        }
        return "String";
    }

    /**
     * 获取当前的SQL类型
     *
     * @return DB类型
     */
    private QuerySQL getQuerySQL(DbType dbType) {
        for (QuerySQL qs : QuerySQL.values()) {
            if (qs.getDbType().equals(dbType.getValue())) {
                return qs;
            }
        }
        return QuerySQL.MYSQL;
    }

    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }

    public void setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str == null || suffix == null) {
            return (str == null && suffix == null);
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        int strOffset = str.length() - suffix.length();
        return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
    }
}
