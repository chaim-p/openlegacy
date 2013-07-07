package org.openlegacy.designtime;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openlegacy.designtime.rpc.source.parsers.CobolNumberInformationTest;
import org.openlegacy.designtime.rpc.source.parsers.CobolParserTest;

@RunWith(Suite.class)
@SuiteClasses({ CobolParserTest.class, CobolNumberInformationTest.class })
public class OpenLegacyRpcDesigntimeSuite {

}
