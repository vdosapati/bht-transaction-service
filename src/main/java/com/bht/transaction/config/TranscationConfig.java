package com.bht.transaction.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bht.async.MsThreadPoolExecutor;
import com.bht.transaction.ms.util.TransactionUtil;
import com.bht.transaction.ms.vo.AccountTransactionDetails;
import com.bht.transaction.ms.vo.Transaction;

@Configuration
public class TranscationConfig {
	
	@Autowired
	private TransactionUtil transactionUtil;
	
	@Bean(name="inmemoryMap")
	Map<String, AccountTransactionDetails> createImememoryMap() {
		Map<String, AccountTransactionDetails> userTranscations = new ConcurrentHashMap<>();
		AccountTransactionDetails accountTransactionDetails = new AccountTransactionDetails();
		Transaction transcations = Transaction.builder()
				.fromAccountNumber("4532167894")
				.toAccountNumber("4532167894")
				.remarks("self")
				.transactionAmount(100)
				.build();
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transcations);
		accountTransactionDetails.setAccountNumber("4532167894");
		accountTransactionDetails.setTransactions(transactions);
		userTranscations.put("4532167894", accountTransactionDetails);
		return userTranscations;

	}
	
	@Bean
	public MsThreadPoolExecutor custompoolExecutor() {
		MsThreadPoolExecutor MsThreadPoolExecutor = new MsThreadPoolExecutor(10, 25, 10, TimeUnit.SECONDS,
				"Customthread", new LinkedBlockingDeque<>(10));
		return MsThreadPoolExecutor;

	}
	

}
