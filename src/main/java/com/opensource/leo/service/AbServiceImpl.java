package com.opensource.leo.service;

import com.opensource.leo.hash.HashAlgo;
import com.opensource.leo.hash.HashAlgoMapper;
import com.opensource.leo.meta.Bucket;
import com.opensource.leo.model.AbRequest;
import com.opensource.leo.model.Result;
import com.opensource.leo.model.StatusCode;
import com.opensource.leo.meta.Exper;
import com.opensource.leo.model.AbResponse;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Author:leo.lx
 * Date:2016-02-26 16:48
 */
public class AbServiceImpl implements AbService {

    private ExperMetaService experMetaService;

    public AbServiceImpl(ExperMetaService experMetaService) {
        this.experMetaService = experMetaService;
    }

    @Override
    public Result<AbResponse> ab(AbRequest request) {
        Result result = new Result();
        if (!validRequest(request)) {
            result.setStatus(StatusCode.PARAM_ERROR);
            return result;
        }

        List<Exper> expers = experMetaService.getExperiments();
        if (expers == null || expers.size() == 0) {
            result.setStatus(StatusCode.NO_EXPR);
            return result;
        }

        AbResponse abResponse;
        try {
            abResponse = matchExpr(request, expers);
            result.setModule(abResponse);
        } catch (Exception e) {
            result.setStatus(StatusCode.FAILED);
        }
        return result;
    }

    private boolean validRequest(AbRequest request) {
        if (request == null || StringUtils.isBlank(request.getCookie())) {
            return false;
        }
        return true;
    }

    protected AbResponse matchExpr(AbRequest req, List<Exper> experiments) {
        String cookie = req.getCookie();
        AbResponse response = new AbResponse();
        for (Exper e : experiments) {
            AbResponse.AbEntity entity = new AbResponse.AbEntity();
            HashAlgo hashAlgo = HashAlgoMapper.getHashAlg(e.getHashAlg());
            String expr = e.getName();
            List<Bucket> buckets = e.getBuckets();
            if (hashAlgo == null || StringUtils.isBlank(expr) || buckets == null || buckets.isEmpty()) {
                continue;
            }
            String newCookie = new StringBuilder(cookie).append(expr).toString();
            long hash = hashAlgo.hash(newCookie);
            if (hash < 0) {
                continue;
            }
            int b = (int) (hash % 100);
            for (Bucket bucket : buckets) {
                if (!bucket.hitBucket(b)) {
                    continue;
                }
                entity.setBucketId(bucket.getId());
                break;
            }
            if (entity.getBucketId() == 0) {
                System.out.println(entity);
            }
            entity.setExprName(e.getName());
            response.addEntity(entity);
        }
        return response;
    }
}