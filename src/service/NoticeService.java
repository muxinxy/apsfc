package service;

import java.util.ArrayList;

import dao.NoticeDao;
import po.Notice;

public class NoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	public ArrayList<Notice> findAll() {
		return noticeDao.findAll();
	}
	// 添加
	public int add(Notice notice) {
		return noticeDao.add(notice);
	}

	// 删除
	public int del(int id) {
		return noticeDao.del(id);
	}

	public Notice findByName(String name) {
		return noticeDao.findByName(name);
	}

}
