<h1>Expressions Example</h1>

<h2>Lists</h2>

<ul>
<li>A List: [$numbers$]</li>
<li>A List, delimited: [$numbers; separator=", "$]</li>
</ul>

<h2>Maps</h2>

<p>(Keys must be strings, not Clojure keywords)</p>

<p>FN: $user.fname$, LN: $user.lname$</p>

<p>Keys: $user.keys; separator=", "$</p>

<h2>Multi-valued attrs</h2>

<ul>
$numbers:listItem()$
</ul>