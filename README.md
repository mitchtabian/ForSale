<h2>Enabling Firebase Cloud Functions</h2>
<ol>
<li>Install Node.js onto your computer. Get it here: https://nodejs.org/en/</li>

<li>Open a command prompt in the Android project directory</li>

<li>Install the Firebase CLI tool:
<pre><code>npm install -g firebase-tools</code></pre>
</li>

<li>Log into Firebase:
<pre><code>firebase login</code></pre>
</li>

<li>Initialize your Android project to enable the use of Firebase Cloud Functions:
<pre><code>firebase init functions</code></pre>
You'll be asked to select your project. Obviously select the project you're working with.
</li>

<li>Install two node.js packages that will make it easier to send requests to your ElasticSearch server:
<pre><code>npm install --save request request-promise</code></pre>
</li>

<li>Setup the config file for this firebase project so it can communicate with the ElasticSearch server:
<pre><code>firebase functions:config:set elasticsearch.username="user" elasticsearch.password="your password" elasticsearch.url="your elasticsearch url"</code></pre>
</li>


</ol>

