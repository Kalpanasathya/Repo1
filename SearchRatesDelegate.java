/*******************************************************************************
 * Copyright (c) 2016, 2018 , Oracle and/or its affiliates. All rights reserved.
 ******************************************************************************/
package com.oracle.pas.delegate;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.adminserver.dcl.TransientFieldDataDcl;
import com.adminserver.dcl.interfaces.IFieldDataDcl;
import com.adminserver.model.AsUserModel;
import com.adminserver.pas.model.EditRateGroupModel;
import com.adminserver.pas.model.RateContentModel;
import com.adminserver.pas.model.ResponseModel;
import com.adminserver.pas.model.SearchRatesModel;
import com.adminserver.utl.SpringBeanUtl;
import com.oracle.pas.annotations.AccessLoggingAnnotation;
import com.oracle.pas.page.EditRateGroupPage;
import com.oracle.pas.page.SearchRatesPage;
import com.oracle.pas.service.delegate.BaseServiceDelegate;

/**
 * @Description SearchRates Delegate for delegating and managing SearchRatesPage.
 */

@Component
@Scope("singleton")
public class SearchRatesDelegate extends BaseServiceDelegate {

   private static final String[] MODEL_ENTITY_TYPE_CODE_CHANGE    = { "pageErrorDcl", "entityTypeCode", "relationshipEntityFieldValue", "titleRelationshipEntityFieldValue" };
   private static final String[] PROCESS_FIND_ACTION              = { "rateGroupDcl", "selectedRateGroupRowDcl", "rateCriteriaFieldSetContainerList", "pageErrorDcl", "hideRateGroupDescriptionSelectItemList", "rateGroupGuid", "selectedRateDescription", "rateGroupTableDcl", "rateGroupDescriptionSelectItemDclList", "rateResultTableDcl", "linkRateGroupButtonAuthorized", "addRatesSearchScreen" };
   private static final String[] PROCESS_FETCH_RESULTS            = { "rateGroupTableDcl" };
   private static final String[] PROCESS_RATE_GROUP_CHANGE        = { "rateGroupGuid", "rateResultTableDcl", "rateCriteriaFieldSetContainerList", "rateGroupDcl", "selectedRateGroupRowDcl", "selectedRateDescription", "pageErrorDcl", "rateGroupTableDcl" };
   private static final String[] PROCESS_RATE_GROUP_ROW_SELECTION = { "selectedRateGroupRowDcl", "rateResultTableDcl", "rateGroupDcl", "rateCriteriaFieldSetContainerList", "pageErrorDcl", "dynFldCont" };
   private static final String[] PROCESS_FILTER_ACTION            = { "rateResultTableDcl", "pageErrorDcl", "dynFldCont", "rateCriteriaScreenFieldDataDclMap", "rateCriteriaFieldSetContainerList", "selectedRateGroupRowDcl", "rateGroupDcl", "rateSearchCriteriaDcl" };
   private static final String[] PROCESS_FETCH_FILTER_ACTION      = { "rateResultTableDcl" };
   private static final String[] PROCESS_EXPIRATION_DATE_CHANGE   = { "rateGroupDcl" };
   private static final String[] PROCESS_EDIT_RATE_SAVE           = { "pageErrorDcl", "rateGroupDcl", "expirationDateDisabled","dynFldCont" };
   private static final String[] LOAD_RATE_MODEL                  = { "pageErrorDcl", "titleRateSearchResults", "titleSave", "rateResultTableDcl", "rateGroupDcl", "rateDcl", "titleAction", "titleRate", "titleIntegerCriteria", "titleAction", "titleRateDeleteConfirmation", "rateGroupGuid", "rateDescription", "titleRateGroup", "updatedRateDclMap", "defaultCurrencyCode" };
   private static final String[] RATE_SUB_MODEL                   = { "pageErrorDcl", "rateResultTableDcl", "rateGroupDcl", "rateDcl", "titleRate", "rateGroupGuid", "rateDescription", "updatedRateDclMap", "selectedRateRowDcl", "currentChangeDcl", "eventInsertDataDclListMap", "fixedFieldMap" };

   /**
    * Initializes the SearchRates.
    * 
    * @param request
    * @return response
    */
   @AccessLoggingAnnotation
   public Response loadSearchRates( HttpServletRequest request ) {

      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.startProcessing();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredSearchRateJson = filterModelProperties( searchRatesPage, new String[]{} );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredSearchRateJson );
      String filteredRateContentJson = filterModelProperties( searchRatesPage.getRateContentPage(), new String[]{} );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );

      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process entity type change
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   public Response processEntityTypeCodeChange( HttpServletRequest request, String searchRatesModelJson ) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( searchRatesModelJson, SearchRatesModel.class, null );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processEntityTypeCodeChange();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredJson = filterAllModelPropertiesExcept( searchRatesPage, MODEL_ENTITY_TYPE_CODE_CHANGE );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process Find action
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   public Response processFindAction( HttpServletRequest request, String searchRatesModelJson ) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( searchRatesModelJson, SearchRatesModel.class, null );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processFindAction();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_FIND_ACTION );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredJson );
      return buildOkResponse( responseModel );

   }

   /**
    * Load the data table to show the rate group details.
    * 
    * @param request
    * @param limit
    * @param offset
    * @param totalResults
    * @param orderBy
    * @param requestModel
    * @return
    */
   public Response processTableDcl( @Context HttpServletRequest request, @QueryParam("limit" ) Integer limit, @QueryParam("offset") Integer offset, @QueryParam("totalResults") String totalResults, @QueryParam("orderBy") String orderBy, String requestModel) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( requestModel, SearchRatesModel.class, null );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processTableDcl( limit, offset, totalResults, orderBy );

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_FETCH_RESULTS );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredJson );
      return buildOkResponse( responseModel );

   }

   /**
    * Load the data table to show the rate details.
    * 
    * @param request
    * @param limit
    * @param offset
    * @param totalResults
    * @param orderBy
    * @param requestModel
    * @return
    */
   public Response processRateSearchTableDcl( @Context HttpServletRequest request, @QueryParam("limit" ) Integer limit, @QueryParam("offset") Integer offset, @QueryParam("totalResults") String totalResults, @QueryParam("orderBy") String orderBy, String requestModel) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( requestModel, SearchRatesModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processRateSearchTableDcl( limit, offset, totalResults, orderBy );

      // build response
      ResponseModel responseModel = new ResponseModel();

      String filteredSearchRateJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_FETCH_FILTER_ACTION );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredSearchRateJson );

      String filteredRateContentJson = filterAllModelPropertiesExcept( searchRatesPage.getRateContentPage(), LOAD_RATE_MODEL );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );

      return buildOkResponse( responseModel );

   }

   /**
    * Handles the request for process Rate Group change event
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   public Response processRateGroupChange( HttpServletRequest request, String searchRatesModelJson ) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( searchRatesModelJson, SearchRatesModel.class, null );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processRateGroupChange();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_RATE_GROUP_CHANGE );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Initializes the EditRateGroup tab.
    * 
    * @param request
    * @param rateGroupGuid
    * @return response
    */
   public Response loadEditRateGroup( HttpServletRequest request, String rateGroupGuid, boolean multipleRateGroups ) {

      AsUserModel userModel = loadUserModel( request );
      EditRateGroupPage editRateGroupPage = SpringBeanUtl.getBean( EditRateGroupPage.class );
      editRateGroupPage.setUserModel( userModel );
      editRateGroupPage.setRateGroupGuid( rateGroupGuid );
      editRateGroupPage.setMultipleRateGroups( multipleRateGroups );
      editRateGroupPage.startProcessing();
      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredJson = filterModelProperties( editRateGroupPage, new String[]{} );
      responseModel.addModel( EditRateGroupPage.class.getSimpleName(), filteredJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process ExpirationDateChange event
    * 
    * @param request
    * @param clientWithholdingModelJson
    * @return response
    */
   public Response processExpirationDateChange( HttpServletRequest request, String editRateGroupModelJson ) {

      EditRateGroupModel editRateGroupModel = ( EditRateGroupModel )buildObjectFromJsonString( editRateGroupModelJson, EditRateGroupModel.class, null );
      AsUserModel userModel = loadUserModel( request );
      EditRateGroupPage editRateGroupPage = SpringBeanUtl.getBean( EditRateGroupPage.class );
      editRateGroupPage.setUserModel( userModel );
      editRateGroupPage.setModel( editRateGroupModel );
      editRateGroupPage.processExpirationDateChange();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredJson = filterAllModelPropertiesExcept( editRateGroupPage, PROCESS_EXPIRATION_DATE_CHANGE );
      responseModel.addModel( EditRateGroupPage.class.getSimpleName(), filteredJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Saves a Rate Group.
    * 
    * @param request
    * @param rateGroupGuid
    * @param editRateGroupModelJson
    * @param selectedRateDescription
    * @return response
    */
   @AccessLoggingAnnotation
   public Response processEditRateGroupSave( HttpServletRequest request, String editRateGroupModelJson, String rateGroupGuid, String selectedRateDescription ) {

      AsUserModel userModel = loadUserModel( request );
      EditRateGroupModel editRateGroupModel = ( EditRateGroupModel )buildObjectFromJsonString( editRateGroupModelJson, EditRateGroupModel.class, loadAbstractTypeResolverForEntity() );
      EditRateGroupPage editRateGroupPage = SpringBeanUtl.getBean( EditRateGroupPage.class );
      editRateGroupPage.setUserModel( userModel );
      editRateGroupPage.setModel( editRateGroupModel );
      editRateGroupPage.processSave();

      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      SearchRatesModel searchRatesModel = new SearchRatesModel();
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.getModel().setSelectedRateDescription( selectedRateDescription );
      searchRatesPage.updateRateGroupTable();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String searchRatesFilteredJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_FIND_ACTION );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), searchRatesFilteredJson );

      String editRateFilteredJson = filterAllModelPropertiesExcept( editRateGroupPage, PROCESS_EDIT_RATE_SAVE );
      responseModel.addModel( EditRateGroupPage.class.getSimpleName(), editRateFilteredJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process Rate Group selection event
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   public Response processRateGroupRowSelection( HttpServletRequest request, String searchRatesModelJson ) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( searchRatesModelJson, SearchRatesModel.class, null );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processRateGroupRowSelection();

      // build response
      ResponseModel responseModel = new ResponseModel();

      String filteredSearchRateJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_RATE_GROUP_ROW_SELECTION );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredSearchRateJson );

      String filteredRateContentJson = filterModelProperties( searchRatesPage.getRateContentPage(), new String[]{} );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );

      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process Filter action
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   public Response processFilterAction( HttpServletRequest request, String searchRatesModelJson ) {

      SearchRatesModel searchRatesModel = ( SearchRatesModel )buildObjectFromJsonString( searchRatesModelJson, SearchRatesModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.setModel( searchRatesModel );
      searchRatesPage.processFilterAction();

      // build response
      ResponseModel responseModel = new ResponseModel();

      String filteredSearchRateJson = filterAllModelPropertiesExcept( searchRatesPage, PROCESS_FILTER_ACTION );
      responseModel.addModel( SearchRatesModel.class.getSimpleName(), filteredSearchRateJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process Rate selection event
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   public Response processRateRowSelection( HttpServletRequest request, String searchRatesModelJson ) {

      RateContentModel rateContentModel = ( RateContentModel )buildObjectFromJsonString( searchRatesModelJson, RateContentModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.getRateContentPage().setUserModel( userModel );
      searchRatesPage.getRateContentPage().setModel( rateContentModel );
      searchRatesPage.processRowSelection();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredRateContentJson = filterAllModelPropertiesExcept( searchRatesPage.getRateContentPage(), RATE_SUB_MODEL );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process value change event
    * 
    * @param request
    * @param fieldName
    * @param searchRatesModelJson
    * @return response
    */
   public Response processValueChange( HttpServletRequest request, String fieldName, String searchRatesModelJson ) {

      RateContentModel rateContentModel = ( RateContentModel )buildObjectFromJsonString( searchRatesModelJson, RateContentModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.getRateContentPage().setUserModel( userModel );
      searchRatesPage.getRateContentPage().setModel( rateContentModel );
      searchRatesPage.processValueChange( fieldName );

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredRateContentJson = filterAllModelPropertiesExcept( searchRatesPage.getRateContentPage(), RATE_SUB_MODEL );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );

      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process reverse value change event
    * 
    * @param request
    * @param fieldName
    * @param searchRatesModelJson
    * @return response
    */
   public Response processReverseChange( HttpServletRequest request, String fieldName, String searchRatesModelJson ) {

      RateContentModel rateContentModel = ( RateContentModel )buildObjectFromJsonString( searchRatesModelJson, RateContentModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.getRateContentPage().setUserModel( userModel );
      searchRatesPage.getRateContentPage().setModel( rateContentModel );
      searchRatesPage.reverseChange( fieldName );

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredRateContentJson = filterAllModelPropertiesExcept( searchRatesPage.getRateContentPage(), RATE_SUB_MODEL );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );
      return buildOkResponse( responseModel );
   }

   /**
    * Handles the request for process save action
    * 
    * @param request
    * @param searchRatesModelJson
    * @return response
    */
   @AccessLoggingAnnotation
   public Response processSave( HttpServletRequest request, String searchRatesModelJson ) {

      RateContentModel rateContentModel = ( RateContentModel )buildObjectFromJsonString( searchRatesModelJson, RateContentModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.getRateContentPage().setUserModel( userModel );
      searchRatesPage.getRateContentPage().setModel( rateContentModel );
      searchRatesPage.processSave();
      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredRateContentJson = filterAllModelPropertiesExcept( searchRatesPage.getRateContentPage(), RATE_SUB_MODEL );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );

      return buildOkResponse( responseModel );
   }

   @AccessLoggingAnnotation
   public Response processDelete( HttpServletRequest request, String searchRatesModelJson ) {

      RateContentModel rateContentModel = ( RateContentModel )buildObjectFromJsonString( searchRatesModelJson, RateContentModel.class, loadAbstractTypeResolverForEntity() );
      AsUserModel userModel = loadUserModel( request );
      SearchRatesPage searchRatesPage = SpringBeanUtl.getBean( SearchRatesPage.class );
      searchRatesPage.setUserModel( userModel );
      searchRatesPage.getRateContentPage().setUserModel( userModel );
      searchRatesPage.getRateContentPage().setModel( rateContentModel );
      searchRatesPage.processDelete();

      // build response
      ResponseModel responseModel = new ResponseModel();
      String filteredRateContentJson = filterAllModelPropertiesExcept( searchRatesPage.getRateContentPage(), RATE_SUB_MODEL );
      responseModel.addModel( RateContentModel.class.getSimpleName(), filteredRateContentJson );

      return buildOkResponse( responseModel );

   }

   @SuppressWarnings({ "unchecked", "rawtypes" })
   private Map <Class, Class> loadAbstractTypeResolverForEntity() {

      final Map <Class, Class> abstractTypeResolverMap = new HashMap <Class, Class>();
      abstractTypeResolverMap.put( IFieldDataDcl.class, TransientFieldDataDcl.class );
      return abstractTypeResolverMap;
   }
}
