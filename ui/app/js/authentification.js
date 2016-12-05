if(false) {
	console.warn('Connexion automatique !!');
	connected = true;
	defaut = 'Basic YXplOmF6ZQ==';
}
$http.defaults.headers.common['Authorization'] = defaut;

AuthentificationService.connect = function(login, password) {
	var auth = 'Basic' + btoa(login+':'+password);
	var config = {
		headers : {
			'Authorization' : auth
		}
	};
	$http.get(UrlService.getConnection(), config).then(function(){
		connected = true;
		$http.defaults.headers.common['Authorization'] = auth;
		return true;
	}, function(){
		connected = false;
		$http.defaults.headers.common['Authorization'] = 'Basic';
		return false;
	});
};

AuthentificationService.isConnected = function() {
	return connected;
};

return AuthentificationService;