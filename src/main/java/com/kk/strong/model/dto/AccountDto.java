package com.kk.strong.model.dto;

import com.kk.strong.model.AccountRole;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto {

    private String username;
    private List<AccountRole> accountRoles;
}
