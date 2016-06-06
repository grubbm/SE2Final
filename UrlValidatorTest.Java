/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;


/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

	
   	public UrlValidatorTest(String testName) {
      	super(testName);
   	}

   	public void testManualTest()
   	{
		ResultPair[] manuals = {
			new ResultPair("http://www.google.com", true),
			new ResultPair("test://www.google.com", false),
			new ResultPair("https://www.google.com", true),
			new ResultPair("www.google.com", false),
			new ResultPair("http://google.com", true),
			new ResultPair("http:///www.google.com", true),
			new ResultPair("http://www.google.com/", true),
			new ResultPair("http:/\\www.google.com", false),
			new ResultPair("htt://www.google.com", false),
			new ResultPair("http://vww.google.com", false),
			new ResultPair("httP://www.google.com", true),
			new ResultPair("httpb://www.google.com", false),
			new ResultPair("1http://www.google.com", false),
			new ResultPair("http://www.amazon.co.uk/", true),
			new ResultPair("http://www.amazon.falsecountry", false),
			new ResultPair("http://www.azcardinals.com", true),
			new ResultPair("http://www.nationalreview.com/", true),
			new ResultPair("http://www.google.com:80/", true),
			new ResultPair("http://www.google.com:-80", false),
			new ResultPair("http://www.google.com:80b", false),	
			new ResultPair("https://secure.engr.oregonstate.edu:8000/teach.php?type=want_auth", true),
			new ResultPair("sftp://student@access.engr.oregon.edu", false),
			new ResultPair("https://github.com/aburasali/cs362sp16/commit/5584f41cd06d02b131ea9b2bc5a182a21b36aa3b", true),
			new ResultPair("ftp://127.0.0.1", true),
			new ResultPair("https:///127.0.0.1", true),
			new ResultPair("https://www.google.com/#q=cardinals", true),
	    	};
String expected, actual;
int manualIndex;
boolean exp, act;

		String[] schemes = {"http", "https", "ftp", "httP"};

	   	UrlValidator validatorRun = new UrlValidator(schemes);//null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   	UrlValidator defaultRun = new UrlValidator(); //http, https, and ftp are valid
	   	UrlValidator schemeRun = new UrlValidator(schemes); //passed schemes are valid
	   	
	   	
	   	System.out.println("\n******** Manual Testing Run ******** \n");
	   	System.out.println("**ALLOW_ALL_SCHEMES run**\n\n");

	   	for (manualIndex = 0; manualIndex < manuals.length; manualIndex++) {
		   
		  // Check actual pair values 
		   act = validatorRun.isValid(manuals[manualIndex].item);
		   actual = (act) ? "VALID" : "INVALID";
	   		
		   // Check expected pair values
		   exp = manuals[manualIndex].valid;
		   expected = (exp) ? "VALID" : "INVALID";
		   
		   	// If fail ALLOW_ALL_SCHEMES
		   	if (!expected.equals(actual)) {
				System.out.println("Testing url: " + manuals[manualIndex].item + "\n  Expected Result: " + expected);
				System.out.println("  FAILURE, actual: " + actual);
			   
		   	}
		   	// if pass ALLOW_ALL_SCHEMES
		   	else {
				//System.out.println("Testing url: " + manuals[manualIndex].item + "\n  Expected Result: " + expected + "\n  PASSED, actual: " + actual);
		   	}
	   	}  	
		   	System.out.println("\n\n**Default scheme run**\n\n");
		   	for (manualIndex = 0; manualIndex < manuals.length; manualIndex++) {

		 // If fail ALLOW_ALL_SCHEMES
		   	if (!( manuals[manualIndex].valid) == (defaultRun.isValid(manuals[manualIndex].item))) {
				System.out.println("Testing url: " + manuals[manualIndex].item + "\n  Expected Result: " + manuals[manualIndex].valid);
				System.out.println("  FAILURE, actual: " + defaultRun.isValid(manuals[manualIndex].item));
			   
		   	}
		   	// if pass ALLOW_ALL_SCHEMES
		   	else {
				//System.out.println("Testing url: " + manuals[manualIndex].item + "\n  Expected Result: " + manuals[manualIndex].valid + "\n  PASSED, actual: " + defaultRun.isValid(manuals[manualIndex].item));
		   	}
		   	}
		   	
		   	System.out.println("\n\n**String set schemes in constructor run\n\n");
		   	for (manualIndex = 0; manualIndex < manuals.length; manualIndex++) {

		   	// If fail ALLOW_ALL_SCHEMES
			   	if (!( manuals[manualIndex].valid) == (schemeRun.isValid(manuals[manualIndex].item))) {
					System.out.println("Testing url: " + manuals[manualIndex].item + "\n  Expected Result: " + manuals[manualIndex].valid);
					System.out.println("  FAILURE, actual: " + schemeRun.isValid(manuals[manualIndex].item));
				   
			   	}
			   	// if pass ALLOW_ALL_SCHEMES
			   	else {
					//System.out.println("Testing url: " + manuals[manualIndex].item + "\n  Expected Result: " + manuals[manualIndex].valid + "\n  PASSED, actual: " + schemeRun.isValid(manuals[manualIndex].item));
			   	}
		   	
	   	}
   	
	  
	   
   }
   
   
   public void testPartitionSegment()
   {
	//	String[] schemes = {"http", "https", "ftp", "httP"};

	   int sindex, hindex, portIndex, pathIndex, queryIndex;
		UrlValidator schemeRun = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		UrlValidator authorityRun = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		UrlValidator pathRun = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		UrlValidator queryRun = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		
		SchemesResults[] schemesResultArray = new SchemesResults[11];
		HostsResults[] hostResultArray = new HostsResults[16];
		PortsResults[] portResultArray = new PortsResults[9];
		PathsResults[] pathResultArray = new PathsResults[9];
		QueriesResults[] queryResultArray = new QueriesResults[5];

		System.out.println("\nFirst Partition: Testing Schemes, Hosts, Ports, Paths and Queries By Section");
	  
		for (sindex = 0; sindex < testSchemes.length; sindex++)
		{
			schemesResultArray[sindex] = new SchemesResults();
			schemesResultArray[sindex].schemeName = testSchemes[sindex].item;
			schemesResultArray[sindex].validity = testSchemes[sindex].valid;
			System.out.println( "\nScheme: " + testSchemes[sindex].item);
			System.out.println( "  Validator: " + schemeRun.isValidScheme(testSchemes[sindex].item) );
			System.out.println( "  Is valid?  " + testSchemes[sindex].valid );
			
			if( schemeRun.isValidScheme(testSchemes[sindex].item) == true && testSchemes[sindex].valid == false ){
				schemesResultArray[sindex].failCount++;	
			} else if ( schemeRun.isValidScheme(testSchemes[sindex].item) == false && testSchemes[sindex].valid == true ){
				schemesResultArray[sindex].failCount++;
			} else {
				schemesResultArray[sindex].passCount++;
			}
		}
			
		//Host Run and Nested Port Run for .isValidAuthority() URLValidator method
		for (hindex = 0; hindex < testHosts.length; hindex++)
		{
			hostResultArray[hindex] = new HostsResults();
			hostResultArray[hindex].hostName = testHosts[hindex].item;
			hostResultArray[hindex].validity = testHosts[hindex].valid;
			
			//Port Run nested for .isValidAuthority() URLValidator method
			for (portIndex = 0; portIndex < testPorts.length; portIndex++)
			{
				if(  hindex == 0) //Needed so this array isn't reset on each outer loop
				{ 
					portResultArray[portIndex] = new PortsResults();
					portResultArray[portIndex].portName = testPorts[portIndex].item;
					portResultArray[portIndex].validity = testPorts[portIndex].valid;
				}
				
				//test that authority( host + port ) correctly test as valid/invalid
				//Test if Authority is valid if port is invalid
				if( authorityRun.isValidAuthority( testHosts[hindex].item + testPorts[portIndex].item) == true && testPorts[portIndex].valid == false )
				{
					portResultArray[portIndex].failCount++;	
				} //Test if Authority is valid if host is invalid
				else if ( authorityRun.isValidAuthority( testHosts[hindex].item + testPorts[portIndex].item) == true && testHosts[hindex].valid == false )
				{
					hostResultArray[hindex].failCount++;
				}//Test if Authority is invalid if hosts and ports are valid
				else if ( authorityRun.isValidAuthority( testHosts[hindex].item + testPorts[portIndex].item) == false && (testPorts[portIndex].valid == true && testHosts[hindex].valid == true) )
				{
					portResultArray[portIndex].failCount++;
				//If either or both are invalid then Authority should be invalid, or if both are valid so should authority
				}
				else 
				{
					portResultArray[portIndex].passCount++;
					hostResultArray[hindex].passCount++;
				}
				
			}
		}
		
		//Path Run
		for (pathIndex = 0; pathIndex < testPaths.length; pathIndex++)
		{
			pathResultArray[pathIndex] = new PathsResults();
			pathResultArray[pathIndex].pathName = testPaths[pathIndex].item;
			pathResultArray[pathIndex].validity = testPaths[pathIndex].valid;
			
			//test that paths correctly test as valid/invalid
			if( pathRun.isValidPath(testPaths[pathIndex].item) == true && testPaths[pathIndex].valid == false ){
				pathResultArray[pathIndex].failCount++;
			} else if ( pathRun.isValidPath(testPaths[pathIndex].item) == false && testPaths[pathIndex].valid == true ){
				pathResultArray[pathIndex].failCount++;
			} else {
				pathResultArray[pathIndex].passCount++;
			}
		}
						
		for (queryIndex = 0; queryIndex < testQueries.length; queryIndex++)
		{
			queryResultArray[queryIndex] = new QueriesResults();
			queryResultArray[queryIndex].queryName = testQueries[queryIndex].item;
			queryResultArray[queryIndex].validity = testQueries[queryIndex].valid;
			
			//Test that queries correctly test as valid/invalid
			if( queryRun.isValidQuery(testQueries[queryIndex].item) == true && testQueries[queryIndex].valid == false ){
				queryResultArray[queryIndex].failCount++;
			} else if (queryRun.isValidQuery(testQueries[queryIndex].item) == false && testQueries[queryIndex].valid == true ){
				queryResultArray[queryIndex].failCount++;
			} else {
				queryResultArray[queryIndex].passCount++;
			}
		}
							
		//Print Results
		//SCHEME RESULTS
		System.out.println("\nSchemes Results:");
		for (sindex = 0; sindex < testSchemes.length; sindex++)
		{
			if( schemesResultArray[sindex].passCount == 0 && schemesResultArray[sindex].failCount == 0 )
			{
				System.out.println("***ERROR NO RESULTS AT " + schemesResultArray[sindex].schemeName);
			} 
			else 
			{
				if( schemesResultArray[sindex].failCount == 0)
				{
					System.out.println("  " + String.format("%-10s %-4s", schemesResultArray[sindex].schemeName, "PASSED" ));
				} 
				else if( schemesResultArray[sindex].passCount == 0)
				{
					System.out.println("  " + String.format("%-10s %-4s", schemesResultArray[sindex].schemeName, "FAILED" ));
				} 
				else 
				{
					System.out.println("  " + schemesResultArray[sindex].schemeName);
					System.out.println("     *FAILURES: " + schemesResultArray[sindex].failCount);
					System.out.println("     *PASSES: " + schemesResultArray[sindex].passCount);
				}
			}
		}
		//HOST RESULTS
		System.out.println("\nHost Results:");
		for (hindex = 0; hindex < testHosts.length; hindex++)
		{
			if( hostResultArray[hindex].passCount == 0 && hostResultArray[hindex].failCount == 0 )
			{
				System.out.println("***ERROR NO RESULTS AT " + hostResultArray[hindex].hostName);
			} 
			else 
			{
				if( hostResultArray[hindex].failCount == 0)
				{
					System.out.println("  " + String.format("%-20s %-4s", hostResultArray[hindex].hostName, "PASSED" ));
				} 
				else if( hostResultArray[hindex].passCount == 0)
				{
					System.out.println("  " + String.format("%-20s %-4s", hostResultArray[hindex].hostName, "FAILED" ));
				}
				else 
				{
					System.out.println("  " + hostResultArray[hindex].hostName);
					System.out.println("     *FAILURES: " + hostResultArray[hindex].failCount);
					System.out.println("     *PASSES: " + hostResultArray[hindex].passCount);
				}
			}
		}
		//PORT RESULTS
		System.out.println("\nPort Results:");
		for (portIndex = 0; portIndex < testPorts.length; portIndex++)
		{
			if( portResultArray[portIndex].passCount == 0 && portResultArray[portIndex].failCount == 0 ){
				System.out.println("***ERROR NO RESULTS AT " + portResultArray[portIndex].portName);
			}
			else 
			{
				if( portResultArray[portIndex].failCount == 0)
				{
					System.out.println("  " + String.format("%-10s %-4s", portResultArray[portIndex].portName, "PASSED" ));
				} 
				else if( portResultArray[portIndex].passCount == 0)
				{
					System.out.println("  " + String.format("%-10s %-4s", portResultArray[portIndex].portName, "FAILED" ));
				}
				else 
				{
					System.out.println("  " + portResultArray[portIndex].portName);
					System.out.println("     *FAILURES: " + portResultArray[portIndex].failCount);
					System.out.println("     *PASSES: " + portResultArray[portIndex].passCount);
				}
			}
		}
		//PATH RESULTS
		System.out.println("\nPath Results:");
		for (pathIndex = 0; pathIndex < testPaths.length; pathIndex++)
		{
			if( pathResultArray[pathIndex].passCount == 0 && pathResultArray[pathIndex].failCount == 0 ){
				System.out.println("***ERROR NO RESULTS AT " + pathResultArray[pathIndex].pathName);
			}
			else 
			{
				if( pathResultArray[pathIndex].failCount == 0)
				{
					System.out.println("  " + String.format("%-17s %-4s", pathResultArray[pathIndex].pathName, "PASSED" ));
				} 
				else if( pathResultArray[pathIndex].passCount == 0)
				{
					System.out.println("  " + String.format("%-17s %-4s", pathResultArray[pathIndex].pathName, "FAILED" ));
				} 
				else 
				{
					System.out.println("  " + pathResultArray[pathIndex].pathName);
					System.out.println("     *FAILURES: " + pathResultArray[pathIndex].failCount);
					System.out.println("     *PASSES: " + pathResultArray[pathIndex].passCount);
				}
			}
		}
		//QUERY RESULTS
		System.out.println("\nQuery Results:");
		for (queryIndex = 0; queryIndex < testQueries.length; queryIndex++)
		{
			if( queryResultArray[queryIndex].passCount == 0 && queryResultArray[queryIndex].failCount == 0 ){
				System.out.println("***ERROR NO RESULTS AT " + queryResultArray[queryIndex].queryName);
			} 
			else 
			{
				if( queryResultArray[queryIndex].failCount == 0)
				{
					System.out.println("  " + String.format("%-40s %-4s", queryResultArray[queryIndex].queryName, "PASSED" ));
				} 
				else if( queryResultArray[queryIndex].passCount == 0)
				{
					System.out.println("  " + String.format("%-40s %-4s", queryResultArray[queryIndex].queryName, "FAILED" ));
					System.out.println("     *validated as " + queryRun.isValidQuery(testQueries[queryIndex].item) + " vs value: " + queryResultArray[queryIndex].validity + "\n");
				}
				else 
				{
					System.out.println("  " + queryResultArray[queryIndex].queryName);
					System.out.println("     *FAILURES: " + queryResultArray[queryIndex].failCount);
					System.out.println("     *PASSES: " + queryResultArray[queryIndex].passCount);
				}
			}
		}
   }
   
   public void testYourSecondPartition(){
	   //Test Combinations of URL Parts??
	   /*
	    * 
	    for (queryIndex = 0; queryIndex < testQueries.length; queryIndex++)
		{
			//Needed so this array isn't reset on each outer loop
			if( sindex == 0 && hindex == 0 && portIndex == 0 && pathIndex == 0 ){
				queryResultArray[queryIndex] = new QueriesResults();
				queryResultArray[queryIndex].queryName = testQueries[queryIndex].item;
				queryResultArray[queryIndex].validity = testQueries[queryIndex].valid;
				
				//Test that queries correctly test as valid/invalid
				if( queryRun.isValidQuery(testQueries[queryIndex].item) == true && testQueries[queryIndex].valid == false ){
					queryResultArray[queryIndex].failCount++;
				} else if (queryRun.isValidQuery(testQueries[queryIndex].item) == false && testQueries[queryIndex].valid == true ){
					queryResultArray[queryIndex].failCount++;
				} else {
					queryResultArray[queryIndex].passCount++;
				}
			}
		}
	   url = URLSynth(sindex, hindex, portIndex, pathIndex, queryIndex);
		urlTest = (url.valid) ? "VALID" : "INVALID"; 
		

		if (url.valid) {
			if (!validatorRun.isValid(url.item)) {
				//System.out.println("FAILURE: expected VALID but returned INVALID: ");
				//System.out.println(url.item + " result: " + urlTest);
				schemesResultArray[sindex].urlFailCount++;
				hostResultArray[hindex].urlFailCount++;
				portResultArray[portIndex].urlFailCount++;
				pathResultArray[pathIndex].urlFailCount++;
				queryResultArray[queryIndex].urlFailCount++;
				failed = true;
			}
		}

		else {
			if (validatorRun.isValid(url.item)) {
				//System.out.println("FAILURE: expected INVALID but returned VALID: ");
				//System.out.println(url.item + " result: " + urlTest);
				schemesResultArray[sindex].urlFailCount++;
				hostResultArray[hindex].urlFailCount++;
				portResultArray[portIndex].urlFailCount++;
				pathResultArray[pathIndex].urlFailCount++;
				queryResultArray[queryIndex].urlFailCount++;
				failed = true;
			}								
		}
		
		if (failed == true){
			schemesResultArray[sindex].urlPassCount++;
			hostResultArray[hindex].urlPassCount++;
			portResultArray[portIndex].urlPassCount++;
			pathResultArray[pathIndex].urlPassCount++;
			queryResultArray[queryIndex].urlPassCount++;
		}
		failed = false;
	}
	*/
   }
   
   
   public void testIsValid()
   {
		String[] schemes = {"http", "https", "ftp", "httP", "sftp"};

	   	int sindex, hindex, portIndex, pathIndex, queryIndex;
		UrlValidator validatorRun = new UrlValidator(schemes); //null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		ResultPair url;
		String urlTest;
		
		

		System.out.println("\nRun Programming Testing Section");
	  
		for (sindex = 0; sindex < testSchemes.length; sindex++)
		{
			for (hindex = 0; hindex < testHosts.length; hindex++)
			{
				for (portIndex = 0; portIndex < testPorts.length; portIndex++)
				{
					for (pathIndex = 0; pathIndex < testPaths.length; pathIndex++)
					{
						for (queryIndex = 0; queryIndex < testQueries.length; queryIndex++)
						{
							url = URLSynth(sindex, hindex, portIndex, pathIndex, queryIndex);
							urlTest = (url.valid) ? "VALID" : "INVALID"; 
							

							if (url.valid) {
								if (!validatorRun.isValid(url.item)) {
									System.out.println("FAILURE: expected VALID but returned INVALID: ");
									System.out.println(url.item + " result: " + urlTest);
								}
							}

							else {
								if (validatorRun.isValid(url.item)) {
									System.out.println("FAILURE: expected INVALID but returned VALID: ");
									System.out.println(url.item + " result: " + urlTest);
								}								
							}
					 	}
				 	}
			 	}
		 	}
		}
  	}
   
   public void testAnyOtherUnitTest()
   {

   }
   
   
   ResultPair[] testSchemes = {
			new ResultPair("", true), 
			new ResultPair("http://", true),
			new ResultPair("https://", true),
			new ResultPair("ftp://", true),
			new ResultPair("sftp://", true),
			new ResultPair("http:///", false),
			new ResultPair("http{://", false),
			new ResultPair("hyttp://", false),
			new ResultPair("http:/", false),
			new ResultPair("http//", false),
			new ResultPair("http::", false)
		};
   
	   class SchemesResults {
			String schemeName;
			Boolean validity;
			int passCount = 0;
			int failCount = 0;
			int urlPassCount = 0;
			int urlFailCount = 0;
		};
	   
		ResultPair[] testHosts = {
			new ResultPair("", false),
			new ResultPair("www.google.com", true),
			new ResultPair("www.godaddy.com", true),
			new ResultPair("www.yahoo.com", true),
			new ResultPair("www.azcardinals.com", true),
			new ResultPair("www.nike.com", true),
			new ResultPair("google", false),
			new ResultPair("godaddy", false),
			new ResultPair("yahoo", false),
			new ResultPair("azcardinals.com", true),
			new ResultPair("oregonstate.edu", true),
			new ResultPair("nike.com", true),
			new ResultPair("127.0.0.1", true),
			new ResultPair("127.0.1", false),
			new ResultPair("255.255.255.255", true),
			new ResultPair("256.256.256.256", false)
		};
	   
		class HostsResults {
			String hostName;
			Boolean validity;
			int passCount = 0;
			int failCount = 0;
			int urlPassCount = 0;
			int urlFailCount = 0;
		};
		
		ResultPair[] testPorts = {
			new ResultPair("", true),
			new ResultPair(":80", true),
			new ResultPair(":8080", true),
			new ResultPair(":80b", false),
			new ResultPair(":-80", false),
			new ResultPair(":80-", false),
			new ResultPair(":/80", false),
			new ResultPair(":65535", true),
			new ResultPair(":65536", false)
		};
			
		class PortsResults {
			String portName;
			Boolean validity;
			int passCount = 0;
			int failCount = 0;
			int urlPassCount = 0;
			int urlFailCount = 0;
		};
		
		ResultPair[] testPaths = {
			new ResultPair("", true),
			new ResultPair("/testPath", true),
			new ResultPair("/test Path", false),
			new ResultPair("/testPath/", true),
			new ResultPair("/tpath/../tpath1", true),
			new ResultPair("/path/name", true),
			new ResultPair("/tpath2/..tpath1", false),
			new ResultPair("/tpath2//name", false),
			new ResultPair("empty", false)
		};
		
		class PathsResults {
			String pathName;
			Boolean validity;
			int passCount = 0;
			int failCount = 0;
			int urlPassCount = 0;
			int urlFailCount = 0;
		};
		
		ResultPair[] testQueries = {
			new ResultPair("", true),
			new ResultPair("?testquery=testvalue", true),
			new ResultPair("?testquery1=testval1&testquery2=testval2", true),
			new ResultPair("?testquery1=testval1&te stquery2=testval2", false),
			new ResultPair("?testquery=testval^testquery2=testval2", false),
			new ResultPair("?=testval", false)
		};
		
		class QueriesResults {
			String queryName;
			Boolean validity;
			int passCount = 0;
			int failCount = 0;
			int urlPassCount = 0;
			int urlFailCount = 0;
		};
			   
		public ResultPair URLSynth(int sindex, int hindex, int portIndex, int pathIndex, int queryIndex)
		{
			boolean isValid;
			
			if ((sindex >= testSchemes.length) || (hindex >= testHosts.length) || (portIndex >= testPorts.length) || ( pathIndex >= testPaths.length)|| (queryIndex >= testQueries.length))
			{
				return new ResultPair("Bad URL", false);
			}
			
			else 
			{
				String testUrl = testSchemes[sindex].item + testHosts[hindex].item + testPorts[portIndex].item + testPaths[pathIndex].item + testQueries[queryIndex].item;
		
				isValid = true;

				if(!testSchemes[sindex].valid || !testHosts[hindex].valid || !testPorts[portIndex].valid || !testPaths[pathIndex].valid || !testQueries[queryIndex].valid)
				{
					isValid = false;
				}
			
				ResultPair testPair = new ResultPair(testUrl, isValid);
		
				return testPair;
			}
		}
		
		
	}
