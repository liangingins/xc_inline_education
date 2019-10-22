package com.xuecheng.test.freemarker;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTemplateTest {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;


    /**
     *
     * 储存文件
     */
    @Test
    public void test01(){
        //准备文件
        File file = new File("d:/index_banner.html");

        FileInputStream fileInputStream= null;
        try {
            //定义输入流
            fileInputStream = new FileInputStream(file);
            //存储文件
            ObjectId fileId = gridFsTemplate.store(fileInputStream, "轮播图test02");
            System.out.println(fileId);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件
     */
    @Test
    public void queryFile(){
        //根据文件id查询文件
        String fileId = "5da6f092a04128469061bcff";
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        //打开下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsResource 获取流对象
        GridFsResource gridFsResource =  new GridFsResource(gridFSFile,gridFSDownloadStream);
        //获取流中的数据
        try {
            String html = IOUtils.toString(gridFsResource.getInputStream());
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     */
    @Test
    public void deleteFile(){
        String fileId = "5da6f092a04128469061bcff";
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId)));
    }

}
