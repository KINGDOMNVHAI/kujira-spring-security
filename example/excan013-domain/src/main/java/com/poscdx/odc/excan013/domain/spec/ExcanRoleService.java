package com.poscdx.odc.excan013.domain.spec;

import com.poscdx.odc.excan013.domain.entity.ExcanRole;
import com.poscdx.odc.excan013.domain.entity.payload.response.RoleResponse;

import java.util.ArrayList;
import java.util.List;

public interface ExcanRoleService {

    public ExcanRole find(int id);

    public List<ExcanRole> findAll();

    public void modify(List<ExcanRole> entityList);

    public ExcanRole register(ExcanRole entity);

    public void remove(int id);

    RoleResponse addRole(ExcanRole excanRole);
    RoleResponse updateRole(ExcanRole excanRole);

    RoleResponse findRoleByCondition(String roleName, String statusId);

}
