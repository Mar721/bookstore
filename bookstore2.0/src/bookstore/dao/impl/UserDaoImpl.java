package bookstore.dao.impl;

import bookstore.dao.UserDao;
import bookstore.pojo.User;
import com.myssm.basedao.BaseDAO;

public class UserDaoImpl extends BaseDAO<User> implements UserDao {
    @Override
    public User login(String uname, String pwd) {
        return super.load("select * from t_user where uname=? and pwd=?;",uname,pwd);
    }

    @Override
    public void addUser(User user) {
            super.executeUpdate("insert into t_user(uname,pwd,email)values(?,?,?);",
                user.getUname(),user.getPwd(),user.getEmail());
    }

    @Override
    public User getUser(String uname) {
        return super.load("select * from t_user where uname=?;",uname);
    }
}
