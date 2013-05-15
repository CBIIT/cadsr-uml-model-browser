/* Copyright NCI/NCICB, 2005

   $Header: $
   $Name:  $

   Author: Tejas Dave

   This script loads the Tool Options table with required and optional values
   for the UMLModelBrowser Tool.

   Each is described briefly below. A full description of each can be found in
   the Tool Installation Guide. These values must be reviewed and
   changed as needed per the local installation and database instance.  

*/
whenever sqlerror exit sql.sqlcode rollback;

/*
  ==============================================================================
  Required Settings (do not comment or remove)
  ==============================================================================
*/
/*
   CDEBrowser url and download directory.
*/

MERGE INTO SBREXT.TOOL_OPTIONS_VIEW_EXT S
	USING (SELECT 'UMLBrowser' AS TOOL_NAME, 'PRIVACY_URL' AS PROPERTY, 'https://wiki.nci.nih.gov/x/qxEhAQ' AS VALUE, 'caDSR Privacy Policy Statement' AS DESCRIPTION, 'US' AS LOCALE FROM DUAL
) T
ON (S.TOOL_NAME = T.TOOL_NAME AND S.PROPERTY = T.PROPERTY)
WHEN MATCHED THEN UPDATE SET S.VALUE = S.VALUE, S.DESCRIPTION = T.DESCRIPTION, S.LOCALE = T.LOCALE
WHEN NOT MATCHED THEN INSERT (TOOL_NAME, PROPERTY, VALUE, DESCRIPTION, LOCALE) VALUES (T.TOOL_NAME, T.PROPERTY, T.VALUE, T.DESCRIPTION, T.LOCALE);

commit;

exit
