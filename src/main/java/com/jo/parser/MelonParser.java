package com.jo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jo.parser.model.Music;

public class MelonParser implements MusicParser {

	@Override
	public List<Music> execute() {
		String url = "http://www.melon.com/chart/index.htm";

		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Elements trs1 = document.select(".lst50");
		Elements trs2 = document.select(".lst100");
		if (trs2 != null) {
			trs1.addAll(trs2);
		}

		List<Music> list = new ArrayList<Music>();

		for (Element element : trs1) {
			String rank = element.select(".right_none .rank").text();
			String title = element.select(".wrap_song_info div strong a").text();
			String artist = element.select(".wrap_song_info .rank02 > .play_artist span").text();
			String album = element.select(".fc_mgray").text();
			String imageurl = element.select(".image_type15 img").attr("src");

			Music music = new Music(Integer.parseInt(rank), title, artist, album, imageurl);
			list.add(music);
		}

		return list;
	}

}
