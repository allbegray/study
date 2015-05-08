package com.jo.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jo.parser.model.Music;

public class MelonParserTest {

	List<Music> musics = null;

	@Before
	public void setup() {
		MelonParser melonParser = new MelonParser();
		musics = melonParser.execute();
	}

	@Test
	public void isSize100Test() {
		Assert.assertTrue(musics.size() == 100);
	}

	@Test
	public void hasContentTest() {
		Music top1 = musics.get(0);
		Assert.assertTrue(top1.getImageurl().startsWith("http"));
		Assert.assertTrue(top1.getAlbum() != null);
		Assert.assertTrue(top1.getArtist() != null);
		Assert.assertTrue(top1.getTitle() != null);
		Assert.assertTrue(top1.getRank() > 0);
	}

	@Test
	public void isAscDirectionTest() {
		Music top1 = musics.get(0);
		Assert.assertTrue(top1.getRank() == 1);
	}

}
