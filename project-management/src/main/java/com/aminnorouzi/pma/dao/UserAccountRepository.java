package com.aminnorouzi.pma.dao;

import com.aminnorouzi.pma.entities.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}