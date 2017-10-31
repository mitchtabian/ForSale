<h2>Creating an ElasticSearch Firebase Cloud Function</h2>
<h5>This function will trigger when a post is created or deleted and update the data on your ElasticSearch server.</h5>
<ol>

<li>Open the file named "index.js" in the "functions" folder in your Android project's directory. Paste this into index.js:</li>

<pre><code>
const functions = require('firebase-functions');

const request = require('request-promise')

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

</code></pre>

<li>save the file and deploy it to firebase:
<pre><code>firebase deploy --only functions</code></pre>
</li>
</ol>

