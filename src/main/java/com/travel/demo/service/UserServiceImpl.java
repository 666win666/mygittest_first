package com.travel.demo.service;


import com.travel.demo.dao.UserDao;
import com.travel.demo.entity.User;
import com.travel.demo.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;
    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("Y");
        userDao.save(user);

        //3.激活邮件发送，邮件正文？

//        String content="<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【民宿旅游网】</a>";
//
//        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }


    /**
     * 登录方法
     * @param user
     * @return
     */
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

}
