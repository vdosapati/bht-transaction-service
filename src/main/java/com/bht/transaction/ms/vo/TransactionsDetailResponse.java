package com.bht.transaction.ms.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionsDetailResponse {
	List<AccountTransactionDetails> totalAccountTransactions;

}
