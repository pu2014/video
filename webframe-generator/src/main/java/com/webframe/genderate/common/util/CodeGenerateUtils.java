package com.webframe.genderate.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.webframe.genderate.common.bean.ColumnClass;
import com.webframe.genderate.common.enums.MysqlTypeEnum;

import freemarker.template.Template;

@Component
public class CodeGenerateUtils {
	
	@Value("${generate.author}")
	private String author;
    
    public void generate(String tableName, List<ColumnClass> list, String packagePath, String diskPath) throws Exception{
    	for (ColumnClass columnClass : list) {
			columnClass.setColumnType(getNewColumnType(columnClass.getColumnType()));
			columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(columnClass.getColumnName().toUpperCase()));
		}
        //生成Model文件
        generateModelFile(tableName,list, packagePath, diskPath);
        //生成Dao文件
        generateDaoFile(tableName, packagePath, diskPath);
        //生成Service文件
        generateServiceFile(tableName, list,packagePath, diskPath);
       //生成ServiceImpl文件
     //   generateServiceImplFile(tableName, packagePath, diskPath);
        //生成Controller文件
        generateControllerFile(tableName, packagePath, diskPath);
        //生成table页面
        generateTableFile(tableName,list, packagePath, diskPath);
       //生成table——js页面
        generateTableJsFile(tableName,list, packagePath, diskPath);
      //生成form页面
        generateFormFile(tableName, list,packagePath, diskPath);
       //生成form——js页面
     //   generateFormJsFile(tableName, packagePath, diskPath);
        generateMdFile(tableName,list, packagePath, diskPath);
    }

    private void generateFormFile(String tableName,List<ColumnClass> list,String packagePath, String diskPath) throws Exception {
    	File file = new File(diskPath + "form\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String changeTableName = replaceUnderLineAndUpperCase(tableName).toLowerCase();
        final String suffix = ".html";
        final String path = diskPath + "form\\" + changeTableName +"form"+ suffix;
        final String templateName = "form.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", list);
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);
	}

	private void generateModelFile(String tableName, List<ColumnClass> list, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "entity\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String changeTableName = replaceUnderLineAndUpperCase(tableName);
        final String suffix = ".java";
        final String path = diskPath + "entity\\" + changeTableName + suffix;
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", list);
        dataMap.put("packagePath",packagePath);
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }
    
    private void generateDaoFile(String tableName, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "dao\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName) + "Dao";
        final String suffix = ".java";
        final String path = diskPath + "dao\\" + name + suffix;
        final String templateName = "Dao.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packagePath",packagePath);
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }
    
    private void generateServiceFile(String tableName, List<ColumnClass> list, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "service\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName) + "Service";
        final String suffix = ".java";
        final String path = diskPath + "service\\" + name + suffix;
        final String templateName = "Service.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", list);
        dataMap.put("packagePath",packagePath);
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }
    
    /*private void generateServiceImplFile(String tableName, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "service\\impl\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName) + "ServiceImpl";
        final String suffix = ".java";
        final String path = diskPath + "service\\impl\\" + name + suffix;
        final String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packagePath",packagePath);
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }*/
    
    private void generateControllerFile(String tableName, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "controller\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName) + "Controller";
        final String suffix = ".java";
        final String path = diskPath + "controller\\" + name + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("packagePath",packagePath);
        dataMap.put("prefix", packagePath.subSequence(packagePath.lastIndexOf(".") + 1, packagePath.length()));
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }
    
    private void generateTableFile(String tableName, List<ColumnClass> list, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "html\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName).toLowerCase();
        final String suffix = ".html";
        final String path = diskPath + "html\\" + name + suffix;
        final String templateName = "table.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", list);
        dataMap.put("packagePath",packagePath);
        dataMap.put("prefix", packagePath.subSequence(packagePath.lastIndexOf(".") + 1, packagePath.length()));
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }
    
    private void generateTableJsFile(String tableName, List<ColumnClass> list, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "js\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName).toLowerCase();
        final String suffix = ".js";
        final String path = diskPath + "js\\" + name + suffix;
        final String templateName = "table_js.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", list);
        dataMap.put("packagePath",packagePath);
        dataMap.put("prefix", packagePath.subSequence(packagePath.lastIndexOf(".") + 1, packagePath.length()));
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }
    
    private void generateMdFile(String tableName, List<ColumnClass> list, String packagePath, String diskPath) throws Exception{
    	File file = new File(diskPath + "md\\");
        if (!file.exists()) {
            file.mkdirs();
        }
    	String name = replaceUnderLineAndUpperCase(tableName).toLowerCase();
        final String suffix = ".md";
        final String path = diskPath + "md\\" + name + suffix;
        final String templateName = "md.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column", list);
        dataMap.put("packagePath",packagePath);
        dataMap.put("tableName",tableName);
        dataMap.put("prefix", packagePath.subSequence(packagePath.lastIndexOf(".") + 1, packagePath.length()));
        generateFileByTemplate(templateName,mapperFile,dataMap,tableName,packagePath);

    }

    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap,String tableName, String path) throws Exception{
    	String changeTableName = replaceUnderLineAndUpperCase(tableName);
    	Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small",tableName);
        dataMap.put("table_name",changeTableName);
        dataMap.put("author",author);
        dataMap.put("date",DateUtil.getTodayStr());
        dataMap.put("package_name",path);
    //    dataMap.put("table_annotation",tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

    public String replaceUnderLineAndUpperCase(String str){
    	str = str.toUpperCase();
		String[] strArr = str.split("_");
		String result = "";
		for(int i = 0; i < strArr.length; i++){
			result += strArr[i].substring(0, 1) + strArr[i].substring(1, strArr[i].length()).toLowerCase();
		}
		return result;
    }
    public String firstCharUpper(String str){
        if(str.length()>2){
        	return str.substring(0, 1).toLowerCase() + str.substring(1);
        }else{
        	return str;
        }
    }
    
    public String getNewColumnType(String type){
		int index = type.indexOf('(');
		String newType = "";
		if(index > -1){
			newType = type.substring(0, index).toUpperCase();
		}else{
			newType = type.toUpperCase();
		}
		MysqlTypeEnum typeEnum = MysqlTypeEnum.valueOf(newType);
		switch(typeEnum){
			case DATE:
				newType = "DATE";
				break;
			case TIME:
				newType = "DATE";
				break;
			case DATETIME:
				newType = "DATE";
				break;
			case TIMESTAMP:
				newType = "DATE";
				break;
			case TINYINT:
				newType = "LONG";
				break;
			case SMALLINT:
				newType = "LONG";
				break;
			case MEDIUMINT:
				newType = "LONG";
				break;
			case INT:
				newType = "LONG";
				break;
			case BIGINT:
				newType = "LONG";
				break;
			case FLOAT:
				newType = "FLOAT";
				break;
			case DOUBLE:
				newType = "DOUBLE";
				break;
			case CHAR:
				newType = "STRING";
				break;
			case VARCHAR:
				newType = "STRING";
				break;
			case TINYTEXT:
				newType = "STRING";
				break;
			case TEXT:
				newType = "STRING";
				break;
			case MEDIUMTEXT:
				newType = "STRING";
				break;
			case LONGTEXT:
				newType = "STRING";
				break;
			case BLOB:
				newType = "BLOB";
				break;
		}
		return newType;
	}
    
}
