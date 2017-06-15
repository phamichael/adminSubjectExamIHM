<%--
  Created by IntelliJ IDEA.
  User: o2122061
  Date: 15/06/17
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ page session="false" %>
<html>
<head>
    <title>Login</title>
    <sj:head/>
    <sb:head/>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-4 col-md-4">
                <br/>
                <br/>
                <br/>
                <s:form action="password" theme="bootstrap">
                    <s:textfield
                            id="login"
                            name="login"
                            placeholder="Login"/>
                    <s:submit id="btn-submit" cssClass="btn btn-primary pull-right" value="Valider"/>
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>