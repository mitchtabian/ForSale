<h2>Creating an ElasticSearch index with Postman</h2>
<ol>
<li>Download and install postman if you don't have it: <a href='https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?utm_source=chrome-ntp-icon'>get it here </a></li>

<li>Add the url end point for you're ElasticSearch server. This is mine: http://35.188.218.81//elasticsearch/posts but yours will be different.

<p><b>DO NOT COPY MINE! IT WON'T WORK</b></p></li>

<li>
Add the body to the <b>PUT</b> request:
<pre><code>{
	"mappings":{
	"post":{
		"properties":{
				"city":{
					"type": "text"
				},
				"contact_email":{
					"type": "text"
				},
				"country":{
					"type": "text"
				},
				"description":{
				"type": "text"
				},
				"image":{
				"type": "text"
				},
				"post_id":{
					"type": "text"
				},
				"state_province":{
					"type": "text"
				},
				"title":{
					"type": "text"
				},
				"user_id":{
					"type": "text"
				}
			}
		}
	}
}</code></pre>
</li>

<li>Add the headers to the PUT request:
<pre><code>
key: Content-Type, value: application/json
key: Authorization, value: Basic dXNlcjpUQlhxVkRYNkRaaVQ=
</code></pre>
<b>Remember</b> to get your authorization token you just take convert "user:" and your password to base64. So for me my password was "TBXqVDX6DZiT" and username was "user" so user:TBXqVDX6DZiT = dXNlcjpUQlhxVkRYNkRaaVQ=. 
I used the unicode converter here: https://www.base64decode.org/.
</li>

<li>Press "Send" on Postman. If the request is successful you'll get a result that looks like this: 
<pre><code>
{
    "acknowledged": true,
    "shards_acknowledged": true,
    "index": "test"
}
</code></pre>
</li>

</ol>



