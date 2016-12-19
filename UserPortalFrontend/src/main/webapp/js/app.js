'use strict';
var app=angular.module('app',['ngRoute','ngResource','ngCookies','textAngular','ngFileUpload']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'home.html',
         
         
     })

    /*.when('/login', {
         controller: 'UserController',
         templateUrl: 'resources/login.jsp',
         
     })
     */

     .when('/register', {
        controller: 'UserController',
         templateUrl: 'views/user.html',
        
     })
     /*BLOG RELATED MAPPING */

     .when('/blog', {
        controller: 'BlogController',
        controller: 'ViewBlogController',
        controller: 'BlogCommentController',
         templateUrl: 'views/blog.html',
        
     })
     .when('/bloglist', {
        controller: 'js/BlogController',        
        controller: 'BlogCommentController',
        controller: 'ViewBlogController',
         templateUrl: 'views/bloglist.html',
        
     })
     .when('/loginpage', {
        
         templateUrl: 'login.html',
        
     })
     .when('/viewblog', {
    	 controller: 'ViewBlogController',
         templateUrl: 'viewblog.html',
        
     })
     .when('/forum', {
    	 controller: 'ForumController',
         templateUrl: 'views/forum.html',
        
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