package org.openlegacy.terminal.modules.trail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openlegacy.AbstractTest;
import org.openlegacy.modules.trail.Trail;
import org.openlegacy.modules.trail.TrailWriter;
import org.openlegacy.terminal.actions.SendKeyActions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class XmlTrailWriterTest extends AbstractTest {

	@Inject
	private TrailWriter trailWriter;

	@Test
	public void testTrail() {
		terminalSession.doAction(SendKeyActions.ENTER, null);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		trailWriter.write(terminalSession.getModule(Trail.class).getSessionTrail(), baos);
		System.out.println(new String(baos.toByteArray()));
	}
}