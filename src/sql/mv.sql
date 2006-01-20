create materialized view log on object_classes_Ext;
create materialized view log on oc_recs_Ext;
create materialized view log on ac_att_cscsi_ext;
create materialized view log on concepts_Ext;
create materialized view log on component_concepts_Ext;
create materialized view log on con_Derivation_rules_Ext;
create materialized view log on component_levels_Ext;




PROMPT UP_CADSR_PROJECT_MVW

create materialized view UP_CADSR_PROJECT_MVW
   AS SELECT cs_idseq
             ,cs_idseq cp_idseq
             ,preferred_name short_name
             ,long_name long_name
             ,preferred_Definition description
             ,version 
   From classification_schemes
   where cstl_name = 'Project';

PROMPT UP_SUB_PROJECTS_MVW

create materialized view UP_SUB_PROJECTS_MVW
   AS SELECT cast(admincomponent_Crud.cmr_guid as char(36)) sp_idseq 
             ,csi.csi_name  sub_project_name
             ,csi.description sub_project_description
             ,cs.cs_idseq
             ,cs_csi_idseq
             , cp.cp_idseq
   From classification_Schemes cs 
       , cs_csi cscsi
       , class_Scheme_items csi
       , UP_CADSR_PROJECT_MVW cp
    where cs.cs_idseq = cscsi.cs_idseq
    and cscsi.csi_idseq = csi.csi_idseq
    and csitl_name = 'UML_PACKAGE_ALIAS'
    and cs.cs_idseq = cp.cp_idseq;

PROMPT UP_PACKAGES_MVW_TEMP

create materialized view UP_PACKAGES_MVW_TEMP
   AS SELECT cast(admincomponent_Crud.cmr_guid as char(36)) pg_idseq
            ,name
            ,description
            ,cs_idseq
            ,cs_csi_idseq
            ,cp_idseq
            ,p_cs_Csi_idseq
        from(select
             csi.csi_name  name
             ,csi.description description
             ,cp.cs_idseq 
             ,cscsi.cs_csi_idseq
             , cp.cp_idseq
             ,p_cs_Csi_idseq
   From  cs_csi cscsi
       , class_Scheme_items csi
       , UP_CADSR_PROJECT_MVW cp
    where cp.cs_idseq = cscsi.cs_idseq
    and cscsi.csi_idseq = csi.csi_idseq
    and csitl_name ='UML_PACKAGE_NAME'
   union
   SELECT csi.csi_name  name
             ,csi.description description
             ,cscsi.cs_idseq cs_idseq
             ,cscsi.cs_csi_idseq
             , cp.cp_idseq
            ,p_cs_Csi_idseq
   From UP_SUB_PROJECTS_MVW sp
       , cs_csi cscsi
       , class_Scheme_items csi
       , UP_CADSR_PROJECT_MVW cp
    where cscsi.p_cs_csi_idseq = sp.cs_csi_idseq
    and cscsi.csi_idseq = csi.csi_idseq
    and csitl_name ='UML_PACKAGE_NAME'
    and cscsi.cs_idseq = cp.cp_idseq);

PROMPT UP_PACKAGES_MVW
create materialized view UP_PACKAGES_MVW
 AS SELECT   up.pg_idseq
            ,up.name
            ,up.description
            ,up.cs_idseq
            ,up.cs_csi_idseq
            ,up.cp_idseq
            ,sp.sp_idseq
   from UP_PACKAGES_MVW_TEMP up
        ,UP_SUB_PROJECTS_MVW sp
   where up.p_cs_csi_idseq = sp.cs_csi_idseq(+);

PROMPT UP_CLASS_METADATA_MVW_TEMP

 create materialized view UP_CLASS_METADATA_MVW_TEMP
    AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  cm_idseq
              ,desig.name  name
              ,defin.definition description
              ,oc.oc_idseq
              ,pg_idseq
              ,cscsi.cs_idseq
              ,desig.desig_idseq
              ,defin.defin_idseq
              ,pg.cs_csi_idseq
              ,pg.name||'.'||desig.name fully_qualified_class_name
             , cp.cp_idseq
    From object_classes_Ext oc
         , ac_csi accsi
         , UP_PACKAGES_MVW pg
         , cs_csi cscsi
         , designations desig
         , (select *  from definitions
            where defl_name = 'UML Class') defin
         , ac_att_cscsi_ext descsi
         , ac_att_cscsi_ext defcsi
        , UP_CADSR_PROJECT_MVW cp
      where accsi.ac_idseq = oc.oc_idseq
      and cscsi.cs_csi_idseq = accsi.cs_csi_idseq
      and oc.oc_idseq = desig.ac_idseq
      and oc.oc_idseq = defin.ac_idseq(+)
      and descsi.att_idseq = desig.desig_idseq
      and defcsi.att_idseq(+) = defin.defin_idseq
      and pg.cs_Csi_idseq = nvl(defcsi.cs_csi_idseq,pg.cs_Csi_idseq)
      and pg.cs_csi_idseq = descsi.cs_Csi_idseq
      and pg.cs_csi_idseq = accsi.cs_csi_idseq
      and desig.detl_name = 'UML Class'
      and cscsi.cs_idseq = cp.cp_idseq
      and pg.cp_idseq = cp.cp_idseq;


PROMPT UP_GEN_METADATA_MVW


create materialized view UP_GEN_METADATA_MVW
    AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  gm_idseq
             ,ocr_idseq
             ,t_cm_idseq
             ,s_cm_idseq
             ,cs_idseq
             ,cp_idseq
    from(select distinct ocr_idseq
             ,t_cm.cm_idseq  t_cm_idseq
             ,s_cm.cm_idseq  s_cm_idseq
             ,t_cm.cs_idseq
             ,t_Cm.cp_idseq
   From  oc_recs_Ext ocr
       ,UP_CLASS_METADATA_MVW_TEMP t_cm
       ,UP_CLASS_METADATA_MVW_TEMP s_cm
       ,ac_csi accsi
       ,cs_csi cscsi
    where ocr.t_oc_idseq = t_cm.oc_idseq
    and    ocr.s_oc_idseq = s_cm.oc_idseq
    and rl_name = 'IS_A'
    and ocr.ocr_idseq = accsi.ac_idseq
    and accsi.cs_csi_idseq = cscsi.cs_csi_idseq
    and cscsi.cs_idseq = t_cm.cs_idseq
    and t_cm.cp_idseq = s_cm.cp_idseq);
    
PROMPT UP_ASSOCIATIONS_METADATA_MVW

 create materialized view UP_ASSOCIATIONS_METADATA_MVW
 AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  asm_idseq
           ,ocr_idseq
           ,t_cm_idseq
           ,s_Cm_idseq
           ,cs_idseq
           ,cm_idseq
           ,cp_idseq
           ,source_role
           ,target_role
             ,source_low_multiplicity
             ,source_high_multiplicity
             ,target_low_multiplicity
             ,target_high_multiplicity
           ,isbidirectional
     from(SELECT distinct ocr_idseq
             ,t_cm.cm_idseq  t_cm_idseq
             ,s_cm.cm_idseq  s_cm_idseq
             ,a_cm.cs_idseq
             ,a_cm.cm_idseq
             ,cp.cp_idseq
             ,source_role
             ,target_role
             ,source_low_multiplicity
             ,source_high_multiplicity
             ,target_low_multiplicity
             ,target_high_multiplicity
             ,decode(direction,'Bi-Directional',1,0) isbidirectional
   From  oc_recs_Ext ocr
       ,UP_CLASS_METADATA_MVW_TEMP t_cm
       ,UP_CLASS_METADATA_MVW_TEMP s_cm
       ,UP_CLASS_METADATA_MVW_TEMP a_cm
       , ac_csi accsi
       , cs_Csi cscsi
       , UP_CADSR_PROJECT_MVW cp 
    where ocr.t_oc_idseq = t_cm.oc_idseq
    and    ocr.s_oc_idseq = s_cm.oc_idseq
    and  (t_cm.cm_idseq = a_cm.cm_idseq
      or s_Cm.cm_idseq = a_cm.cm_idseq)
    and rl_name <> 'IS_A'
    and ocr.ocr_idseq = accsi.ac_idseq
    --and a_cm.oc_idseq =  cm_accsi.ac_idseq
    --and accsi.cs_Csi_idseq = cm_Accsi.cs_Csi_idseq
    and cscsi.cs_csi_idseq = accsi.cs_csi_idseq
    and cscsi.cs_idseq = cp.cs_idseq
    and t_cm.cp_idseq = s_cm.cp_idseq
    and t_cm.cp_idseq = a_cm.cp_idseq
    and t_cm.cp_idseq = cp.cp_idseq);


PROMPT UP_CLASS_METADATA_MVW

 create materialized view UP_CLASS_METADATA_MVW
    AS SELECT cmt.cm_idseq
              ,cmt.name
              ,cmt.description
              ,cmt.oc_idseq
              ,cmt.pg_idseq
              ,cmt.cs_idseq
              ,cmt.desig_idseq
              ,cmt.defin_idseq
              ,cmt.cs_csi_idseq
              ,cmt.fully_qualified_class_name
             , cmt.cp_idseq
             ,gm.GM_IDSEQ
    From UP_CLASS_METADATA_MVW_TEMP cmt
       ,UP_GEN_METADATA_MVW gm
     where cmt.cm_idseq= gm.s_cm_idseq(+)
     and cmt.cp_idseq = nvl(gm.cp_idseq,cmt.cp_idseq);

     
    
PROMPT UP_ATTRIBUTE_METADATA_MVW_TEMP

create materialized view UP_ATTRIBUTE_METADATA_MVW_TEMP
   AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  am_idseq
             ,dedes.name  name
             ,defin.definition description
             ,de.de_idseq
             ,pg.pg_idseq
             ,cscsi.cs_idseq
             ,dedes.desig_idseq
             ,defin.defin_idseq
             ,pg.cs_csi_idseq
             ,dec.dec_idseq
             ,de.vd_idseq
             ,fully_qualified_class_name||'.'||dedes.name fully_qualified_name
             ,cm_idseq
             ,dec.prop_idseq
             , cp.cp_idseq
   from data_element_concepts dec
        ,UP_CLASS_METADATA_MVW uc
       , ac_csi accsi
       , UP_PACKAGES_MVW pg
       , UP_CADSR_PROJECT_MVW cp
       , cs_csi cscsi
       , (Select * from designations
         where detl_name =  'UML Class:UML Attr') dedes
       , ac_att_cscsi_ext descsi
       , ac_att_cscsi_ext defcsi
       , data_elements de
       ,(select defin_idseq,definition,ac_idseq from definitions
         where defl_name = 'UML Class : UML Attr') defin
    where uc.oc_idseq = dec.oc_idseq
    and dec.dec_idseq = de.dec_idseq
    and de.de_idseq = defin.ac_idseq(+)
    and de.de_idseq = dedes.ac_idseq
    and accsi.ac_idseq = de.de_idseq
    and cscsi.cs_csi_idseq = accsi.cs_csi_idseq
    and descsi.att_idseq = dedes.desig_idseq
    and defcsi.att_idseq(+) = defin.defin_idseq
    and (pg.cs_csi_idseq = descsi.cs_Csi_idseq
    and pg.cs_Csi_idseq = nvl(defcsi.cs_csi_idseq,pg.cs_csi_idseq)
    and pg.cs_csi_idseq = accsi.cs_csi_idseq)
    and cscsi.cs_idseq = cp.cp_idseq
    and uc.cs_idseq = cscsi.cs_idseq;


PROMPT UP_ATTRIBUTE_TYPE_METADATA_MVW


create materialized view UP_ATTRIBUTE_TYPE_METADATA_MVW
   AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  at_idseq
             ,am.am_idseq
             ,am.de_idseq
             ,am.vd_idseq
             ,vd.dtl_name vd_datatype
             ,vd.long_name vd_long_name
   from UP_ATTRIBUTE_METADATA_MVW_TEMP am
       , value_domains vd       
    where am.vd_idseq = vd.vd_idseq;
    
    
PROMPT UP_ATTRIBUTE_METADATA_MVW

create materialized view UP_ATTRIBUTE_METADATA_MVW
   AS SELECT uat.am_idseq
             ,uat.name
             ,uat.description
             ,uat.de_idseq
             ,uat.pg_idseq
             ,uat.cs_idseq
             ,uat.desig_idseq
             ,uat.defin_idseq
             ,uat.cs_csi_idseq
             ,uat.dec_idseq
             ,uat.vd_idseq
             ,uat.fully_qualified_name
             ,uat.cm_idseq
             ,uat.prop_idseq
             ,uat.cp_idseq
             ,at.at_idseq
   from UP_ATTRIBUTE_METADATA_MVW_TEMP uat
        ,UP_ATTRIBUTE_TYPE_METADATA_MVW at
    where 
    	uat.am_idseq=at.am_idseq;

    

PROMPT UP_TYPE_ENUMERATION_MVW

create materialized view UP_TYPE_ENUMERATION_MVW
   AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  te_idseq
             ,at.at_idseq
             ,pv.Value
             ,pv.short_meaning
             ,vp.vp_idseq
             ,vp.pv_idseq
   from UP_ATTRIBUTE_TYPE_METADATA_MVW at
       , vd_pvs vp
       , permissible_Values pv    
    where at.vd_idseq = vp.vd_idseq
    and vp.pv_idseq = pv.pv_idseq;







PROMPT UP_SEMANTIC_METADATA_MVW

create materialized view UP_SEMANTIC_METADATA_MVW
   AS SELECT cast(admincomponent_Crud.cmr_guid as char(36))  sm_idseq
             ,con.con_idseq
             ,con.preferred_name concept_code
             ,con.long_name concept_name
             ,con.preferred_definition concept_Definition
             ,decode(cc.primary_flag_ind,'Yes',1,0) primary_Concept
             ,cc.display_order
             ,cl.component_level 
             ,cs.cs_idseq cs_idseq
             ,cp_idseq cp_idseq
             ,null cm_idseq
             ,null am_idseq
             ,null at_idseq
             ,null te_idseq
             ,null asm_idseq
             ,cast(null as char(36)) ocr_idseq
             ,cast(null as char(36)) oc_idseq
             ,null prop_idseq
             ,null vd_idseq
             ,null short_meaning
   From concepts_ext con
       , component_concepts_Ext cc
       , component_levels_Ext cl
       , classification_Schemes cs
       , UP_CADSR_PROJECT_MVW cp
    where cs.condr_idseq = cc.condr_idseq
    and cc.con_idseq = con.con_idseq
    and cc.cl_idseq = cl.cl_idseq(+)
    and cs.cs_idseq = cp.cs_idseq
  union
    SELECT cast(admincomponent_Crud.cmr_guid as char(36))  sm_idseq
             ,con.con_idseq
             ,con.preferred_name concept_code
             ,con.long_name concept_name
             ,con.preferred_definition concept_Definition
             ,decode(cc.primary_flag_ind,'Yes',1,0)
             ,cc.display_order
             ,cl.component_level
             ,null cs_idseq
             , cp_idseq
             ,cm_idseq
             ,null am_idseq
             ,null at_idseq
             ,null te_idseq
             ,cast(null as char(36)) asm_idseq
             ,cast(null as char(36)) ocr_idseq
             ,oc.oc_idseq oc_idseq
             ,null prop_idseq
             ,null vd_idseq
             ,null short_meaning
   From concepts_ext con
       , component_concepts_Ext cc
       , component_levels_Ext cl
       , UP_CLASS_METADATA_MVW cm
       , object_classes_Ext oc
    where cm.oc_idseq = oc.oc_idseq
    and oc.condr_idseq = cc.condr_idseq
    and cc.con_idseq = con.con_idseq
    and cc.cl_idseq = cl.cl_idseq(+) 
   union
    SELECT cast(admincomponent_Crud.cmr_guid as char(36))  sm_idseq
             ,con.con_idseq
             ,con.preferred_name concept_code
             ,con.long_name concept_name
             ,con.preferred_definition concept_Definition
             ,decode(cc.primary_flag_ind,'Yes',1,0)
             ,cc.display_order
             ,cl.component_level
             ,null cs_idseq
             , cp_idseq
             ,null cm_idseq
             ,am_idseq
             ,null at_idseq
             ,null te_idseq
             ,null asm_idseq
             ,cast(null as char(36)) ocr_idseq
             ,cast(null as char(36)) oc_idseq
             ,prop.prop_idseq prop_idseq
             ,null vd_idseq
             ,null short_meaning
   From concepts_ext con
       , component_concepts_Ext cc
       , component_levels_Ext cl
       , UP_Attribute_METADATA_MVW cm
       , properties_Ext prop
    where cm.prop_idseq = prop.prop_idseq
    and prop.condr_idseq = cc.condr_idseq
    and cc.con_idseq = con.con_idseq
    and cc.cl_idseq = cl.cl_idseq(+)
   union
   SELECT cast(admincomponent_Crud.cmr_guid as char(36))  sm_idseq
             ,con.con_idseq
             ,con.preferred_name concept_code
             ,con.long_name concept_name
             ,con.preferred_definition concept_Definition
             ,decode(cc.primary_flag_ind,'Yes',1,0)
             ,cc.display_order
             ,cl.component_level 
             ,null cs_idseq
             ,null cp_idseq
             ,null cm_idseq
             ,null am_idseq
             ,at_idseq
             ,null te_idseq
             ,cast(null as char(36)) asm_idseq
             ,cast(null as char(36)) ocr_idseq
             ,null oc_idseq
             ,null prop_idseq
             ,vd.vd_idseq vd_idseq
             ,null short_meaning
   From concepts_ext con
       , component_concepts_Ext cc
       , component_levels_Ext cl
       , UP_ATTRIBUTE_TYPE_METADATA_MVW cm
       , value_domains vd
    where cm.vd_idseq = vd.vd_idseq
    and vd.condr_idseq = cc.condr_idseq
    and cc.con_idseq = con.con_idseq
    and cc.cl_idseq = cl.cl_idseq(+)
   union
    SELECT cast(admincomponent_Crud.cmr_guid as char(36))  sm_idseq
             ,con.con_idseq
             ,con.preferred_name concept_code
             ,con.long_name concept_name
             ,con.preferred_definition concept_Definition
             ,decode(cc.primary_flag_ind,'Yes',1,0)
             ,cc.display_order
             ,cl.component_level 
             ,null cs_idseq
             ,null cp_idseq
             ,null cm_idseq
             ,null am_idseq
             ,null at_idseq
             ,te_idseq
             ,cast(null as char(36)) asm_idseq
             ,cast(null as char(36)) ocr_idseq
             ,null oc_idseq
             ,null prop_idseq
             ,null vd_idseq
             ,vm.short_meaning short_meaning
   From concepts_ext con
       , component_concepts_Ext cc
       , component_levels_Ext cl
       , UP_TYPE_ENUMERATION_MVW cm
       , value_meanings_lov vm
    where cm.short_meaning = vm.short_meaning
    and vm.condr_idseq = cc.condr_idseq
    and cc.con_idseq = con.con_idseq
    and cc.cl_idseq = cl.cl_idseq(+);

PROMPT UP_SEMANTIC_METADATA_MVW


       
