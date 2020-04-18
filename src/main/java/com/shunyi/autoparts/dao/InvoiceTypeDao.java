package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 发票类型Dao
 * @author Shunyi Chen
 * @date 2020/4/18
 */
public interface InvoiceTypeDao extends JpaRepository<InvoiceType, Long>  {
}
