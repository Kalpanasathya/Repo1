/*******************************************************************************
 * Copyright (c), 2016 , Oracle and/or its affiliates. All rights reserved.
 ******************************************************************************/
package com.oracle.pas.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracle.pas.delegate.SearchRatesDelegate;

/**
 * @Description SearchRates Service
 */
@Service
@Path("/searchRates")
public class SearchRatesService {

   private static final String LOAD_SEARCH_RATES                = "/loadSearchRates";
   private static final String PROCESS_ENTITY_TYPE_CODE_CHANGE        = "/processEntityTypeCodeChange";
   private static final String PROCESS_FIND_ACTION                    = "/processFindAction";
   private static final String PROCESS_FETCH_RESULTS                  = "/fetchResultSet";
   private static final String PROCESS_FETCH_RATE_RESULTS             = "/fetchRateSearchResultSet";
   private static final String PROCESS_RATE_GROUP_CHANGE              = "/processRateGroupChange";
   private static final String PROCESS_RATE_GROUP_ROW_SELECTION       = "/processRateGroupRowSelection";
   private static final String PROCESS_FILTER_ACTION                  = "/processFilterAction";
   private static final String PROCESS_VALUE_CHANGE                   = "/processValueChange";
   private static final String PROCESS_REVERSE_CHANGE                 = "/processReverseChange";
   private static final String PROCESS_RATE_ROW_SELECTION             = "/processRateRowSelection";
   private static final String PROCESS_SAVE                           = "/processSave";
   private static final String PROCESS_DELETE                         = "/processDeleted";
   private static final String SERVICE_LOAD_EDIT_RATE_GROUP     = "/loadEditRateGroup";
   private static final String SERVICE_PROCESS_EXPIRATION_DATE_CHANGE = "/processExpirationDateChange";
   private static final String SERVICE_PROCESS_EDIT_RATE_SAVE         = "/processEditRateSave";

   @Autowired
   private SearchRatesDelegate searchRatesDelegate;

   /**
    * Initialize the search rates model
    * 
    * @param request
    * @return
    */
   @GET
   @Path(LOAD_SEARCH_RATES)
   @Produces(MediaType.APPLICATION_JSON)
   public Response initializeSearchRates( @Context HttpServletRequest request ) {

      Response response = searchRatesDelegate.loadSearchRates( request );
      return response;
   }

   /**
    * Process a Entity Type Change
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_ENTITY_TYPE_CODE_CHANGE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processEntityTypeCodeChange( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processEntityTypeCodeChange( request, searchRatesModelJson );
      return response;
   }

   /**
    * Process a find action
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_FIND_ACTION)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processFindAction( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processFindAction( request, searchRatesModelJson );
      return response;
   }

   /**
    * Fetch the rate group details for the given request criteria.
    * 
    * @param request
    * @param limit
    * @param offset
    * @param totalResults
    * @param orderBy
    * @param requestModel
    * @return Response
    */
   @POST
   @Path(PROCESS_FETCH_RESULTS)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processTableDcl( @Context HttpServletRequest request, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("totalResults") String totalResults, @QueryParam("orderBy") String orderBy, String requestModel ) {

      return searchRatesDelegate.processTableDcl( request, limit, offset, totalResults, orderBy, requestModel );
   }

   /**
    * Process a RateGrouop Change
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_RATE_GROUP_CHANGE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processRateGroupChange( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processRateGroupChange( request, searchRatesModelJson );
      return response;
   }

   /**
    * Process RateGroup selection
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_RATE_GROUP_ROW_SELECTION)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processRateGroupRowSelection( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processRateGroupRowSelection( request, searchRatesModelJson );
      return response;
   }

   /**
    * Process a Filter action
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_FILTER_ACTION)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processFilterAction( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processFilterAction( request, searchRatesModelJson );
      return response;
   }

   /**
    * Fetch the rate details for the given request criteria.
    * 
    * @param request
    * @param limit
    * @param offset
    * @param totalResults
    * @param orderBy
    * @param requestModel
    * @return Response
    */
   @POST
   @Path(PROCESS_FETCH_RATE_RESULTS)
   @Produces(MediaType.APPLICATION_JSON)
   public Response processRateSearchTableDcl( @Context HttpServletRequest request, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("totalResults") String totalResults, @QueryParam("orderBy") String orderBy, String requestModel ) {

      return searchRatesDelegate.processRateSearchTableDcl( request, limit, offset, totalResults, orderBy, requestModel );
   }

   /**
    * Process a Rate Row Selection
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_RATE_ROW_SELECTION)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processRateRowSelection( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processRateRowSelection( request, searchRatesModelJson );
      return response;
   }

   /**
    * Process a value change event
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_VALUE_CHANGE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processValueChange( @Context HttpServletRequest request, @QueryParam("fieldName" ) String fieldName, String searchRatesModelJson) {

      Response response = searchRatesDelegate.processValueChange( request, fieldName, searchRatesModelJson );
      return response;
   }

   /**
    * Process a Reverse change event
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_REVERSE_CHANGE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processReverseChange( @Context HttpServletRequest request, @QueryParam("fieldName" ) String fieldName, String searchRatesModelJson) {

      Response response = searchRatesDelegate.processReverseChange( request, fieldName, searchRatesModelJson );
      return response;
   }

   /**
    * Process Rate save action
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_SAVE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processSave( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processSave( request, searchRatesModelJson );
      return response;
   }

   /**
    * Process delete row action
    * 
    * @param request
    * @return
    */
   @POST
   @Path(PROCESS_DELETE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processDelete( @Context HttpServletRequest request, String searchRatesModelJson ) {

      Response response = searchRatesDelegate.processDelete( request, searchRatesModelJson );
      return response;
   }

   /**
    * This service is to get the screen to edit a rate group.
    * 
    * @param request
    * @param rateGroupGuid
    * @param multipleRateGroups
    * @return
    */
   @GET
   @Path(SERVICE_LOAD_EDIT_RATE_GROUP)
   @Produces(MediaType.APPLICATION_JSON)
   public Response initializeSearchRates( @Context HttpServletRequest request, @QueryParam("rateGroupGuid" ) String rateGroupGuid, @QueryParam("multipleRateGroups") Boolean multipleRateGroups) {

      Response response = searchRatesDelegate.loadEditRateGroup( request, rateGroupGuid, multipleRateGroups );
      return response;
   }

   @POST
   @Path(SERVICE_PROCESS_EXPIRATION_DATE_CHANGE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processExpirationDateChange( @Context HttpServletRequest request, String editRateGroupModelJson ) {

      Response response = searchRatesDelegate.processExpirationDateChange( request, editRateGroupModelJson );
      return response;
   }

   /**
    * This service is to save the edited rate group.
    * 
    * @param request
    * @param editRateGroupModelJson
    * @return
    */
   @POST
   @Path(SERVICE_PROCESS_EDIT_RATE_SAVE)
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public Response processSave( @Context HttpServletRequest request, String editRateGroupModelJson, @QueryParam("rateGroupGuid" ) String rateGroupGuid, @QueryParam("selectedRateDescription") String selectedRateDescription) {

      Response response = searchRatesDelegate.processEditRateGroupSave( request, editRateGroupModelJson, rateGroupGuid, selectedRateDescription );
      return response;
   }
}
