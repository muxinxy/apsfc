package dao;

import java.util.ArrayList;

import org.junit.Test;

import po.Types;

public class TypesDaoTest {
	private TypesDao typesDao=new TypesDao();

	@Test
	public void testAdd() {
		Types type=new Types();
		type.setName("川菜");
		int result=typesDao.add(type);
		System.out.println(result);
	}

	@Test
	public void testDel() {
		int id=12;
		int result=typesDao.del(id);
		System.out.println(result);
	}

	@Test
	public void testChg() {
		int id=2;
		String name="川菜";
		int result=typesDao.chg(id, name);
		System.out.println(result);
	}

	@Test
	public void testFindAll() {
		ArrayList<Types> list=typesDao.findAll();
		for (Types types:list) {
			System.out.println(types);
		}
	}

	@Test
	public void testFindById() {
		Types types=typesDao.findById(10);
		System.out.println(types);
	}

	@Test
	public void testFindByName() {
		Types types=typesDao.findByName("蒸菜");
		System.out.println(types);
	}
}
