package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.config.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        return cmsPageService.findList(page,size,queryPageRequest);
    }

    @Override
    @GetMapping("/findOne")
    public CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath) {
        return cmsPageService.findByPageNameAndSiteIdAndPageWebPath(pageName,siteId,pageWebPath);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPage getById(@PathVariable("id") String id) {
        return cmsPageService.getById(id);
    }

    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        return cmsPageService.update(id,cmsPage);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") String id) {
        return cmsPageService.delete(id);
    }
}
