package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryPageRequest extends RequestData {

    @ApiModelProperty("页面id")
    private String pageId;//页面id

    @ApiModelProperty("站点id")
    private String siteId;//站点id

    @ApiModelProperty("页面名称")
    private String pageName;//页面名称

    @ApiModelProperty("页面别名")
    private String pageAliase;//页面别名

    @ApiModelProperty("模板id")
    private String templateId;//模板id


}
