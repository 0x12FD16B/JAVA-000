package io.x12fd16b.week09.sat.assignment03.provider.dao;

import io.x12fd16b.week09.sat.assignment03.api.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * AccountDao
 *
 * @author David Liu
 */
@Mapper
public interface AccountDao {

    @Update("update `account` set dollar = dollar + #{dollar}, rmb = rmb + #{rmb} where dollar >= #{dollar} and rmb >= #{rmb} and id = #{id}")
    boolean transfer(Account account);
}
