package com.meida.generatefront;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meida.generatefront.config.ConstVal;
import com.meida.generatefront.config.TemplateConfig;
import com.meida.generatefront.config.builder.ConfigBuilder;
import com.meida.generatefront.config.po.TableInfo;
import com.meida.common.util.StringUtils;


/**
 * 生成文件
 *
 */
public class AutoGenerator extends AbstractGenerator {

    private static final Logger logger = LoggerFactory.getLogger(AutoGenerator.class);

    /**
     * velocity引擎
     */
    private VelocityEngine engine;

    /**
     * 生成代码
     * @throws Exception 
     */
    public void execute() throws Exception {
        logger.debug("==========================准备生成文件...==========================");
        // 初始化配置
        initConfig();
        // 创建输出文件路径
        mkdirs(config.getPathInfo());
        // 获取上下文
        Map<String, VelocityContext> ctxData = analyzeData(config);
        // 循环生成文件
        for (Map.Entry<String, VelocityContext> ctx : ctxData.entrySet()) {
            batchOutput(ctx.getKey(), ctx.getValue());
        }
        // 打开输出目录
        if (config.getGlobalConfig().isOpen()) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + config.getGlobalConfig().getOutputDir());
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + config.getGlobalConfig().getOutputDir());
                    } else {
                        logger.debug("文件输出目录:" + config.getGlobalConfig().getOutputDir());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.debug("==========================文件生成完成！！！==========================");
    }

    /**
     * 分析数据
     *
     * @param config 总配置信息
     * @return 解析数据结果集
     */
    private Map<String, VelocityContext> analyzeData(ConfigBuilder config) {
        List<TableInfo> tableList = config.getTableInfoList();
        Map<String, String> packageInfo = config.getPackageInfo();
        Map<String, VelocityContext> ctxData = new HashMap<String, VelocityContext>();
        String superModelClass = getSuperClassName(config.getSuperModelClass());
        String superModelVoClass = getSuperClassName(config.getSuperModelVoClass());
        String superDaoClass = getSuperClassName(config.getSuperDaoClass());
        String superDaoImplClass = getSuperClassName(config.getSuperDaoImplClass());
        String superServiceClass = getSuperClassName(config.getSuperServiceClass());
        String superServiceImplClass = getSuperClassName(config.getSuperServiceImplClass());
        String superControllerClass = getSuperClassName(config.getSuperControllerClass());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        for (TableInfo tableInfo : tableList) {
            VelocityContext ctx = new VelocityContext();
            ctx.put("package", packageInfo);
            ctx.put("author", config.getGlobalConfig().getAuthor());
            ctx.put("date", date);
            ctx.put("table", tableInfo);
            ctx.put("po", tableInfo.getPoName());
            ctx.put("vo", tableInfo.getVoName());
            ctx.put("modelColumnConstant", config.getStrategyConfig().isModelColumnConstant());
            ctx.put("modelBuliderModel", config.getStrategyConfig().isModelBuliderModel());
            ctx.put("tabeAnnotation", !tableInfo.getPoName().toLowerCase().equals(tableInfo.getName().toLowerCase()));
            ctx.put("superModelClassPackage", config.getSuperModelClass());
            ctx.put("superModelClass", superModelClass);
            ctx.put("superModelVoClassPackage", config.getSuperModelVoClass());
            ctx.put("superModelVoClass", superModelVoClass);
            ctx.put("superDaoClassPackage", config.getSuperDaoClass());
            ctx.put("superDaoClass", superDaoClass);
            ctx.put("superDaoImplClassPackage", config.getSuperDaoImplClass());
            ctx.put("superDaoImplClass", superDaoImplClass);
            ctx.put("superServiceClassPackage", config.getSuperServiceClass());
            ctx.put("superServiceClass", superServiceClass);
            ctx.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
            ctx.put("superServiceImplClass", superServiceImplClass);
            ctx.put("superControllerClassPackage", config.getSuperControllerClass());
            ctx.put("superControllerClass", superControllerClass);
            ctxData.put(tableInfo.getPoName(), ctx);
        }
        return ctxData;
    }

    /**
     * 获取类名
     *
     * @param classPath
     * @return
     */
    private String getSuperClassName(String classPath) {
        if (StringUtils.isEmpty(classPath))
            return null;
        return classPath.substring(classPath.lastIndexOf(".") + 1);
    }

    /**
     * 处理输出目录
     *
     * @param pathInfo 路径信息
     */
    private void mkdirs(Map<String, String> pathInfo) {
        for (Map.Entry<String, String> entry : pathInfo.entrySet()) {
            File dir = new File(entry.getValue());
            if (!dir.exists()) {
                boolean result = dir.mkdirs();
                if (result) {
                    logger.debug("创建目录： [" + entry.getValue() + "]");
                }
            }
        }
    }

    /**
     * 合成上下文与模板
     *
     * @param context vm上下文
     */
    private void batchOutput(String poName, VelocityContext context) {
        try {
            TableInfo tableInfo = (TableInfo) context.get("table");
            Map<String, String> pathInfo = config.getPathInfo();
            String poFile = String.format((pathInfo.get(ConstVal.PO_PATH) + ConstVal.MODEL_NAME), poName);
            String voFile = String.format((pathInfo.get(ConstVal.VO_PATH) + ConstVal.MODEL_NAME), tableInfo.getVoName());
            String listDtoFile = String.format((pathInfo.get(ConstVal.LISTDTO_PATH) + ConstVal.MODEL_NAME), tableInfo.getListDtoName());
            String listParamDtoFile = String.format((pathInfo.get(ConstVal.LISTPARAMDTO_PATH) + ConstVal.MODEL_NAME), tableInfo.getListParamDtoName());
            String submitDtoFile = String.format((pathInfo.get(ConstVal.SUBMITDTO_PATH) + ConstVal.MODEL_NAME), tableInfo.getSubmitDtoName());
            String submitParamDtoFile = String.format((pathInfo.get(ConstVal.SUBMITPARAMDTO_PATH) + ConstVal.MODEL_NAME), tableInfo.getSubmitParamDtoName());

            String daoFile = String.format((pathInfo.get(ConstVal.DAO_PATH) + File.separator + tableInfo.getDaoName() + ConstVal.JAVA_SUFFIX), poName);
            String daoMappingFile = String.format((pathInfo.get(ConstVal.DAOMAPPING_PATH) + File.separator + tableInfo.getDaoMappingName() + ConstVal.XML_SUFFIX), poName);
            String daoimplFile = String.format((pathInfo.get(ConstVal.DAOIMPL_PATH) + File.separator + tableInfo.getDaoImplName() + ConstVal.JAVA_SUFFIX), poName);
            String serviceFile = String.format((pathInfo.get(ConstVal.SERIVCE_PATH) + File.separator + tableInfo.getServiceName() + ConstVal.JAVA_SUFFIX), poName);
            String serviceimplFile = String.format((pathInfo.get(ConstVal.SERVICEIMPL_PATH) + File.separator + tableInfo.getServiceImplName() + ConstVal.JAVA_SUFFIX), poName);
            String controllerFile = String.format((pathInfo.get(ConstVal.CONTROLLER_PATH) + File.separator + tableInfo.getControllerName() + ConstVal.JAVA_SUFFIX), poName);
            String managerlistFile = String.format((pathInfo.get(ConstVal.MANAGERLIST_PATH) + File.separator + tableInfo.getManagerListName() + ConstVal.JSP_SUFFIX), poName);
            String addorupfateFile = String.format((pathInfo.get(ConstVal.ADDORPDATE_PATH) + File.separator + tableInfo.getAddOrUpdateName() + ConstVal.JSP_SUFFIX), poName);

            TemplateConfig template = config.getTemplate();

            // 根据override标识来判断是否需要创建文件
            if (isCreate(poFile)) {
                vmToFile(context, template.getPo(), poFile);
            }

            if (isCreate(voFile)) {
                vmToFile(context, template.getVo(), voFile);
            }

            if (isCreate(listDtoFile)) {
                vmToFile(context, template.getListDto(), listDtoFile);
            }

            if (isCreate(listParamDtoFile)) {
                vmToFile(context, template.getListParamDto(), listParamDtoFile);
            }

            if (isCreate(submitDtoFile)) {
                vmToFile(context, template.getSubmitDto(), submitDtoFile);
            }

            if (isCreate(submitParamDtoFile)) {
                vmToFile(context, template.getSubmitParamDto(), submitParamDtoFile);
            }

            if (isCreate(daoFile)) {
                vmToFile(context, template.getDao(), daoFile);
            }

            if (isCreate(daoMappingFile)) {
                vmToFile(context, template.getDaoMapping(), daoMappingFile);
            }

//            if (isCreate(daoimplFile)) {
//                vmToFile(context, template.getDaoImpl(), daoimplFile);
//            }

            if (isCreate(serviceFile)) {
                vmToFile(context, template.getService(), serviceFile);
            }

            if (isCreate(serviceimplFile)) {
                vmToFile(context, template.getServiceImpl(), serviceimplFile);
            }

            if (isCreate(controllerFile)) {
                vmToFile(context, template.getController(), controllerFile);
            }

//            if (isCreate(managerlistFile)) {
//                vmToFile(context, template.getManagerList(), managerlistFile);
//            }
//            if (isCreate(addorupfateFile)) {
//                vmToFile(context, template.getAddOrUpdate(), addorupfateFile);
//            }
        } catch (IOException e) {
            logger.error("无法创建文件，请检查配置信息！", e);
        }
    }

    /**
     * 将模板转化成为文件
     *
     * @param context      内容对象
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     */
    private void vmToFile(VelocityContext context, String templatePath, String outputFile) throws IOException {
        VelocityEngine velocity = getVelocityEngine();
        Template template = velocity.getTemplate(templatePath, ConstVal.UTF8);
        FileOutputStream fos = new FileOutputStream(outputFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
        template.merge(context, writer);
        writer.close();
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }

    /**
     * 设置模版引擎，主要指向获取模版路径
     */
    private VelocityEngine getVelocityEngine() {
        if (engine == null) {
            Properties p = new Properties();
            p.setProperty(ConstVal.VM_LOADPATH_KEY, ConstVal.VM_LOADPATH_VALUE);
            //p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
            p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
            p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
            engine = new VelocityEngine(p);
        }
        return engine;
    }

    /**
     * 检测文件是否存在
     *
     * @return 是否
     */
    private boolean isCreate(String filePath) {
        File file = new File(filePath);
        return !file.exists() || config.getGlobalConfig().isFileOverride();
    }

}
