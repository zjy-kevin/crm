package com.yue.crm.workbench.dao;

import com.yue.crm.workbench.domain.ClueRemark;
import com.yue.crm.workbench.domain.CustomerRemark;

import java.util.List;

public interface ClueRemarkDao {

    List<ClueRemark> getListByClueId(String clueId);

    int delete(ClueRemark clueRemark);
}
