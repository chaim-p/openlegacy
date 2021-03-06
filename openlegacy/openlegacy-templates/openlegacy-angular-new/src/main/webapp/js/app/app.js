( function() {

	'use strict';

	/* App Module */
	
	var olApp = angular.module( 'olApp', [ 'ngCookies','controllers', 'services'] );

	olApp.config( [ '$routeProvider', function( $routeProvider) {
		
		$routeProvider = $routeProvider.when( '/login', {templateUrl: 'views/login.html', controller: 'loginController'} );
		$routeProvider = $routeProvider.when( '/logoff', {templateUrl: 'views/logoff.html', controller: 'logoffController'} );
		$routeProvider = $routeProvider.when( '/menu', {templateUrl: 'views/menu.html'} );

		/* Register controller place-holder start
		<#if keys?size &gt; 0>
$routeProvider = $routeProvider.when( '/${entityName}/:<#list keys as key>${key.name?replace(".", "_")}<#if key_has_next>+</#if></#list>', {templateUrl: 'views/${entityName}.html', controller: '${entityName}Controller'} );
		</#if>
		$routeProvider = $routeProvider.when( '/${entityName}', {templateUrl: 'views/${entityName}.html', controller: '${entityName}Controller'} );
		Register controller place-holder end */
		
		$routeProvider = $routeProvider.otherwise( {redirectTo: '/login'} );
		
	} ] );
} )();

function appOnLoad($cookies,$rootScope,$location,$olHttp){
	// fix relative URL's
	if (olConfig.baseUrl.indexOf("http" < 0)){
		olConfig.baseUrl = $location.protocol() + "://" + $location.host() + ":" + $location.port() + olConfig.baseUrl;
	}
	
	if ($cookies.loggedInUser != null){
		$rootScope.loggedInUser = $cookies.loggedInUser;
		$location.path("/menu");
	}
//	$olHttp.get("menu",function(data){
	//	$rootScope.menus = data.simpleMenuItem.menuItems;
	//});
}


function search(baseUrl){
	var url = baseUrl;
	$("#keys :input").each(function(i){
		url = url + $(this).val() + "+";
	});
	url = url.substring(0,url.length-1;
	location.href = url;
}