package com.jo.melon;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Melon {

	public static void main(String[] args) {
		melonParsing();
	}
	
	public static void melonParsing() {
		try {
			
			String url = "http://www.melon.com/chart/index.htm";
			Document document = Jsoup.connect(url).get();
			
			Elements trs1 = document.select(".lst50");
			Elements trs2 = document.select(".lst100");
			
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
			
			for (Element element : trs2) {
				String rank = element.select(".right_none .rank").text();
				String title = element.select(".wrap_song_info div strong a").text();
				String artist = element.select(".wrap_song_info .rank02 > .play_artist span").text();
				String album = element.select(".fc_mgray").text();
				String imageurl = element.select(".image_type15 img").attr("src");
				
				Music music = new Music(Integer.parseInt(rank), title, artist, album, imageurl);
				list.add(music);
			}
			
			for (Music music : list) {
				System.out.println(music.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
