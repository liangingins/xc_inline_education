package com.xuecheng.test.freemarker;

import com.xuecheng.test.freemarker.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.util.*;

public class FreemarkerTest {

    @Test
    public void  testGenaratedHtml() throws IOException, TemplateException {
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板路径
        String classPath = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(classPath+"/templates/"));
        //设置字符编码
        configuration.setDefaultEncoding("utf-8");
        //加载模板
        Template template = configuration.getTemplate("test01.ftl");
        //数据模型
        Map map = getMap();
        //静态化页面
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(content);
        //输出文件
        InputStream inputStream = IOUtils.toInputStream(content);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test1.html"));
        IOUtils.copy(inputStream,fileOutputStream);
        //关闭流
        inputStream.close();
        fileOutputStream.close();
    }
    //使用模板字符串静态化
    @Test
    public void  testGenaratedHtml2() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        //模板字符串
        String templateString="" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                " 名称：${name}\n" +
                " </body>\n" +
                "</html>";
        //创建模板加载器
        StringTemplateLoader stringTemplateLoader= new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",templateString);
        configuration.setTemplateLoader(stringTemplateLoader);
        //得到模板
        Template template = configuration.getTemplate("template");
        //数据模型
        Map map = new HashMap();
        map.put("name","我真的好烦这个键盘");
        //静态化页面
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //输出文件
        InputStream inputStream = IOUtils.toInputStream(content);
        FileOutputStream  fileOutputStream = new FileOutputStream(new File("d:/test02.html"));
        IOUtils.copy(inputStream,fileOutputStream);
        //关闭流
        inputStream.close();
        fileOutputStream.close();


    }
    public Map getMap(){
        Map map = new HashMap();
        //向数据模型放数据
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.00F);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.00F);
        stu2.setAge(19);
        // stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        map.put("point",1278389483);
        //返回模板文件名称
        return map;
    }



}
