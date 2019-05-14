<html>
<head><title> ${blogTitle} </title>
<body>
<h1> ${blogTitle} </h1>
<p>

</p>
<h3>Top 10 Hacker News</h3>
<#list references as reference>
${reference_index + 1}. <a href="${reference.url}"> ${reference.title} </a> <br/>
</#list>
</body>
</html>