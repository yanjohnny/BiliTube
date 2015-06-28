package org.succlz123.bilitube.app.bean.bangumi;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/5/5.
 */
public class BangumiArray {
	private String spid;
	private String title;
	private String cover;
	private String click;
	private String video_view;
	private String attention;
	private String bgmcount;
	private String priority;
	private String weekday;
	private String lastupdate;
	private String areaid;
	private String typeid;
	private String season_id;
	private String mcover;
	private String scover;
	private String area;
	private String lastupdate_at;
	@Expose
	@SerializedName("new")
	private String _new;

	public String get_new() {
		return _new;
	}

	public void set_new(String _new) {
		this._new = _new;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getVideo_view() {
		return video_view;
	}

	public void setVideo_view(String video_view) {
		this.video_view = video_view;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getBgmcount() {
		return bgmcount;
	}

	public void setBgmcount(String bgmcount) {
		this.bgmcount = bgmcount;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getSeason_id() {
		return season_id;
	}

	public void setSeason_id(String season_id) {
		this.season_id = season_id;
	}

	public String getMcover() {
		return mcover;
	}

	public void setMcover(String mcover) {
		this.mcover = mcover;
	}

	public String getScover() {
		return scover;
	}

	public void setScover(String scover) {
		this.scover = scover;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLastupdate_at() {
		return lastupdate_at;
	}

	public void setLastupdate_at(String lastupdate_at) {
		this.lastupdate_at = lastupdate_at;
	}

	public static List<BangumiArray> parseJson(String json) {
		List<BangumiArray> list = new ArrayList<BangumiArray>();
		Gson gson = new Gson();
		list=gson.fromJson(json, new TypeToken<List<BangumiArray>>() {
		}.getType());

		return list;
	}
}
