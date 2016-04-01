package com.opensource.leo.service;

import com.opensource.leo.model.Result;
import com.opensource.leo.model.AbRequest;
import com.opensource.leo.model.AbResponse;

public interface AbService {
    public Result<AbResponse> ab(AbRequest req);
}
