package com.zlebank.zplatform.trade.acc.service;

import com.zlebank.zplatform.trade.acc.bean.ResultBean;

/**
 * 交易账务接口
 * @author guojia
 *
 */
public interface TradeAccountingService {

	/**
	 * 交易分录
	 * @param txnseqno
	 * @return 结果bean
	 */
	public ResultBean accountingFor(String txnseqno);
}
