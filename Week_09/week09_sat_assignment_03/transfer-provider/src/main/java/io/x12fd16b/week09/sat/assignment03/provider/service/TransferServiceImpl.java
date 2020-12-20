package io.x12fd16b.week09.sat.assignment03.provider.service;

import io.x12fd16b.week09.sat.assignment03.api.TransferService;
import io.x12fd16b.week09.sat.assignment03.api.model.Account;
import io.x12fd16b.week09.sat.assignment03.provider.dao.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Transfer Service
 *
 * @author David Liu
 */
@Service
@Slf4j
public class TransferServiceImpl implements TransferService {
    @Resource
    private AccountDao accountDao;

    @Override
    @HmilyTCC(cancelMethod = "confirm", confirmMethod = "cancel")
    public void transfer(Account account) {
        accountDao.transfer(account);
    }

    public void confirm(Account account) {
        log.info("confirm");
    }

    public void cancel(Account account) {
        log.info("cancel");
    }
}
