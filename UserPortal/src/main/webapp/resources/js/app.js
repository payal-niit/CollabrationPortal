'use strict';
var app=angular.module('app',['ngRoute','ngResource','ngCookies','textAngular','ngFileUpload']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'resources/js/home.jsp',
         
         
     })

    /*.when('/login', {
         controller: 'UserController',
         templateUrl: 'resources/login.jsp',
         
     })
     */

     .when('/register', {
        controller: 'UserController',
         templateUrl: 'resources/js/user.jsp',
        
     })
     /*BLOG RELATED MAPPING */

     .when('/blog', {
        controller: 'BlogController',
        controller: 'ViewBlogController',
        controller: 'BlogCommentController',
         templateUrl: 'resources/js/blog.jsp',
        
     })
     .when('/bloglist', {
        controller: 'BlogController',        
        controller: 'BlogCommentController',
         templateUrl: 'resources/js/bloglist.jsp',
        
     })
     .when('/loginpage', {
        
         templateUrl: 'resources/js/login.jsp',
        
     })
     .when('/viewblog', {
    	 controller: 'ViewBlogController',
         templateUrl: 'resources/js/viewblog.jsp',
        
     })
     .when('/forum', {
    	 controller: 'ForumController',
         templateUrl: 'resources/js/forum.jsp',
        
     })
     /*s*/
});



/*app.run(function ($rootScope, $location, $cookieStore, $http){
	
	$rootScope.$on('$locationChangeStart', function(event, next, current){
		console.log("$locationChangeStart")
		// Redirect to login page if not logged in and trying to access a restricted page page
		var restrictedPage= $.inArray($location.path(),['//','home','/view_blog','/register'])===-1;
		console.log("restrictedPage:"+restrictedPage);
		var loggedIn=$rootScope.currentUser.id;
		console.log("loggedIn:"+loggedIn)
		if(restrictedPage & !loggedIn){
			console.log("Navigation to login page:")
			$location.path('/');
			
		}
	});
	



// KEEP USER LOGGED IN AFTER PAGE REFRESH

$rootScope.currentUser=$cookieStore.get('CurrentUser') || {};
if($rootScope.currentUser){
	
	$http.defaults.headers.common['Authorization']='Basic'+ $rootScope.currentUser;
}												//'Basic ' + $rootScope.globals.currentUser.authdata;
});
*/