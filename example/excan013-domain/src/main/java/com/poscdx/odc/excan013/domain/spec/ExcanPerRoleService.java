package com.poscdx.odc.excan013.domain.spec;

import com.poscdx.odc.excan013.domain.entity.ExcanPerRole;

import java.util.List;

public interface ExcanPerRoleService {

    public ExcanPerRole find(int id);

    public List<ExcanPerRole> findAll();

    public void modify(List<ExcanPerRole> entityList);

    public ExcanPerRole register(ExcanPerRole entity);

    public void remove(int id);
}
