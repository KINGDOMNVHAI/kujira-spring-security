package com.poscodx.odc.excan013.service.logic;

import com.poscdx.odc.excan013.domain.logic.ExcanCategoryLogic;
import com.poscdx.odc.excan013.domain.store.ExcanCategoryStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExcanCategorySpringLogic extends ExcanCategoryLogic
{
    public ExcanCategorySpringLogic(ExcanCategoryStore store) {
        super(store);
    }
}
