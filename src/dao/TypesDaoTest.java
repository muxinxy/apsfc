package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.glass.ui.CommonDialogs.Type;

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
		int id=11;
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

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
