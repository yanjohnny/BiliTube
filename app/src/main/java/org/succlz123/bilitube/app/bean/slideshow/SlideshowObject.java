package org.succlz123.bilitube.app.bean.slideshow;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by fashi on 2015/5/1.
 */
public class SlideshowObject {
	private int results;
	private List<SlideshowList> list;

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public List<SlideshowList> getList() {
		return list;
	}

	public void setList(List<SlideshowList> list) {
		this.list = list;
	}

	private static SlideshowObject parseJson(String json) {
		Gson gson = new Gson();

		return gson.fromJson(json, SlideshowObject.class);
	}
}
