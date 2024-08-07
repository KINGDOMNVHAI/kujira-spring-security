package com.poscdx.odc.excan013.domain.store;

import com.poscdx.odc.excan013.domain.entity.ExcanPermission;

import java.util.List;

public interface ExcanPermissionStore {

    public ExcanPermission retrieve(int id);

    public List<ExcanPermission> retrieveAll();

    public ExcanPermission update(ExcanPermission entity);

    public ExcanPermission create(ExcanPermission entity);

    public void delete(int id);
}
