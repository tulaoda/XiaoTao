package com.xt.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xt.entity.Goods;
import com.xt.entity.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public User login(User user) {
		return (User)sessionFactory.getCurrentSession()
									.createQuery("from User where nickname=? and passwd=?")	
									.setString(0, user.getNickname())
									.setString(1, user.getPasswd())	
									.uniqueResult();	
	}
	
	public void addNewUser(User user){
		sessionFactory.getCurrentSession().save(user);
	}
	
	public int modifyPass(User user){
		String hql="update User u set u.pass =? where phone=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getPasswd());
		query.setString(1, user.getPhone());
		return query.executeUpdate();
	}
	
	public int modifyAddress(User user){
		String hql="update User u set u.address =? where phone=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getAddress());
		query.setString(1, user.getPhone());
		return query.executeUpdate();
	}
	
	public int modifyName(User user){
		String hql="update User u set u.name =? where phone=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getNickname());
		query.setString(1, user.getPhone());
		return query.executeUpdate();
	}
	
	public boolean exits(User user){
		User u=(User) sessionFactory.getCurrentSession()
		.createQuery("from User where nickname=? ")	
		.setString(0, user.getNickname())
		.uniqueResult();	
		return u!=null?true:false;
	}
	
	public User validateNickname(User user){
		return (User)sessionFactory.getCurrentSession()
		.createQuery("from User where nickname=? ")	
		.setString(0, user.getNickname())
		.uniqueResult();	
	}
	
	public List<Object> findUserAndGoods(int pageSize,int page){
		String hql="from User u,Goods g where u.userid=g.userid";
		return	sessionFactory.getCurrentSession().createQuery(hql)
				.setFirstResult((page-1)*pageSize)
				.setMaxResults(pageSize).list(); 
		
	}
}
