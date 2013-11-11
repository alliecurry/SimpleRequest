class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'login', action: 'login')
        "/auth"(controller: 'login', action: 'auth')
		"500"(view:'/error')
	}
}