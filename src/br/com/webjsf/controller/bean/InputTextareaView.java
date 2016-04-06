package br.com.webjsf.controller.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "inputTextareaView")
@ViewScoped
public class InputTextareaView {
	
	public void caralho(){
		
	}
	
	public List<String> completeArea(String query) {

		List<String> results = new ArrayList<String>();

		if (query.equals("PrimeFaces")) {
			results.add("PrimeFaces Rocks!!!");
			results.add("PrimeFaces has 100+ components.");
			results.add("PrimeFaces is lightweight.");
			results.add("PrimeFaces is easy to use.");
			results.add("PrimeFaces is developed with passion!");
		} else {
			for (int i = 0; i < 10; i++) {
				results.add(query + i);
			}
		}
		return results;
	}
}