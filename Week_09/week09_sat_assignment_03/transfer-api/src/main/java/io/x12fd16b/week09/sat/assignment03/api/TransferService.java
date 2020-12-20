package io.x12fd16b.week09.sat.assignment03.api;

import io.x12fd16b.week09.sat.assignment03.api.model.Account;
import org.dromara.hmily.annotation.Hmily;

/**
 * Transfer Service
 *
 * @author David Liu
 */
public interface TransferService {
    @Hmily
    void transfer(Account account);
}
