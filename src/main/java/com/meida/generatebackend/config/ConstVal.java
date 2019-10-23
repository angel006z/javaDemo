package com.meida.generatebackend.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

public class ConstVal {
	
	public static final String BASEURL = "baseUrl";
	public static final String JSPURL = "jspUrl";  //跳转页面的路径
	public static final String BASEPATH = "com/meida/generatebackend";  //模板文件的基本路径
    public static final String MODULENAME = "ModuleName";

    public static final String PO = "Info";
    public static final String VO = "Vo";
    public static final String DTO = "Dto";

    public static final String LISTDTO = "ListDto";
    public static final String LISTPARAMDTO = "ListParamDto";
    public static final String SUBMITDTO = "SubmitDto";
    public static final String SUBMITPARAMDTO = "SubmitParamDto";

    public static final String SERIVCE = "Service";
    public static final String SERVICEIMPL = "ServiceImpl";
    public static final String DAO = "Dao";
    public static final String DAOMAPPING = "Mapping";
    public static final String DAOIMPL = "DaoImpl";
    public static final String CONTROLLER = "Controller";
    public static final String MANAGER = "Manager";
    public static final String ADDORUPDATE = "addOrUpdate";

    public static final String PO_PATH = "po_path";
    public static final String VO_PATH = "vo_path";
    public static final String LISTDTO_PATH = "listDto_path";
    public static final String LISTPARAMDTO_PATH = "listParamDto_path";
    public static final String SUBMITDTO_PATH = "submitDto_path";
    public static final String SUBMITPARAMDTO_PATH = "submitParamDto_path";
    public static final String SERIVCE_PATH = "serivce_path";
    public static final String SERVICEIMPL_PATH = "serviceimpl_path";
    public static final String DAO_PATH = "dao_path";
    public static final String DAOMAPPING_PATH = "daomapping_path";
    public static final String DAOIMPL_PATH = "daoimpl_path";
    public static final String CONTROLLER_PATH = "controller_path";
    public static final String MANAGERLIST_PATH = "managerlist_path";
    public static final String ADDORPDATE_PATH = "addorupdate_path";

    public static final String JAVA_TMPDIR = "java.io.tmpdir";
    public static final String UTF8 = Charset.forName("UTF-8").name();
    public static final String UNDERLINE = "_";

    public static final String JAVA_SUFFIX = ".java";
    public static final String JSP_SUFFIX = ".jsp";
    public static final String XML_SUFFIX = ".xml";

    public static final String TEMPLATE_PO = BASEPATH + "/template/po.java.vm";
    public static final String TEMPLATE_VO = BASEPATH + "/template/vo.java.vm";
    public static final String TEMPLATE_LISTDTO = BASEPATH + "/template/listDto.java.vm";
    public static final String TEMPLATE_LISTPARAMDTO = BASEPATH + "/template/listParamDto.java.vm";
    public static final String TEMPLATE_SUBMITDTO = BASEPATH + "/template/submitDto.java.vm";
    public static final String TEMPLATE_SUBMITPARAMDTO = BASEPATH + "/template/submitParamDto.java.vm";
    public static final String TEMPLATE_SERVICE = BASEPATH + "/template/service.java.vm";
    public static final String TEMPLATE_SERVICEIMPL = BASEPATH + "/template/serviceImpl.java.vm";
    public static final String TEMPLATE_DAO = BASEPATH + "/template/dao.java.vm";
    public static final String TEMPLATE_DAOMAPPING = BASEPATH + "/template/daoMapping.xml.vm";
    public static final String TEMPLATE_DAOIMPL = BASEPATH + "/template/daoimpl.java.vm";
    public static final String TEMPLATE_CONTROLLER = BASEPATH + "/template/controller.java.vm";
    public static final String TEMPLATE_MANAGERLIST = BASEPATH + "/template/managerList.jsp.vm";
    public static final String TEMPLATE_ADDORUPDATE = BASEPATH + "/template/addOrUpdate.jsp.vm";

    public static final String MODEL_NAME = File.separator + "%s" + JAVA_SUFFIX;

    // 配置使用classloader加载资源
    public static final String VM_LOADPATH_KEY = "file.resource.loader.class";
    public static final String VM_LOADPATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    public static final String SUPERD_DAO_CLASS = "";
    public static final String SUPERD_DAOIMPL_CLASS = "";
    public static final String SUPERD_SERVICE_CLASS = "";
    public static final String SUPERD_SERVICEIMPL_CLASS = "";

    public static final String SUPERD_SUBMITDTO_CLASS = "";
    public static final String SUPERD_SUBMITPARAMDTO_CLASS = "";

    //java文件路径
    public static final String javaPath(String packageName) throws IOException {
    	// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		File projectPath = new DefaultResourceLoader().getResource("").getFile();
		System.out.println(projectPath.getPath());
		while(!new File(projectPath.getPath()+separator+"src").exists()){
			projectPath = projectPath.getParentFile();
		}
		// Java文件路径
		String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName), 
				new String[]{"/"}, new String[]{separator});
		return javaPath;
    }
    
    //工程路径
    public static final String projectPath() throws IOException {
    	// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		File projectPath = new DefaultResourceLoader().getResource("").getFile();
		System.out.println(projectPath.getPath());
		while(!new File(projectPath.getPath()+separator+"src").exists()){
			projectPath = projectPath.getParentFile();
		}
		// Java文件路径
		String projectPathStr = StringUtils.replaceEach(projectPath+"/", 
				new String[]{"/"}, new String[]{separator});
		return projectPathStr;
    }
}
