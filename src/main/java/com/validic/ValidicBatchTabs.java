/**
 * 
 */
package com.validic;

/**
 * @author IDC-0004
 *
 */
public class ValidicBatchTabs {
	
	private int tabNumber;
	private String tabLabel;
	
	
	public ValidicBatchTabs(int tabNumber, String tabLabel) {
		super();
		this.tabNumber = tabNumber;
		this.tabLabel = tabLabel;
	}
	public int getTabNumber() {
		return tabNumber;
	}
	public void setTabNumber(int tabNumber) {
		this.tabNumber = tabNumber;
	}
	public String getTabLabel() {
		return tabLabel;
	}
	public void setTabLabel(String tabLabel) {
		this.tabLabel = tabLabel;
	}
	
	

}
