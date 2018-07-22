package com.bht.transaction.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bht.transaction.ms.vo.AccountTransactionDetails;
import com.bht.transaction.ms.vo.AccountTransactionRequest;
import com.bht.transaction.ms.vo.Transaction;
import com.bht.transaction.ms.vo.TransactionRequest;
import com.bht.transaction.ms.vo.TransactionsDetailResponse;
import com.bht.vo.FGRequestContext;

@Service
public class TransctionServiceImpl implements ITranscationService {

	@Autowired
	@Qualifier("inmemoryMap")
	private Map<String, AccountTransactionDetails> acctTranscations;

	@Override
	public AccountTransactionDetails getDetails(FGRequestContext reqContext, TransactionRequest transactionRequest) {
		List<String> accountNumbers = new ArrayList<>();
		AccountTransactionDetails accountTransactionDetails = null;
		if (acctTranscations.containsKey(transactionRequest.getAccountNumber())) {
			accountTransactionDetails = acctTranscations.get(transactionRequest.getAccountNumber());

			if (transactionRequest.getTransaction().getToAccountNumber()
					.equals(transactionRequest.getAccountNumber())) {
				int totalAmt = accountTransactionDetails.getTotalAmount()
						+ transactionRequest.getTransaction().getTransactionAmount();
				accountTransactionDetails.setTotalAmount(totalAmt);
			} else {
				if (accountTransactionDetails.getTotalAmount() > transactionRequest.getTransaction()
						.getTransactionAmount()) {
					int totalAmt = accountTransactionDetails.getTotalAmount()
							- transactionRequest.getTransaction().getTransactionAmount();
					accountTransactionDetails.setTotalAmount(totalAmt);
				}
			}

		} else {
			accountTransactionDetails = new AccountTransactionDetails();
			accountTransactionDetails.setAccountNumber(transactionRequest.getAccountNumber());
			accountTransactionDetails.setTotalAmount(transactionRequest.getTransaction().getTransactionAmount());
			accountTransactionDetails.setCurrency("Doller");
			List<Transaction> transactions = new ArrayList<>();
			transactions.add(transactionRequest.getTransaction());
			accountTransactionDetails.setTransactions(transactions);
		}
		acctTranscations.put(transactionRequest.getAccountNumber(), accountTransactionDetails);

		return accountTransactionDetails;
	}

	@Override
	public TransactionsDetailResponse getTotalAccountTransactions(FGRequestContext reqContext,
			AccountTransactionRequest accountOpenRequest) {
		TransactionsDetailResponse transactionsDetailResponse = new TransactionsDetailResponse();
		List<AccountTransactionDetails> totalAccountTransactions = new ArrayList<>();
		for (String acct : accountOpenRequest.getAccountNumbers()) {
			AccountTransactionDetails accountTransactionDetails = acctTranscations.get(acct);
			if (null != accountTransactionDetails) {
				totalAccountTransactions.add(accountTransactionDetails);
			}
		}

		transactionsDetailResponse.setTotalAccountTransactions(totalAccountTransactions);
		return transactionsDetailResponse;
	}

}
