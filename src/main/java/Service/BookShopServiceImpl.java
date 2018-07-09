package Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import DAO.BookShopDao;


@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    /*
    * 事务属性:
    *   1:事务的传播行为:propagation=Propagation.xxx
    *   2:事务的隔离级别:isolation=Isolation.xxx
    *   3:事务是否回滚的异常:noRollbackFor = {xxxException.class}
    *   4.指定事务是否只读(标识这个事务是否只读数据不更新数据):readOnly=true|false
    *   5.指定强制回滚之前,事务可以占用的时间 : timeout = x (x为秒)
    * */

    @Transactional(propagation = Propagation.REQUIRES_NEW,
    isolation = Isolation.READ_COMMITTED, readOnly = false,
    timeout = 3)
    @Override
    public void purchase(String username, String isbn) {
        int price = bookShopDao.fineBookPriceByIsbn(isbn);
        bookShopDao.updateBookStock(isbn);
        bookShopDao.updateUserAccount(username,price);
    }
}
