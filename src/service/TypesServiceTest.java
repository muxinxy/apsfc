package service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import po.Types;

public class TypesServiceTest {
	private TypesService typesService=new TypesService();

	@Test
	public void testAdd() {
		Types types=new Types();
		types.setName("111");
		System.out.println(typesService.add(types));
	}

	@Test
	public void testDel() {
		int id=13;
		System.out.println(typesService.del(id));
	}

	@Test
	public void testChg() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		ArrayList<Types> list=typesService.findAll();
		for(Types types:list){
			System.out.println(types);
		}
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

}
