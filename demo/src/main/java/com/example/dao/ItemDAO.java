package com.example.dao;

import java.util.List;

import com.example.vo.ItemVO;

public interface ItemDAO {
	public List<ItemVO> selectItemList();
	public ItemVO selectItemOne(int no);
	
	public List<ItemVO> selectItemSearch(String txt);
	
	public int insertItemBatch(List<ItemVO> list);
	
	public int deleteItemBatch(int[] no);
	
	public List<ItemVO> selectItemWhere(int[] itemno);
	
	//일괄수정
	public int updateItemBatch(List<ItemVO> list);
}