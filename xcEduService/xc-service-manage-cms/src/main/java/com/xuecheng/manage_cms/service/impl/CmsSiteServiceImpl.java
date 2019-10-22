package com.xuecheng.manage_cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import com.xuecheng.manage_cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Override
    public QueryResponseResult findList() {
        QueryResult queryResult = new QueryResult();
        List<CmsSite> cmsList = cmsSiteRepository.findAll();
        queryResult.setList(cmsList);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);

    }
}
