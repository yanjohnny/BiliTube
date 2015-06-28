package org.succlz123.bilitube.app.dao.Api;

import org.succlz123.bilitube.app.bean.bangumi.BangumiArray;
import org.succlz123.bilitube.app.dao.helper.BiliUrlHelper;

import java.util.List;

/**
 * Created by fashi on 2015/5/5.
 */
public class BangumiApi {

	public static List getBangumiList() {
		String url = BiliUrlHelper.BILIBANGUMI;
		String json = OkHttpApi.getInstance().doGet(url);

		return BangumiArray.parseJson(json);
	}
}
