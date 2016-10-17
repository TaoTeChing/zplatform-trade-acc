package com.zlebank.zplatform.trade.acc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.trade.acc.bean.ResultBean;
import com.zlebank.zplatform.trade.acc.bean.TxnsLogBean;
import com.zlebank.zplatform.trade.acc.common.dao.TxnsLogDAO;
import com.zlebank.zplatform.trade.acc.common.dao.pojo.PojoTxnsLog;
import com.zlebank.zplatform.trade.acc.enums.BusiTypeEnum;
import com.zlebank.zplatform.trade.acc.service.ConsumeAccountingService;
import com.zlebank.zplatform.trade.acc.service.TradeAccountingService;

@Service("tradeAccountingService")
public class TradeAccountingServiceImpl implements TradeAccountingService {

	@Autowired
	private TxnsLogDAO txnsLogDAO;
	@Autowired
	private ConsumeAccountingService consumeAccountingService;
	@Override
	public ResultBean accountingFor(String txnseqno) {
		ResultBean resultBean = null;
		PojoTxnsLog txnsLog = txnsLogDAO.getTxnsLogByTxnseqno(txnseqno);
		BusiTypeEnum busitype = BusiTypeEnum.fromValue(txnsLog.getBusitype());
		TxnsLogBean txnsLogBean = BeanCopyUtil.copyBean(TxnsLogBean.class, txnsLog);
		switch (busitype) {
			case consumption:
				resultBean = consumeAccountingService.consumeAccounting(txnsLogBean);
				break;
			case charge:
				break;
			case insteadPay:
				break;
			case refund:
				break;
			case safeGuard:
				break;
			case withdrawal:
				break;
			case transfer:
				break;
			default:
				break;
		}
		
		return resultBean;
	}

}
