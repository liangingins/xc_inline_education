package com.xuecheng.api.config;

import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Cms站点管理接口",description = "cms站点管理接口，提供站点的增、删、改、查")
public interface CmsSiteControllerApi {

    //分页条件查询
    @ApiOperation(value = "查询全部")
    public QueryResponseResult findList();

}
