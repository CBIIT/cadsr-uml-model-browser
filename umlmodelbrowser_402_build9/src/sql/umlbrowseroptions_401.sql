SET SCAN OFF;
/*
    Database values owned by UMLBrowser
*/
MERGE INTO sbrext.tool_options_view_ext s
   USING (SELECT 'UMLBrowser' AS tool_name, 'NCI_METATHESAURUS_URL' AS property,
		 'http://bioportal.nci.nih.gov/ncbo/faces/pages/quick_search.xhtml' AS value,
                 'URL for EVS NCI_METATHESAURUS_URL.' AS description
            FROM DUAL
          UNION
          SELECT 'UMLBrowser' AS tool_name, 'NCI_TERMINOLOGY_SERVER_URL' AS property,
		 'http://bioportal.nci.nih.gov/ncbo/faces/pages/advanced_search.xhtml' AS value,
                 'URL for NCI_TERMINOLOGY_SERVER_URL.' AS description
          FROM DUAL
	  UNION
          SELECT 'UMLBrowser' AS tool_name, 'ADMIN_TOOL_URL' AS property,
		 'http://cadsradmin@tier@.nci.nih.gov' AS value,
                 'URL for Admintool.' AS description
          FROM DUAL) t
   ON (s.tool_name = t.tool_name AND s.property = t.property)
   WHEN MATCHED THEN
      UPDATE
         SET s.value = t.value, s.description = t.description
   WHEN NOT MATCHED THEN
      INSERT (tool_name, property, value, description, locale)
      VALUES (t.tool_name, t.property, t.value, t.description, 'US');

/*
    Commit changes.
*/
commit;
