<#import "parts/common.ftlh" as c>

<@c.page>
    User editor
    <form action="/user" method="post">
        <input type="text" value="username" name="${user.username}">
        <input type="text" value="${user.id}" name="userID">
        <input type="text" value="$_csrf.token" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>