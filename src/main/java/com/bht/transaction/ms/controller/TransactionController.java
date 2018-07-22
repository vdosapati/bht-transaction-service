package com.bht.transaction.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bht.controller.IRestController;
import com.bht.transaction.ms.service.ITranscationService;
import com.bht.transaction.ms.vo.AccountTransactionDetails;
import com.bht.transaction.ms.vo.AccountTransactionRequest;
import com.bht.transaction.ms.vo.TransactionRequest;
import com.bht.transaction.ms.vo.TransactionsDetailResponse;
import com.bht.vo.MSRequestScope;
import com.bht.vo.ServiceResponse;

@Controller
public class TransactionController implements IRestController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private MSRequestScope requestScope;

	@Autowired
	private ITranscationService transactionService;

	/**
	 * Service exposed to open secondary account
	 * @param servletRequest
	 * @param accountOpenRequest
	 * @return
	 */
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ServiceResponse<AccountTransactionDetails>> getDetails(
			HttpServletRequest servletRequest, @RequestBody @Valid TransactionRequest accountOpenRequest) {
		return invokeServiceResponse(servletRequest, accountOpenRequest, transactionService::getDetails);

	}
	
	@RequestMapping(value = "/total-accounts", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ServiceResponse<TransactionsDetailResponse>> getTotalAccountTransactions(
			HttpServletRequest servletRequest, @RequestBody AccountTransactionRequest accountOpenRequest) {
		return invokeServiceResponse(servletRequest, accountOpenRequest, transactionService::getTotalAccountTransactions);

	}

	@Override
	public MSRequestScope getExceptionCollections() {
		return requestScope;
	}
}
