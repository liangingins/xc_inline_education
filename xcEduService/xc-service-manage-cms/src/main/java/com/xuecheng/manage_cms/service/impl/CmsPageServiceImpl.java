package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CmsPageServiceImpl implements CmsPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if(queryPageRequest==null){
            queryPageRequest = new QueryPageRequest();
        }
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsPage cmsPage  = new CmsPage();

        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());

        }
        //定义查询条件
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        if(page<=0){
            page = 1;
        }
        page = page -1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = new PageRequest(page,size);
        Page<CmsPage> result = cmsPageRepository.findAll(example,pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        queryResult.setList(result.getContent());
        queryResult.setTotal(result.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return  queryResponseResult;
    }

    @Override
    public CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath) {
        return cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(pageName,siteId,pageWebPath);

    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        //判断改页面是否存在
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if(cmsPage1==null){
            //不存在 添加
            cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
        }else{
            return new CmsPageResult(CommonCode.FAIL,null);
        }
    }

    @Override
    public CmsPage getById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }

    }

    @Override
    public ResponseResult delete(String id) {
        //判断是否存在页面
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if(optional.isPresent()){
            //存在 删除
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return null;
    }

    @Override
    public CmsPageResult update(String id,CmsPage cmsPage) {
        //判断是否有此页面
        CmsPage cmsPage1 = getById(id);
        if(cmsPage1!=null){
            //有此页面开始更新
            cmsPage1.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsPage1.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsPage1.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsPage1.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsPage1.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsPage1.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新dataUrL
            cmsPage1.setDataUrl(cmsPage.getDataUrl());
            //执行更新
            CmsPage saveCmsPage = cmsPageRepository.save(cmsPage1);
            return new CmsPageResult(CommonCode.SUCCESS,saveCmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }

    /**
     *
     * 实现页面静态化
     */
    public String getPageHtml(String pageId){
        //获取数据
        Map model = getModeByPageId(pageId);

        //获取模板



        //页面静态化


        return null;
    }

    //获取页面数据
    private Map getModeByPageId(String pageId) {
        //根据pageId查询页面信息
        CmsPage cmsPage = this.getById(pageId);
        if(cmsPage==null){
            return null;
        }
        return null;
    }

}
