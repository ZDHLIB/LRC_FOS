package net.lrc.util;

import java.util.List;

public interface Pageable {
	@SuppressWarnings("unchecked")
	public List getResult();

	public int getCount();

	public int getPageSize();

	public int getCurrentPage();

	public int getPages();
}
