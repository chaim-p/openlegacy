/*******************************************************************************
 * Copyright (c) 2012 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/
package com.as400samplecode;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400File;
import com.ibm.as400.access.AS400FileRecordDescription;
import com.ibm.as400.access.QSYSObjectPathName;
import com.ibm.as400.access.Record;
import com.ibm.as400.access.RecordFormat;
import com.ibm.as400.access.SequentialFile;

public class FetchSource {

	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Usage:" + CallCobolProgramPcml.class.getSimpleName() + " host user password [file]");
			return;
		}

		String host = args[0];
		String user = args[1];
		String password = args[2];
		String fileName = "/QSYS.LIB/RMR2L1.LIB/QRPGLESRC.FILE/RPGROICH.MBR";

		if (args.length==4)
			fileName = args[3];
		
		try {
			AS400 as400 = new AS400(host, user, password);
			as400.connectService(AS400.RECORDACCESS);
			QSYSObjectPathName filename = new QSYSObjectPathName(fileName);
			SequentialFile file = new SequentialFile(as400, filename.getPath());
			// Retrieve the record format for the file
			AS400FileRecordDescription recordDescription = new AS400FileRecordDescription(as400, filename.getPath());
			RecordFormat[] format = recordDescription.retrieveRecordFormat();
			file.setRecordFormat(format[0]);
			file.open(AS400File.READ_ONLY, 100, AS400File.COMMIT_LOCK_LEVEL_NONE);
			Record data = file.readNext();
			// Loop while there are records in the file (while we have not
			// reached end-of-file).

			while (data != null) {
				// Object field = data.getField(1);
				data = file.readNext();
				System.out.println(data);
			}
		}

		catch (Exception de) {
			System.out.println(de.getMessage());
			de.printStackTrace();
			System.exit(0);
		}

	}
}
