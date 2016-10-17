package com.zlebank.zplatform.trade.acc.common.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.trade.acc.common.dao.TxnsLogDAO;
import com.zlebank.zplatform.trade.acc.common.dao.pojo.PojoTxnsLog;
import com.zlebank.zplatform.trade.acc.common.enums.TradeStatFlagEnum;
import com.zlebank.zplatform.trade.acc.common.utils.DateUtil;

/**
 * 
 * 基础数据库连接层DAO
 *
 * @author guojia
 * @version
 * @date 2015-6-26 上午10:09:10
 * @param T
 * @since
 */
@Repository("TxnsLogDAO")
public class TxnsLogDAOImpl extends HibernateBaseDAOImpl<PojoTxnsLog> implements
		TxnsLogDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TxnsLogDAOImpl.class);

	@Override
	@Transactional(readOnly = true)
	public PojoTxnsLog getTxnsLogByTxnseqno(String txnseqno) {
		Criteria criteria = getSession().createCriteria(PojoTxnsLog.class);
		criteria.add(Restrictions.eq("txnseqno", txnseqno));
		return (PojoTxnsLog) criteria.uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateAccBusiCode(String txnseqno, String busicode) {
		// TODO Auto-generated method stub
		String hql = "update TxnsLogModel set accbusicode = ? where txnseqno = ? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, busicode);
		query.setParameter(1, busicode);
		int rows = query.executeUpdate();
		log.info("updateAccBusiCode() effect rows:" + rows);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateAppStatus(String txnseqno, String appOrderStatus,
			String appOrderinfo) {
		String hql = "update TxnsLogModel set appordfintime = ?,apporderstatus = ?,apporderinfo = ? where txnseqno = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, DateUtil.getCurrentDateTime());
		query.setParameter(1, appOrderStatus);
		query.setParameter(2, appOrderinfo);
		query.setParameter(3, txnseqno);
		int rows = query.executeUpdate();
		log.info("updateAppStatus() effect rows:" + rows);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateTradeStatFlag(String txnseqno,
			TradeStatFlagEnum tradeStatFlagEnum) {
		String hql = "update TxnsLogModel set tradestatflag = ? where txnseqno = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, tradeStatFlagEnum.getStatus());
		query.setParameter(1, txnseqno);
		int rows = query.executeUpdate();
		log.info("updateTradeStatFlag() effect rows:" + rows);

	}

}
