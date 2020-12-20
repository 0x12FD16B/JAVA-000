package io.x12fd16b.week09.sat.assignment03.consumer.service;

import io.x12fd16b.week09.sat.assignment03.api.TransferService;
import io.x12fd16b.week09.sat.assignment03.api.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransferService
 *
 * @author David Liu
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired(required = false)
    private TransferService transferService;

    @Override
    @HmilyTCC(confirmMethod = "doConfirm", cancelMethod = "doCancel")
    public void doTransfer() {
        Account accountFrom = new Account();
        accountFrom.setId(1);
        accountFrom.setRmb(7);
        accountFrom.setDollar(-1);
        transferService.transfer(accountFrom);

        Account accountTo = new Account();
        accountTo.setId(2);
        accountTo.setDollar(1);
        accountTo.setRmb(-7);
        transferService.transfer(accountTo);
    }

    public void doConfirm() {
        log.info("confirm transfer");
    }

    public void doCancel() {
        log.info("cancel transfer.");
        Account accountFrom = new Account();
        accountFrom.setId(1);
        accountFrom.setRmb(-7);
        accountFrom.setDollar(1);
        transferService.transfer(accountFrom);

        Account accountTo = new Account();
        accountTo.setId(2);
        accountTo.setDollar(-1);
        accountTo.setRmb(7);
        transferService.transfer(accountTo);
    }
}
