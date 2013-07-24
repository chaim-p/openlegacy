package org.openlegacy.designtime.rpc.source.parsers;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

// Test in this file verify preparation  steps for koopa parser.
@ContextConfiguration("CobolParserTest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CobolParserUtilsTest {

	@Test
	public void replaceInFile() throws IOException {

		String sourceBeforeFile = "sampcpy1.cpy";
		String sourceAfterFile = "sampcpy1r.cpy";
		String source = IOUtils.toString(getClass().getResource(sourceBeforeFile));

		TemporaryFolder tempFolder = new TemporaryFolder();
		tempFolder.create();
		File tempFile = tempFolder.newFile(sourceBeforeFile);
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		writer.write(source);
		writer.close();

		CobolParserUtils.replaceStringInFile(":XXX:", "C00-", tempFile.getPath(), "");

		String expexted = IOUtils.toString(getClass().getResource(sourceAfterFile));
		String actual = IOUtils.toString(new FileReader(new File(tempFile.getPath())));
		Assert.assertTrue(expexted.equals(actual));
	}

	@Test
	public void testPreProcessCopy() throws IOException {

		Map<String, InputStream> streamMap = new HashMap<String, InputStream>();

		streamMap.put("sampcpy1.cpy", getClass().getResourceAsStream("sampcpy1.cpy"));
		streamMap.put("sampcpy2.cpy", getClass().getResourceAsStream("sampcpy2.cpy"));

		String copyBookPath = CobolParserUtils.CreateTmpDir("preProcess");
		CobolParserUtils.copyStreamsToFile(copyBookPath, streamMap);

		File copyBookDir = new File(copyBookPath);
		String fileList[] = copyBookDir.list();
		Assert.assertArrayEquals(streamMap.keySet().toArray(), fileList);

	}
}
