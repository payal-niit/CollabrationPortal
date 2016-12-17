app.factory('Blog', ['$resource', function ($resource) {
	return $resource('http://localhost:8080/UserPortal/blog/:personId', {personId: '@blogId'},
	{
		updateBlog: {method: 'PUT'}
	}
    );
}]);
app.controller('ViewBlogController', ['$http','$scope','$cookieStore','Blog','$location','$window','$rootScope',function($http,$scope,$cookieStore,Blog ,$location,$window, $rootScope) {
	var ob=this;
	ob.blogs=[];
    ob.blog = new Blog(); 
	
	ob.viewblog = function(id){
	    console.log('Inside view');
            ob.blog = Blog.get({ personId: id}, function() {
	       ob.flag = 'view';
	       
	       $rootScope.currentBlog = {
					blogId : ob.blog.blogId,
					blogName : ob.blog.blogName,
					blogDescription : ob.blog.blogDescription,
					userId : ob.blog.userId
				};
				$http.defaults.headers.common['Authorization'] = 'Basic'
					+ $rootScope.currentBlog;
				$cookieStore
					.put(
							'currentBlog',
							$rootScope.currentBlog);
				
	       $location.path('/viewblog');
	    });
    };
    
    var YourCtrl = function($scope, localStorageService) {
    	  // To add to local storage
    	  localStorageService.set('currentBlog','currentBlog');
    	  // Read that value back
    	  var value = localStorageService.get('currentBlog');
    	  // To remove a local storage
    	  localStorageService.remove('currentBlog');
    	  // Removes all local storage
    	  localStorageService.clearAll();
    	  // You can also play with cookies the same way
    	  localStorageService.cookie.set('currentBlog','I am a cookie value now');
    	}
}]);