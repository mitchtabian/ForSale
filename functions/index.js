const functions = require('firebase-functions');

const request = require('request-promise');

exports.indexPostsToElastic = functions.database.ref('/posts/{post_id}')
	.onWrite(event => {
		let postData = event.data.val();
		let post_id = event.params.post_id;
		
		console.log('Indexing post:', postData);
		
		let elasticSearchConfig = functions.config().elasticsearch;
		let elasticSearchUrl = elasticSearchConfig.url + 'posts/post/' + post_id;
		let elasticSearchMethod = postData ? 'POST' : 'DELETE';
		
		let elasticSearchRequest = {
			method: elasticSearchMethod,
			url: elasticSearchUrl,
			auth:{
				username: elasticSearchConfig.username,
				password: elasticSearchConfig.password,
			},
			body: postData,
			json: true
		  };
		  
		  return request(elasticSearchRequest).then(response => {
			 console.log("ElasticSearch response", response);
		  });
	});

