package service;

import java.util.ArrayList;

import dao.TypesDao;
import po.Types;

public class TypesService {
	private TypesDao typesDao=new TypesDao();
	//添加
	public int  add(Types type) {
		//判断要添加的菜品是否存在
		if (typesDao.findByName(type.getName())!=null) {
			return -1;
		}
		return typesDao.add(type);
	}
	//删除
	public int del(int id) {
		return typesDao.del(id);
	}
	//修改
	public int chg(int id,String name) {
		Types type=typesDao.findByName(name);
		if (type!=null && type.getId()!=id) {
			return -1;
		}
		return typesDao.chg(id, name);
	}
	//查询所有
	public ArrayList<Types> findAll() {
		return typesDao.findAll();
	}
	//根据id查询
	public Types findById(int id) {
		return typesDao.findById(id);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
