package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

public interface CmsPageService {
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    //根据页面名称、站点id、页面网站路径查询页面
    public CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);

    //添加页面
    public CmsPageResult add(CmsPage cmsPage);

    //根据id查询页面
    public CmsPage getById(String id);

    //根据id删除页面
    public ResponseResult delete(String id);

    //更新页面
    public CmsPageResult update(String id,CmsPage cmsPage);


}
