/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodvite.restaurantdata;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeolocationApi;
import com.google.maps.GeolocationApiRequest;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PendingResult;
import com.google.maps.PendingResult.Callback;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.GeolocationResult;
import com.google.maps.model.PlaceType;


/**
 *
 * @author ravi
 */
public class RestaurantDriver {
    
    public static void main(String args[]){
        
        /*the below code will output place details about thee place id on the console*/
//        try {
//            // variable declaration and initialization
//            double lat = 1.3521;
//            double lng = 103.8198;
//            String ll = Double.toString(lat) + Double.toString(lng);
//            // google place api key
//            String googlePlaceApiKey = "AIzaSyCRX2fbu1FvF-_3XC3pn3Tt5PWRRdPACP0";
//            
//            GeoApiContext context = new GeoApiContext().setApiKey(googlePlaceApiKey);
//            PlaceDetailsRequest  placedetails = PlacesApi.placeDetails(context, "ChIJL4rX8d8Z2jERuW0CLTq4ybI" /*place id*/);
//            System.out.println(placedetails);
//            PlaceDetails pd = placedetails.await();
//            System.out.print("Name: " + pd.name + ", Address: " + pd.formattedAddress + ", Opening now: " + pd.openingHours.openNow);
//            
//        } catch (ApiException | InterruptedException | IOException ex) {
//            Logger.getLogger(RestaurantDriver.class.getName()).log(Level.SEVERE, null, ex);
//        }
        /**********************************************************************************************/
        
        /**place api search based on text **/
        
        // google place api key
//        String googlePlaceApiKey = "AIzaSyCRX2fbu1FvF-_3XC3pn3Tt5PWRRdPACP0";
//
//        GeoApiContext context = new GeoApiContext().setApiKey(googlePlaceApiKey);
//        
//        TextSearchRequest req1 = PlacesApi.textSearchQuery(context, "restaurant");
//        TextSearchRequest req = req1.radius(5000);
//        try {
//            PlacesSearchResponse response = req.await();
//            PlacesSearchResult[] place_result = response.results;
//            System.out.println(place_result.length);
//            for(PlacesSearchResult r : place_result){
//                // System.out.println(r.toString());
//                System.out.println("\nName: " + r.name + ", Rating: " + r.rating + "temp: " + r.icon + ", types: ");
//                for(String t : r.types){
//                    System.out.print(t + ", ");
//                }
//            }
//            
//        } catch (ApiException | InterruptedException | IOException ex) {
//            Logger.getLogger(RestaurantDriver.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        /********************************************************************************************************/
        
        // nearbysearch request ************************************************************************/
        
        double lat = 1.3521;
        double lng = 103.8198;
        LatLng loc = new LatLng(lat, lng);
        
        // google place api key
        String googlePlaceApiKey = "AIzaSyCRX2fbu1FvF-_3XC3pn3Tt5PWRRdPACP0";

        GeoApiContext context = new GeoApiContext().setApiKey(googlePlaceApiKey);
        NearbySearchRequest nearbyplace = new NearbySearchRequest(context).location(loc);
        // must set radius for this method to work
        NearbySearchRequest nearplace = nearbyplace.radius(5000);
        NearbySearchRequest nearbyRest = nearplace.type(PlaceType.RESTAURANT);
        
        
            // to store next page token
        String nextpage = null;
        
        // asynchronous calling style.
        
       
//        nearbyRest.setCallback(new PendingResult.Callback<PlacesSearchResponse>() {
//            @Override
//            public void onResult(PlacesSearchResponse t) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                System.out.println(t);
//                PlacesSearchResult[] sr = t.results;
//                for(PlacesSearchResult r: sr){
//                    System.out.println(r.name);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable thrwbl) {
////                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                Logger.getLogger(RestaurantDriver.class.getName()).log(Level.SEVERE, null, thrwbl);
//
//            }
//            
//        });
        
        try {
            
            PlacesSearchResponse response = nearbyRest.await();
            nextpage = response.nextPageToken;
            // print the next page token
            System.out.println(nextpage);
            PlacesSearchResult[] results = response.results;
            System.out.println("# response: " + results.length);
            for (PlacesSearchResult r : results) {
                System.out.println("Name: " + r.name + ", vicinity: " + r.vicinity + ", PlaceId: " + r.placeId);
            }
           
            // accessing additional result by using nextPageToken
            /*NearbySearchRequest nextreq = nearbyRest.pageToken(nextpage);
            PlacesSearchResponse nextres = nextreq.awaitIgnoreError();
            PlacesSearchResult[] nextresult = nextres.results;
            System.out.println("# response: " + nextresult.length);
            for(PlacesSearchResult r : nextresult){
                System.out.println("Name: " + r.name + ", vicinity: " + r.vicinity + ", PlaceId: " + r.placeId);
            }*/
            
            
        } catch (ApiException | InterruptedException | IOException ex) {
            Logger.getLogger(RestaurantDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        
//        try {
//            
//            while (nextpage != null) {
//                NearbySearchRequest nearbyRest1 = new NearbySearchRequest(context).pageToken(nextpage);
//                
//                PlacesSearchResponse response = nearbyRest1.await();
//                nextpage = response.nextPageToken;
//                // print the next page token
//                System.out.println(nextpage);
//                PlacesSearchResult[] results = response.results;
//                System.out.println("# response: " + results.length);
//                for (PlacesSearchResult r : results) {
//                    System.out.println("Name: " + r.name + ", vicinity: " + r.vicinity + ", PlaceId: " + r.placeId);
//                }
//            }
//            
//            
//        }catch (ApiException | InterruptedException | IOException ex) {
//            Logger.getLogger(RestaurantDriver.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        
    }
    
}
