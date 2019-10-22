package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import com.xuecheng.manage_cms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Override
    public QueryResponseResult findList() {

        QueryResult queryResult = new QueryResult();
        List<CmsTemplate> templateList = cmsTemplateRepository.findAll();
        queryResult.setList(templateList);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
}
