package com.xuecheng.api.config;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Cms站点管理接口",description = "cms站点管理接口，提供站点的增、删、改、查")
public interface CmsConfigControllerApi {

    @ApiOperation(value = "根据id查询轮播图信息")
    public CmsConfig findById(String id);

}
