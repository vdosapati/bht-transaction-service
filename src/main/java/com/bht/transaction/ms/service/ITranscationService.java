package com.bht.transaction.ms.service;

import com.bht.transaction.ms.vo.AccountTransactionDetails;
import com.bht.transaction.ms.vo.AccountTransactionRequest;
import com.bht.transaction.ms.vo.TransactionRequest;
import com.bht.transaction.ms.vo.TransactionsDetailResponse;
import com.bht.vo.FGRequestContext;

public interface ITranscationService {

	AccountTransactionDetails getDetails(FGRequestContext reqContext,TransactionRequest accountOpenRequest);
	TransactionsDetailResponse getTotalAccountTransactions(FGRequestContext reqContext,AccountTransactionRequest accountOpenRequest);
}	